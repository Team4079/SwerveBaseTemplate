@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package frc.robot.subsystems

import com.ctre.phoenix6.hardware.Pigeon2
import com.pathplanner.lib.auto.AutoBuilder
import com.pathplanner.lib.config.PIDConstants
import com.pathplanner.lib.controllers.PPHolonomicDriveController
import edu.wpi.first.math.VecBuilder
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.math.kinematics.SwerveDriveKinematics
import edu.wpi.first.math.kinematics.SwerveModulePosition
import edu.wpi.first.math.kinematics.SwerveModuleState
import edu.wpi.first.math.util.Units
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.DriverStation.Alliance
import edu.wpi.first.wpilibj.smartdashboard.Field2d
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.utils.PID
import frc.robot.utils.RobotParameters.MotorParameters
import frc.robot.utils.RobotParameters.SwerveParameters
import frc.robot.utils.RobotParameters.SwerveParameters.PIDParameters
import frc.robot.utils.RobotParameters.SwerveParameters.Thresholds.SHOULD_INVERT
import frc.robot.utils.dash
import frc.robot.utils.dashPID

/** The [SwerveSubsystem] class includes all the motors to drive the robot. */
class SwerveSubsystem(photonvision: Photonvision) : SubsystemBase() {
  private val poseEstimator: SwerveDrivePoseEstimator
  private val field = Field2d()

  private val pidgey = Pigeon2(MotorParameters.PIDGEY_ID)
  private val states: Array<SwerveModuleState?>
  private val modules =
    arrayOf(
      SwerveModule(
        MotorParameters.FRONT_LEFT_DRIVE_ID,
        MotorParameters.FRONT_LEFT_STEER_ID,
        MotorParameters.FRONT_LEFT_CAN_CODER_ID,
        SwerveParameters.Thresholds.CANCODER_VAL9,
      ),
      SwerveModule(
        MotorParameters.FRONT_RIGHT_DRIVE_ID,
        MotorParameters.FRONT_RIGHT_STEER_ID,
        MotorParameters.FRONT_RIGHT_CAN_CODER_ID,
        SwerveParameters.Thresholds.CANCODER_VAL10,
      ),
      SwerveModule(
        MotorParameters.BACK_LEFT_DRIVE_ID,
        MotorParameters.BACK_LEFT_STEER_ID,
        MotorParameters.BACK_LEFT_CAN_CODER_ID,
        SwerveParameters.Thresholds.CANCODER_VAL11,
      ),
      SwerveModule(
        MotorParameters.BACK_RIGHT_DRIVE_ID,
        MotorParameters.BACK_RIGHT_STEER_ID,
        MotorParameters.BACK_RIGHT_CAN_CODER_ID,
        SwerveParameters.Thresholds.CANCODER_VAL12,
      ),
    )

  private val photonvision: Photonvision

  private val pid =
    PID(
      SmartDashboard.getNumber("AUTO: P", PIDParameters.DRIVE_PID_AUTO.p),
      SmartDashboard.getNumber("AUTO: I", PIDParameters.DRIVE_PID_AUTO.i),
      SmartDashboard.getNumber("AUTO: D", PIDParameters.DRIVE_PID_AUTO.d),
    )
  private var velocity = 0.0

