package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.GlobalsValues;
import frc.robot.utils.PID;
import frc.robot.utils.GlobalsValues.MotorGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues.BasePIDGlobal;

import java.util.Optional;
import org.photonvision.EstimatedRobotPose;

/** The {@link SwerveSubsystem} class includes all the motors to drive the robot. */
public class SwerveSubsystem extends SubsystemBase {
  private final SwerveDrivePoseEstimator poseEstimator;
  private final Field2d field;

  private final Pigeon2 pidgey;
  private final SwerveModuleState[] states;
  private final SwerveModule[] modules;

  private final Photonvision photonvision;

  private static final boolean SHOULD_INVERT = false;
  private PID pid = new PID(SmartDashboard.getNumber("AUTO: P", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.p), SmartDashboard.getNumber("AUTO: I", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.i), SmartDashboard.getNumber("AUTO: D", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.d));
  private double velocity;

  /**
   * Constructs a new SwerveSubsystem.
   *
   * @param photonvision The Photonvision instance used for vision processing.
   */
  public SwerveSubsystem(Photonvision photonvision) {
    modules =
        new SwerveModule[] {
          new SwerveModule(
              MotorGlobalValues.FRONT_LEFT_DRIVE_ID,
              MotorGlobalValues.FRONT_LEFT_STEER_ID,
              MotorGlobalValues.FRONT_LEFT_CAN_CODER_ID,
              SwerveGlobalValues.CANCoderValue9),
          new SwerveModule(
              MotorGlobalValues.FRONT_RIGHT_DRIVE_ID,
              MotorGlobalValues.FRONT_RIGHT_STEER_ID,
              MotorGlobalValues.FRONT_RIGHT_CAN_CODER_ID,
              SwerveGlobalValues.CANCoderValue10),
          new SwerveModule(
              MotorGlobalValues.BACK_LEFT_DRIVE_ID,
              MotorGlobalValues.BACK_LEFT_STEER_ID,
              MotorGlobalValues.BACK_LEFT_CAN_CODER_ID,
              SwerveGlobalValues.CANCoderValue11),
          new SwerveModule(
              MotorGlobalValues.BACK_RIGHT_DRIVE_ID,
              MotorGlobalValues.BACK_RIGHT_STEER_ID,
              MotorGlobalValues.BACK_RIGHT_CAN_CODER_ID,
              SwerveGlobalValues.CANCoderValue12)
        };

    field = new Field2d();

    pidgey = new Pigeon2(MotorGlobalValues.PIDGEY_ID);
    pidgey.reset();
    states = new SwerveModuleState[4];
    this.photonvision = photonvision;

    poseEstimator =
        new SwerveDrivePoseEstimator(
            GlobalsValues.SwerveGlobalValues.kinematics,
            Rotation2d.fromDegrees(getHeading()),
            getModulePositions(),
            new Pose2d(0, 0, Rotation2d.fromDegrees(0)),
            VecBuilder.fill(0.05, 0.05, Units.degreesToRadians(5)),
            VecBuilder.fill(0.5, 0.5, Units.degreesToRadians(30)));

    AutoBuilder.configureHolonomic(
        this::getPose, // Robot pose supplier
        this::newPose, // Method to reset odometry (will be called if your auto has a starting pose)
        this::getAutoSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
        this::chassisSpeedsDrive, // Method that will drive the robot given ROBOT RELATIVE
        // ChassisSpeeds
        SwerveGlobalValues.BasePIDGlobal.pathFollower,
        () -> {
          Optional<DriverStation.Alliance> alliance = DriverStation.getAlliance();
          if (alliance.isPresent()) {
            if (SHOULD_INVERT) {
              return alliance.get() == DriverStation.Alliance.Red;
            } else {
              return alliance.get() != DriverStation.Alliance.Blue;
            }
          }
          return false;
        },
        this // Reference to this subsystem to set requirements
        );

    // SmartDashboard.getNumber("AUTO: P", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.p);
    // SmartDashboard.getNumber("AUTO: I", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.i);
    // SmartDashboard.getNumber("AUTO: D", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.d);
    // SmartDashboard.getNumber("AUTO: V", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_V_AUTO);
  }

