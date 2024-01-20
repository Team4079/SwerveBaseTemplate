// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.sql.Driver;

// import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
// import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.LimelightHelpers;

@SuppressWarnings("unused") // Used in order to remove warnings
public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  private final NetworkTable m_limelightTable;
  private LimelightHelpers m_helpers = new LimelightHelpers();
  LimelightHelpers.LimelightResults llresults;
  double tv, tx, ty, ta = 0.0;
  private Pose2d robotPose_FieldSpace;
  private GenericEntry vision;

  private double[] robotPoseTargetSpace;

  private Field2d field = new Field2d();

  public Limelight() {
    m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    // HttpCamera limelightCamera = new HttpCamera("limelight",
    // "http://limelight.local:5801/stream.mjpg");
    llresults = LimelightHelpers.getLatestResults("limelight");
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    tv = m_limelightTable.getEntry("tv").getDouble(0);
    tx = m_limelightTable.getEntry("tx").getDouble(0);
    ty = m_limelightTable.getEntry("ty").getDouble(0);
    ta = m_limelightTable.getEntry("ta").getDouble(0);

    if (DriverStation.getAlliance().equals(DriverStation.Alliance.Red)) {
      robotPose_FieldSpace = llresults.targetingResults.getBotPose2d_wpiRed();
    } else {
      robotPose_FieldSpace = llresults.targetingResults.getBotPose2d_wpiBlue();
    }

    robotPoseTargetSpace = LimelightHelpers.getBotPose_TargetSpace("limelight");
    SmartDashboard.putNumber("April Tag X", LimelightHelpers.getTX("limelight"));
    field.setRobotPose(robotPose_FieldSpace);
    SmartDashboard.putData("Field Vision", field);  
  }

  public double getTx() {
    return tx;
  }

  public double getTa() {
    return ta;
  }

  public double getTv() {
    return tv;
  }

  public double getTy() {
    return ty;
  }

  public boolean isTarget() {
    return LimelightHelpers.getTX("limelight") != 0;
  }

  public void setLEDMode(double mode) {
    m_limelightTable.getEntry("ledMode").setNumber(mode);
  }

  public void setPipeline(double pipeline) {
    m_limelightTable.getEntry("pipeline").setNumber(pipeline);
  }

  public void setCamMode(double mode) {
    m_limelightTable.getEntry("camMode").setNumber(mode);
  }

  public void setSnapshot(double mode) {
    m_limelightTable.getEntry("snapshot").setNumber(mode);
  }

  public void setStream(double mode) {
    m_limelightTable.getEntry("stream").setNumber(mode);
  }

  public void setAdvanced(double mode) {
    m_limelightTable.getEntry("advanced_mode").setNumber(mode);
  }

  public double getLatency() {
    return llresults.targetingResults.latency_capture;
  }

  public double getTag() {
    return LimelightHelpers.getFiducialID("");
  }

  public Pose2d getRobotPosition() {
    return robotPose_FieldSpace;
  }
}