  init {
    pidgey.reset()
    states = arrayOfNulls(4)
    this.photonvision = photonvision

    poseEstimator =
      SwerveDrivePoseEstimator(
        SwerveParameters.PhysicalParameters.kinematics,
        Rotation2d.fromDegrees(heading),
        modulePositions,
        Pose2d(0.0, 0.0, Rotation2d.fromDegrees(0.0)),
        VecBuilder.fill(0.05, 0.05, Units.degreesToRadians(5.0)),
        VecBuilder.fill(0.5, 0.5, Units.degreesToRadians(30.0)),
      )

    AutoBuilder.configure(
      { this.pose }, // Robot pose supplier
      { pose: Pose2d? ->
        this.newPose(pose)
      }, // Method to reset odometry (will be called if your auto has a starting pose)
      { this.autoSpeeds }, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
      { chassisSpeeds: ChassisSpeeds? ->
        this.chassisSpeedsDrive(chassisSpeeds)
      }, // Method that will drive the robot given ROBOT RELATIVE
      PPHolonomicDriveController( // PPHolonomicController is the built-in path following
        // controller for holonomic drive trains
        PIDConstants(5.0, 0.0, 0.0), // Translation PID constants
        PIDConstants(5.0, 0.0, 0.0), // Rotation PID constants
      ),
      PIDParameters.config,
      {
        val alliance = DriverStation.getAlliance()
        alliance
          .map {
            if (SHOULD_INVERT) {
              it == Alliance.Red
            } else {
              it != Alliance.Blue
            }
          }
          .orElse(false)
      },
      this, // Reference to this subsystem to set requirements
    )
  }

  /**
   * This method will be called once per scheduler run.
   * It updates the robot's pose estimation and logs relevant data to the dashboard.
   */
  override fun periodic() {
    if (DriverStation.isTeleop()) {
      photonvision.getEstimatedGlobalPose(poseEstimator.estimatedPosition)?.apply {
        val timestamp = timestampSeconds
        val estimatedPose = estimatedPose
        val visionMeasurement2d = estimatedPose.toPose2d()
        poseEstimator.addVisionMeasurement(visionMeasurement2d, timestamp)
        SwerveParameters.Thresholds.currentPose = poseEstimator.estimatedPosition
      }
    }

    poseEstimator.update(pidgeyRotation, modulePositions)

    field.robotPose = poseEstimator.estimatedPosition

    dash(
      "Pitch" to pidgey.pitch.valueAsDouble,
      "Heading" to -pidgey.yaw.valueAsDouble,
      "Yaw" to pidgey.yaw.valueAsDouble,
      "Roll" to pidgey.roll.valueAsDouble,
      "Robot Pose" to field,
    )
  }

  /**
   * Sets the drive speeds for the robot.
   *
   * @param forwardSpeed The forward speed.
   * @param leftSpeed The left speed.
   * @param turnSpeed The turn speed.
   * @param isFieldOriented Whether the drive is field-oriented.
   */
  fun setDriveSpeeds(
    forwardSpeed: Double,
    leftSpeed: Double,
    turnSpeed: Double,
    isFieldOriented: Boolean,
  ) {
    dash("Forward speed" to forwardSpeed, "Left speed" to leftSpeed, "Pidgey Heading" to heading)

    val speeds = ChassisSpeeds(forwardSpeed, leftSpeed, turnSpeed).apply {
      if (!isFieldOriented) toRobotRelativeSpeeds(pidgeyRotation)
    }

    val states2 = SwerveParameters.PhysicalParameters.kinematics.toSwerveModuleStates(speeds)
    SwerveDriveKinematics.desaturateWheelSpeeds(states2, MotorParameters.MAX_SPEED)
    moduleStates = states2
  }

  val pidgeyRotation: Rotation2d
    /**
     * Gets the pidgey rotation.
     *
     * @return [Rotation2d] The current rotation of the [pidgey].
     */
    get() = pidgey.rotation2d

  val heading: Double
    /**
     * Gets the pidgey angle.
     *
     * @return [Double] The current angle of the [pidgey].
     */
    get() = -pidgey.yaw.valueAsDouble

  val pidgeyYaw: Double
    /**
     * Gets the pidgey yaw.
     *
     * @return [Double] The current yaw of the [pidgey].
     */
    get() = pidgey.yaw.valueAsDouble

  fun setHeading() {
    val alliance = DriverStation.getAlliance()
    if (alliance.get() == Alliance.Red) {
      pidgey.setYaw(27.4)
    } else {
      pidgey.setYaw(-27.4)
    }
  }

  /** Resets the pidgey(gyro) heading to 0. */
  fun resetPidgey() {
    pidgey.reset()
  }

