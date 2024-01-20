package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.PathPlannerLogging;
import com.pathplanner.lib.util.ReplanningConfig;

// import edu.wpi.first.math.controller.PIDController;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
// import frc.robot.Robot;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.MotorConstants;
import frc.robot.utils.Constants.SwerveConstants;

@SuppressWarnings("unused") // Used in order to remove warnings
public class SwerveSubsystem extends SubsystemBase {
  // private SwerveModule frontLeft;
  // private SwerveModule frontRight;
  // private SwerveModule backLeft;
  // private SwerveModule backRight;
  private SwerveModule[] modules;
  private SwerveDrivePoseEstimator estimator;
  private double delta;
  private Rotation2d gyroAngle = Rotation2d.fromDegrees(0);
  private Pigeon2 pidggy;
  public SwerveDriveOdometry swerveOdometry;

  private double rot;
  private boolean vision;
  private final SwerveDriveKinematics sKinematics = Constants.SwerveConstants.kinematics;
  private double turnSpeed = 0;
  private Timer timer;

  public static final HolonomicPathFollowerConfig pathFollowerConfig = new HolonomicPathFollowerConfig(
      new PIDConstants(0, 0, 0),
      new PIDConstants(0, 0, 0),
      MotorConstants.MAX_SPEED,
      Math.hypot(SwerveConstants.robotSize / 2, SwerveConstants.robotSize / 2),
      new ReplanningConfig());

  private Field2d field = new Field2d();

  /** Creates a new DriveTrain. */
  public SwerveSubsystem() {
    pidggy = new Pigeon2(16);
    modules = new SwerveModule[] {
        new SwerveModule(
            MotorConstants.FRONT_LEFT_DRIVE_ID,
            MotorConstants.FRONT_LEFT_STEER_ID,
            MotorConstants.FRONT_LEFT_CAN_CODER_ID,
            SwerveConstants.CANCoderValue9),
        new SwerveModule(
            MotorConstants.FRONT_RIGHT_DRIVE_ID,
            MotorConstants.FRONT_RIGHT_STEER_ID,
            MotorConstants.FRONT_RIGHT_CAN_CODER_ID,
            SwerveConstants.CANCoderValue10),
        new SwerveModule(
            MotorConstants.BACK_LEFT_DRIVE_ID,
            MotorConstants.BACK_LEFT_STEER_ID,
            MotorConstants.BACK_LEFT_CAN_CODER_ID,
            SwerveConstants.CANCoderValue11),
        new SwerveModule(
            MotorConstants.BACK_RIGHT_DRIVE_ID,
            MotorConstants.BACK_RIGHT_STEER_ID,
            MotorConstants.BACK_RIGHT_CAN_CODER_ID,
            SwerveConstants.CANCoderValue12)
    };

    swerveOdometry = new SwerveDriveOdometry(Constants.SwerveConstants.kinematics, Rotation2d.fromDegrees(getYaw()),
        getModulePositions());

    // Creating my odometry object from the kinematics object and the initial wheel
    // positions.
    // Here, our starting pose is 5 meters along the long end of the field and in
    // the
    // center of the field along the short end, facing the opposing alliance wall.
    estimator = new SwerveDrivePoseEstimator(
        SwerveConstants.kinematics,
        getRotationPidggy(),
        getModulePositions(),
        SwerveConstants.STARTING_POSE);

    addRotorPositionsforModules();
    timer = new Timer();

    AutoBuilder.configureHolonomic(
        this::getPose, // Robot pose supplier
        this::resetOdometry, // Method to reset odometry (will be called if your auto has a starting pose)
        this::getAutoSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
        this::chassisSpeedsDrive, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
        new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your Constants class
            new PIDConstants(0.14, 0.0002, 0.0),
            new PIDConstants(0.01, 0.0, 0.0),
            4.96824, // Max module speed, in m/s
            SwerveConstants.robotSize / 2, // Drive base radius in meters. Distance from robot center to furthest module.
            new ReplanningConfig() // Default path replanning config. See the API for the options here
        ),
        () -> {
          var alliance = DriverStation.getAlliance();
          if (alliance.isPresent()) {
            return alliance.get() == DriverStation.Alliance.Red;
          }
          return false;
        },
        this // Reference to this subsystem to set requirements
    );

    PathPlannerLogging.setLogActivePathCallback((poses) -> field.getObject("path").setPoses(poses));

