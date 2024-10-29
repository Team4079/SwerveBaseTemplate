
package frc.robot.subsystems;

import static frc.robot.utils.GlobalsValues.SwerveGlobalValues.*;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.GlobalsValues.PhotonVisionConstants;
import java.util.List;
import java.util.Optional;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonUtils;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PNPResult;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

/** The PhotonVision subsystem handles vision processing using PhotonVision cameras. */
public class Photonvision extends SubsystemBase {
  // PhotonVision cameras
  PhotonCamera cameraleft = new PhotonCamera("Left");

  // Pose estimator for determining the robot's position on the field
  PhotonPoseEstimator photonPoseEstimatorleft;

  private Translation2d cameraTrans = new Translation2d(0.31, 0);

  // AprilTag field layout for the 2024 Crescendo field
  AprilTagFieldLayout aprilTagFieldLayout = AprilTagFields.k2024Crescendo.loadAprilTagLayoutField();

  // Transformation from the robot to the camera
  // TODO: Make function to convert Translation2d to Translation3d
  Transform3d leftCameraPos =
      new Transform3d(
          conv2dTo3d(cameraTrans, PhotonVisionConstants.CAMERA_ONE_HEIGHT_METER),
          new Rotation3d(0, Math.toRadians(360 - PhotonVisionConstants.CAMERA_ONE_ANGLE_DEG), Math.toRadians(180)));

  PhotonTrackedTarget targetleft;
  boolean targetVisibleleft = false;
  double targetYawleft = -15.0;
  double targetPoseAmbiguityleft = 7157;
  double rangeleft = 0.0;


  double targetYaw = 0.0;
  double rangeToTarget = 0.0;

  PhotonPipelineResult resultleft;
  PhotonPipelineResult currentResult;

  boolean camleftTag = false;

  Translation3d currentPose;



  /** Constructs a new PhotonVision subsystem. */
  public Photonvision() {
    resultleft = new PhotonPipelineResult();
    photonPoseEstimatorleft =
        new PhotonPoseEstimator(
            aprilTagFieldLayout,
            PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR,
            cameraleft,
            leftCameraPos);
  }
  /**
   * This method is called periodically by the scheduler. It updates the tracked targets and
   * displays relevant information on the SmartDashboard.
   */
  @Override
  public void periodic() {
    resultleft = cameraleft.getLatestResult();
    photonPoseEstimatorleft.update();

    if (resultleft.hasTargets()) {
      targetleft = resultleft.getBestTarget();
      targetPoseAmbiguityleft = targetleft.getPoseAmbiguity();
      // SmartDashboard.putNumber("distleft", distleft);


    
        // SmartDashboard.putBoolean("front of subwoofer", frontOfSubwoofer);
    
        // if (camera.getLatestResult().hasTargets()) {
        //   return camera.getLatestResult().getBestTarget().getYaw();
        // }
        // return 4079;

    // if (resultleft.getMultiTagResult().estimatedPose.isPresent) {
    //     Transform3d fieldToCamera = resultleft.getMultiTagResult().estimatedPose.best;
    //     SmartDashboard.putNumber("field to camera", fieldToCamera.getX());
    // }
    } else {
      targetPoseAmbiguityleft = 7157;
    }

    if(BasePIDGlobal.TEST_MODE == true) {
      SmartDashboard.putNumber("photon yaw", targetYaw);
      SmartDashboard.putNumber("range target", rangeToTarget);
      SmartDashboard.putNumber("april tag distance", getDistanceSubwoofer());
      SmartDashboard.putNumber("april tag yaw", getSubwooferYaw());
      SmartDashboard.putNumber("left cam ambiguity", targetPoseAmbiguityleft);
      SmartDashboard.putBoolean("left_targets", resultleft.hasTargets());
    }


    List<PhotonTrackedTarget> results = cameraleft.getLatestResult().getTargets();
    // boolean frontOfSubwoofer = false;

    for (PhotonTrackedTarget tag : results) {
      if (tag.getFiducialId() == 7 || tag.getFiducialId() == 4) {
        targetYaw = tag.getYaw();
      // } else {
      //   targetYaw = 4079;
      }
    }

    SmartDashboard.putNumber("yaw to target", targetYaw);
    
  }

  public boolean hasTag()
  {
    if (resultleft.hasTargets())
    {
      return true;
    }
    return false;
  }