  val pose: Pose2d
    /**
     * Gets the current pose of the robot.
     *
     * @return [Pose2d] The current pose of the robot.
     */
    get() = poseEstimator.estimatedPosition

  /** Zeros the pose of the robot. */
  fun zeroPose() {
    poseEstimator.resetPosition(
      Rotation2d.fromDegrees(heading),
      modulePositions,
      Pose2d(0.0, 0.0, Rotation2d.fromDegrees(0.0)),
    )
  }

  /**
   * Resets the pose of the robot.
   *
   * @param pose The new pose to set.
   */
  fun newPose(pose: Pose2d?) {
    poseEstimator.resetPosition(Rotation2d.fromDegrees(heading), modulePositions, pose)
  }

  val autoSpeeds: ChassisSpeeds
    /**
     * Gets the chassis speeds for autonomous driving.
     *
     * @return [ChassisSpeeds] The current chassis speeds.
     */
    get() {
      val k = SwerveParameters.PhysicalParameters.kinematics
      // Necessary because a kotlin array is being passed into a java varargs parameter
      val chassisSpeeds =
        k::class.java.getMethod("toChassisSpeeds", Array<SwerveModuleState>::class.java)
      return chassisSpeeds.invoke(k, moduleStates) as ChassisSpeeds
    }

  val rotationPidggy: Rotation2d
    /**
     * Gets the rotation of the pidgey for PID control.
     *
     * @return [Rotation2d] The rotation of the pidgey for PID control.
     */
    get() = Rotation2d.fromDegrees(-pidgey.rotation2d.degrees)

  /**
   * Drives the robot using the given chassis speeds.
   *
   * @param chassisSpeeds The desired chassis speeds.
   */
  fun chassisSpeedsDrive(chassisSpeeds: ChassisSpeeds?) {
    // ChassisSpeeds speeds =
    //     ChassisSpeeds.fromRobotRelativeSpeeds(chassisSpeeds, getRotationPidggy());
    // SwerveModuleState[] newStates = SwerveGlobalValues.kinematics.toSwerveModuleStates(speeds);
    // SwerveDriveKinematics.desaturateWheelSpeeds(newStates, MotorGlobalValues.MAX_SPEED);

    val newStates =
      SwerveParameters.PhysicalParameters.kinematics.toSwerveModuleStates(chassisSpeeds)
    moduleStates = newStates
  }

  private var moduleStates: Array<SwerveModuleState>
    /**
     * Gets the current states of the swerve modules.
     *
     * @return SwerveModuleState[] The current states of the swerve modules.
     */
    get() {
      val moduleStates = emptyArray<SwerveModuleState>()
      for (i in modules.indices) {
        moduleStates[i] = modules[i].state
      }
      return moduleStates
    }
    /**
     * Sets the desired module states.
     *
     * @param states SwerveModuleState[] The desired states for the swerve modules.
     */
    set(states) {
      for (i in states.indices) {
        modules[i].state = states[i]
      }
    }

  val modulePositions: Array<SwerveModulePosition?>
    /**
     * Gets the current positions of the swerve modules.
     *
     * @return SwerveModulePosition[] The current positions of the swerve modules.
     */
    get() {
      val positions = arrayOfNulls<SwerveModulePosition>(states.size)

      for (i in positions.indices) {
        positions[i] = modules[i].position
      }

      return positions
    }

  /** Stops the robot. */
  fun stop() = modules.forEach { it.stop() }

  fun setAutoPID() = modules.forEach { it.setAUTOPID() }

  fun setTelePID() = modules.forEach { it.setTELEPID() }

  fun resetDrive() = modules.forEach { it.resetDrivePosition() }

  fun setCustomDrivePID() {
    dashPID("Drive", pid, PIDParameters.DRIVE_PID_V_AUTO) { velocity = it }
    modules.forEach { it.setAUTOPID(pid, velocity) }
  }
}