    SmartDashboard.putData("Field", field);
  }

  public void drive(double forwardSpeed, double leftSpeed, double joyStickInput, boolean isFieldOriented) {
    ChassisSpeeds speeds;

    turnSpeed = joyStickInput * MotorConstants.TURN_CONSTANT;

    if (isFieldOriented) {
      speeds = ChassisSpeeds.fromFieldRelativeSpeeds(
          forwardSpeed,
          leftSpeed,
          turnSpeed,
          getRotationPidggy());
    } else {
      speeds = new ChassisSpeeds(
          forwardSpeed,
          leftSpeed,
          joyStickInput);
    }

    SwerveModuleState[] states = SwerveConstants.kinematics.toSwerveModuleStates(speeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(
        states, MotorConstants.MAX_SPEED);

    for (int i = 0; i < modules.length; i++) {
      modules[i].setState(states[i]);

      SmartDashboard.putNumber("MotorRot" + i, modules[i].getRotationDegree() % 360);
    }
  }

  public SwerveModulePosition[] getModulePositions() {
    SwerveModulePosition[] positions = new SwerveModulePosition[modules.length];
    for (int i = 0; i < modules.length; i++) {
      positions[i] = modules[i].getPosition();
    }
    return positions;
  }

  public Rotation2d getRotationPidggy() {
    rot = -pidggy.getRotation2d().getDegrees();
    return Rotation2d.fromDegrees(rot);
    // SmartDashboard.putNumber("Rotation2d", pidggy.getRotation2d().getDegrees());
    // return pidggy.getRotation2d();
  }

  public void resetPose(Rotation2d gyroAngle, SwerveModulePosition[] modulePositions, Pose2d newPose) {
    estimator.resetPosition(gyroAngle, modulePositions, newPose);
    // navX.getRotation2d();
  }

  public void zeroHeading() {
    pidggy.reset();
  }

  // public void resetPitch(){
  // pidggy.setYawPitchRoll(0,0,0);
  // }

  public void resetDriveEncoders() {
    for (int i = 0; i < modules.length; i++) {
      modules[i].resetEncoders();
    }
  }

  public double getYaw() {
    return pidggy.getYaw().getValue();
  }

  public double pgetHeading() {
    return (pidggy.getYaw().getValue() % 360);
  }

  public void configSlowMode() {
    MotorConstants.SLOW_MODE = !Constants.MotorConstants.SLOW_MODE;
  }

  public boolean getSlowMode() {
    return MotorConstants.SLOW_MODE;
  }

  public void configAAcornMode() {
    MotorConstants.AACORN_MODE = !Constants.MotorConstants.AACORN_MODE;
  }

  public boolean getAAcornMode() {
    return MotorConstants.AACORN_MODE;
  }

  // public void updatePosition(){
  // this.addVision(limelight.getRobotPosition());
  // }

  public Pose2d getPose() {
    return swerveOdometry.getPoseMeters();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    pidggy.getYaw().refresh();

    swerveOdometry.update(Rotation2d.fromDegrees(getYaw()), getModulePositions());

    if (vision) {
      // updatePosition();
    }

    field.setRobotPose(getPose());

    gyroAngle = getRotationPidggy();
    SmartDashboard.putNumber("Gyro Angle", gyroAngle.getDegrees());
    estimator.update(gyroAngle, getModulePositions());
  }

  public double getPitch() {
    return pidggy.getPitch().getValue();
  }

  public void stopModules() {
    for (SwerveModule module : modules) {
      module.stop();
    }
  }

  public void test(int moduleNum, double driveSpeed, double rotationSpeed) {
    SwerveModule module = modules[moduleNum];

    module.setDriveSpeed(driveSpeed);
    module.setSteerSpeed(rotationSpeed);
  }

  public void addRotorPositionsforModules() {
    for (int i = 0; i < modules.length; i++) {
      modules[i].setRotorPos();
    }
  }

  public void stop() {
    for (int i = 0; i < modules.length; i++) {
      modules[i].stop();
    }
  }

  public void updateEstimator() {
    estimator.update(getRotationPidggy(), getModulePositions());

  }

  public void addVision() {
    estimator.addVisionMeasurement(null, Timer.getFPGATimestamp());
  }

  public Pose2d getOdometry() {
    return estimator.getEstimatedPosition();
  }

  public void resetOdometry(Pose2d pose) {
    swerveOdometry.resetPosition(Rotation2d.fromDegrees(getYaw()), getModulePositions(), pose);
  }

  public void addVision(Pose2d visionPose) {
    estimator.addVisionMeasurement(visionPose, Timer.getFPGATimestamp());
  }

  public void outputModuleStates(SwerveModuleState[] states) {
    SwerveDriveKinematics.desaturateWheelSpeeds(
        states, MotorConstants.MAX_SPEED);

    for (int i = 0; i < modules.length; i++) {
      modules[i].setState(states[i]);
    }
  }

  public double getRotorPositions(int moduleNum) {
    return modules[moduleNum].steerMotor.getRotorPosition().getValue();
  }

  public double getCanCoderValues(int canID) {
    double[] canCoderValues = new double[modules.length];
    for (int i = 0; i < modules.length; i++) {
      canCoderValues[i] = modules[i].getCanCoderValue();
    }
    return canCoderValues[canID - 9];
  }

  public double getX() {
    return estimator.getEstimatedPosition().getTranslation().getX();
  }

  public double getY() {
    return estimator.getEstimatedPosition().getTranslation().getY();
  }

  public ChassisSpeeds getAutoSpeeds() {
    return ChassisSpeeds.fromRobotRelativeSpeeds(0, 0, 0, Rotation2d.fromDegrees(0));
    // return ChassisSpeeds.fromRobotRelativeSpeeds(turnSpeed, rot, delta, gyroAngle);
  }  

  public void chassisSpeedsDrive(ChassisSpeeds chassisSpeeds) {
    SwerveModuleState[] states = SwerveConstants.kinematics.toSwerveModuleStates(chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(
        states, MotorConstants.MAX_SPEED);

    for (int i = 0; i < modules.length; i++) {
      modules[i].setState(states[i]);
    }
  }
}