  /**
   * Gets the estimated global pose of the robot.
   *
   * @param prevEstimatedRobotPose The previous estimated pose of the robot.
   * @return An Optional containing the estimated robot pose, or empty if no pose could be
   *     estimated.
   */
  public Optional<EstimatedRobotPose> getEstimatedGlobalPose(Pose2d prevEstimatedRobotPose) {
    photonPoseEstimatorleft.setReferencePose(prevEstimatedRobotPose);
    return photonPoseEstimatorleft.update();
  }

  public Transform3d getEstimatedGlobalPose() {
  if (resultleft.getMultiTagResult().estimatedPose.isPresent) {
      Transform3d fieldToCamera = resultleft.getMultiTagResult().estimatedPose.best;
      return resultleft.getMultiTagResult().estimatedPose.best;
      // System.out.println(photonPoseEstimatorleft.getReferencePose().getX());
  }
  // return photonPoseEstimatorleft.getReferencePose();
  return new Transform3d(0, 0, 0, new Rotation3d());
  }

  /**
   * Horizontal
   *
   * <p>See <a
   * href="https://docs.photonvision.org/en/latest/docs/additional-resources/nt-api.html#getting-target-information">NetworkTables
   * API</a>
   */
  public double getYaw() {
    return targetYaw;
  }

  /**
   * Forward distance to target
   *
   * <p>See <a
   * href="https://docs.photonvision.org/en/latest/docs/additional-resources/nt-api.html#getting-target-information">NetworkTables
   * API</a>
   */

  public double getPivotPosition() {
    // 10/14/2024 outside tuning
    // jayden why are you so bad at tuning
    // Desmos: https://www.desmos.com/calculator/naalukjxze
    double r = getDistanceSubwoofer();
    double f = -0.03288; // power 5
    double e = 0.6821; // power 4
    double d = -5.1422; // power 3
    double c = 16.4425; // power 2
    double b = -16.5582; // power 1
    double a = 15.4507; // constant

    // if (r == -1)
    // {
    //   return GlobalsValues.PivotGlobalValues.PIVOT_SUBWOOFER_ANGLE;
    // }
    return ((f * Math.pow(r, 5))
        + (e * Math.pow(r, 4))
        + (d * Math.pow(r, 3))
        + (c * Math.pow(r, 2))
        + (b * r)
        + a);
  }

  public Translation3d conv2dTo3d(Translation2d translation2d, double z) {
    return new Translation3d(translation2d.getX(), translation2d.getY(), z);
  }

  // public double getYaw(PhotonCamera camera) {
  //   List<PhotonTrackedTarget> results = camera.getLatestResult().getTargets();
  //   boolean frontOfSubwoofer = false;

  //   for (PhotonTrackedTarget tag : results) {
  //     if (tag.getFiducialId() == 7 || tag.getFiducialId() == 4) {
  //       frontOfSubwoofer = true;
  //     }
  //   }

  //   SmartDashboard.putBoolean("front of subwoofer", frontOfSubwoofer);

  //   if (camera.getLatestResult().hasTargets()) {
  //     return camera.getLatestResult().getBestTarget().getYaw();
  //   }
  //   return 4079;
  // }

  public double getDistanceSubwoofer() {
    currentPose = getEstimatedGlobalPose().getTranslation();
    if (false) {
      return 687;
    }
    else {
      // 0.5, 5.5 coordinate for blue subwoofer
      // 16, 5.5 coordinate for red subwoofer
      if (DriverStation.getAlliance().get().equals(DriverStation.Alliance.Red)) {
        return Math.sqrt(Math.pow(currentPose.getX() - 16.5, 2) + Math.pow(currentPose.getY() - 5.5, 2)) + 0.4;
      } else {
        return Math.sqrt(Math.pow(currentPose.getX(), 2) + Math.pow(currentPose.getY() - 5.5, 2)) + 0.4;
      }

    }
  }

  public double getSubwooferYaw() {
    // currentPose = getEstimatedGlobalPose().getRotation().getAngle();
    return 180 - Math.toDegrees(getEstimatedGlobalPose().getRotation().getAngle());
    // if (false) {
    //   return 8033;
    // }
    // else {
    //   // 0.5, 5.5 coordinate for blue subwoofer
    //   // 16, 5.5 coordinate for red subwoofer
    //   if (DriverStation.getAlliance().get().equals(DriverStation.Alliance.Red)) {
    //     return Math.toDegrees(Math.atan2(currentPose.getY() - 5.5, currentPose.getX() - 16.5));
    //   } else {
    //     return Math.toDegrees(Math.atan2(currentPose.getY() - 5.5, currentPose.getX()));
    //   }
    // }
  }
}