  // This method will be called once per scheduler run
  @Override
  public void periodic() {

    if (DriverStation.isTeleop())
    {
      Optional<EstimatedRobotPose> visionMeasurement3d =
          photonvision.getEstimatedGlobalPose(poseEstimator.getEstimatedPosition());
      if (!visionMeasurement3d.isEmpty()) {
        double timestamp = visionMeasurement3d.get().timestampSeconds;
        Pose3d estimatedPose = visionMeasurement3d.get().estimatedPose;
        Pose2d visionMeasurement2d = estimatedPose.toPose2d();
        poseEstimator.addVisionMeasurement(visionMeasurement2d, timestamp);
        poseEstimator.getEstimatedPosition();
        SwerveGlobalValues.currentPose = poseEstimator.getEstimatedPosition();
      }
  }

    poseEstimator.update(getPidgeyRotation(), getModulePositions());

    field.setRobotPose(poseEstimator.getEstimatedPosition());
    // Pidgeon Stuff
    // Global Boolean Value called TEST_MODE if true graph all smartdashboard values
    if(BasePIDGlobal.TEST_MODE == true) {
        SmartDashboard.putData("Robot Pose", field);
        SmartDashboard.putNumber("Pitch", pidgey.getPitch().getValueAsDouble());
        SmartDashboard.putNumber("Heading", pidgey.getAngle());
        SmartDashboard.putNumber("Yaw", pidgey.getYaw().getValueAsDouble());
        SmartDashboard.putNumber("Roll", pidgey.getRoll().getValueAsDouble());
        // SmartDashboard.putData("Pose", getPose().getTranslation().get);
    }
  }

  /**
   * Sets the desired module states.
   *
   * @param states SwerveModuleState[] The desired states for the swerve modules.
   */
  public void setModuleStates(SwerveModuleState[] states) {
    for (int i = 0; i < states.length; i++) {
      modules[i].setState(states[i]);
    }
  }

