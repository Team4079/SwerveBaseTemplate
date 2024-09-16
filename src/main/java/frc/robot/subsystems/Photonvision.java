package frc.robot.subsystems;

import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * The PhotonVision subsystem handles vision processing using PhotonVision cameras.
 */
public class Photonvision extends SubsystemBase {
  // PhotonVision cameras
  PhotonCamera camera1 = new PhotonCamera("Camera One");
  PhotonCamera camera2 = new PhotonCamera("Camera Two");

  // Tracked targets from the cameras
  PhotonTrackedTarget target1;
  PhotonTrackedTarget target2;

  // Pose estimator for determining the robot's position on the field
  PhotonPoseEstimator photonPoseEstimator;

  // AprilTag field layout for the 2024 Crescendo field
  AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();

  // Transformation from the robot to the camera
  Transform3d robotToCam = new Transform3d(new Translation3d(0.5, 0.0, 0.5), new Rotation3d(0,0,0)); // Cam mounted facing forward, half a meter forward of center, half a meter up from center.

  /**
   * Constructs a new PhotonVision subsystem.
   */
  public Photonvision() {
    photonPoseEstimator = new PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, camera1, robotToCam);
  }

  /**
   * This method is called periodically by the scheduler.
   * It updates the tracked targets and displays relevant information on the SmartDashboard.
   */
  @Override
  public void periodic() {
    var result1 = camera1.getLatestResult();
    var result2 = camera2.getLatestResult();

    if (result1.hasTargets()){
      target1 = result1.getBestTarget();
      if (result1.getMultiTagResult().estimatedPose.isPresent) {
        Transform3d fieldToCamera = result1.getMultiTagResult().estimatedPose.best;
        SmartDashboard.putNumber("field to camera", fieldToCamera.getX());
      }
    }

    target2 = result2.hasTargets() ? result2.getBestTarget() : target2;
  }

  /**
   * Gets the estimated global pose of the robot.
   *
   * @param prevEstimatedRobotPose The previous estimated pose of the robot.
   * @return An Optional containing the estimated robot pose, or empty if no pose could be estimated.
   */
  public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d prevEstimatedRobotPose) {
    photonPoseEstimator.setReferencePose(prevEstimatedRobotPose);
    return photonPoseEstimator.update();
  }
}