  /**
   * Sets the drive speeds for the robot.F
   * @param forwardSpeed The forward speed.
   * @param leftSpeed The left speed.
   * @param turnSpeed The turn speed.
   * @param isFieldOriented Whether the drive is field-oriented.
   */
  public void setDriveSpeeds(
      double forwardSpeed, double leftSpeed, double turnSpeed, boolean isFieldOriented) {
    ChassisSpeeds speeds;

  if(BasePIDGlobal.TEST_MODE == true) {
    SmartDashboard.putNumber("Forward speed", forwardSpeed);
    SmartDashboard.putNumber("Left speed", leftSpeed);
    SmartDashboard.putNumber("Pidgey Heading", getHeading());
  }

    if (isFieldOriented) {
      speeds =
          ChassisSpeeds.fromFieldRelativeSpeeds(
              forwardSpeed, leftSpeed, turnSpeed, getPidgeyRotation());
    } else {
      speeds = new ChassisSpeeds(forwardSpeed, leftSpeed, turnSpeed);
    }

    SwerveModuleState[] states2 = SwerveGlobalValues.kinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states2, MotorGlobalValues.MAX_SPEED);
    setModuleStates(states2);
  }

  /**
   * Gets the pidgey rotation.
   *
   * @return Rotation2d The current rotation of the pidgey.
   */
  public Rotation2d getPidgeyRotation() {
    return pidgey.getRotation2d();
  }

  /**
   * Gets the pidgey angle.
   *
   * @return double The current angle of the pidgey.
   */
  public double getHeading() {
    return pidgey.getAngle();
  }

  public double getPidgeyYaw() {
    return pidgey.getYaw().getValueAsDouble();
  }

  public void setHeading() {
    Optional<DriverStation.Alliance> alliance = DriverStation.getAlliance();
    if (alliance.get() == DriverStation.Alliance.Red) {
      pidgey.setYaw(27.4);
    }
    else {
      pidgey.setYaw(-27.4);
    }
  }

  /** Resets the pidgey(gyro) heading to 0. */
  public void resetPidgey() {
    pidgey.reset();
  }

  /**
   * Gets the current pose of the robot.
   *
   * @return Pose2d The current pose of the robot.
   */
  public Pose2d getPose() {
    return poseEstimator.getEstimatedPosition();
  }

  /** Zeros the pose of the robot. */
  public void zeroPose() {
    poseEstimator.resetPosition(
        Rotation2d.fromDegrees(getHeading()),
        getModulePositions(),
        new Pose2d(0, 0, Rotation2d.fromDegrees(0)));
  }

  /**
   * Resets the pose of the robot.
   *
   * @param pose The new pose to set.
   */
  public void newPose(Pose2d pose) {
    poseEstimator.resetPosition(Rotation2d.fromDegrees(getHeading()), getModulePositions(), pose);
  }

  /**
   * Gets the chassis speeds for autonomous driving.
   *
   * @return ChassisSpeeds The current chassis speeds.
   */
  public ChassisSpeeds getAutoSpeeds() {
    return SwerveGlobalValues.kinematics.toChassisSpeeds(getModuleStates());
  }

  /**
   * Gets the rotation of the pidgey for PID control.
   *
   * @return Rotation2d The rotation of the pidgey for PID control.
   */
  public Rotation2d getRotationPidggy() {
    return Rotation2d.fromDegrees(-pidgey.getRotation2d().getDegrees());
  }

  /**
   * Drives the robot using the given chassis speeds.
   *
   * @param chassisSpeeds The desired chassis speeds.
   */
  public void chassisSpeedsDrive(ChassisSpeeds chassisSpeeds) {
    // ChassisSpeeds speeds =
    //     ChassisSpeeds.fromRobotRelativeSpeeds(chassisSpeeds, getRotationPidggy());
    // SwerveModuleState[] newStates = SwerveGlobalValues.kinematics.toSwerveModuleStates(speeds);
    // SwerveDriveKinematics.desaturateWheelSpeeds(newStates, MotorGlobalValues.MAX_SPEED);

    SwerveModuleState[] newStates =
        SwerveGlobalValues.kinematics.toSwerveModuleStates(chassisSpeeds);
    setModuleStates(newStates);
  }

  /**
   * Gets the current states of the swerve modules.
   *
   * @return SwerveModuleState[] The current states of the swerve modules.
   */
  private SwerveModuleState[] getModuleStates() {
    SwerveModuleState[] moduleStates = new SwerveModuleState[modules.length];
    for (int i = 0; i < modules.length; i++) {
      moduleStates[i] = modules[i].getState();
    }
    return moduleStates;
  }

  /**
   * Gets the current positions of the swerve modules.
   *
   * @return SwerveModulePosition[] The current positions of the swerve modules.
   */
  public SwerveModulePosition[] getModulePositions() {
    SwerveModulePosition[] positions = new SwerveModulePosition[states.length];

    for (int i = 0; i < positions.length; i++) {
      positions[i] = modules[i].getPosition();
    }

    return positions;
  }

  /** Stops the robot. */
  public void stop() {
    for (SwerveModule module : modules) {
      module.stop();
    }
  }

  public void setAutoPID() {
    for (SwerveModule module : modules) {
      module.setAUTOPID();
    }
  }

  public void setTelePID() {
    for (SwerveModule module : modules) {
      module.setTELEPID();
    }
  }

  public void resetDrive() {
    for (SwerveModule module : modules) {
      module.resetDrivePosition();
    }
  }

  public void setCustomDrivePID() {
    for (int i = 0; i < modules.length; i++) {
      pid.p = SmartDashboard.getNumber("AUTO: P", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.p);
      pid.i = SmartDashboard.getNumber("AUTO: I", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.i);
      pid.d = SmartDashboard.getNumber("AUTO: D", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_AUTO.d);
      velocity = SmartDashboard.getNumber("AUTO: V", SwerveGlobalValues.BasePIDGlobal.DRIVE_PID_V_AUTO);
      modules[i].setAUTOPID(pid, velocity);
    }
  }
}