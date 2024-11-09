package frc.robot.subsystems;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.GlobalsValues.PhotonVisionConstants;
import frc.robot.utils.GlobalsValues.SwerveGlobalValues.BasePIDGlobal;

import java.util.List;
import java.util.Optional;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.MultiTargetPNPResult;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

/** The PhotonVision subsystem handles vision processing using PhotonVision cameras. */
public class Photonvision extends SubsystemBase {
  // PhotonVision cameras
  PhotonCamera camera = new PhotonCamera("Camera");

  // Pose estimator for determining the robot's position on the field
  PhotonPoseEstimator photonPoseEstimator;

  private Translation2d cameraTrans = new Translation2d(0.31, 0);

  // AprilTag field layout for the 2024 Crescendo field
  AprilTagFieldLayout aprilTagFieldLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2024Crescendo);

  // Transformation from the robot to the camera
  // TODO: Make function to convert Translation2d to Translation3d
  Transform3d CameraPos =
      new Transform3d(
          conv2dTo3d(cameraTrans, PhotonVisionConstants.CAMERA_ONE_HEIGHT_METER),
          new Rotation3d(0, Math.toRadians(360 - PhotonVisionConstants.CAMERA_ONE_ANGLE_DEG), Math.toRadians(180)));

  PhotonTrackedTarget target;
  boolean targetVisible = false;
  double targetYaw = -15.0;
  double targetPoseAmbiguity = 7157;
  double range = 0.0;


  double rangeToTarget = 0.0;

  List<PhotonPipelineResult> result;
  PhotonPipelineResult currentResult;

  boolean camTag = false;

  Translation3d currentPose;



  /** Constructs a new PhotonVision subsystem. */
  public Photonvision() {
    // Intialize result
    result = camera.getAllUnreadResults();
    photonPoseEstimator =
        new PhotonPoseEstimator(
            aprilTagFieldLayout,
            PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR,
            CameraPos);
  }
  /**
   * This method is called periodically by the scheduler. It updates the tracked targets and
   * displays relevant information on the SmartDashboard.
   */
  @Override
  public void periodic() {
    result = camera.getAllUnreadResults();
    if (result.isEmpty()) {
      return;
    }
    currentResult = result.get(0);

    photonPoseEstimator.update(currentResult);

    if (currentResult.hasTargets()) {
      target = currentResult.getBestTarget();
      targetPoseAmbiguity = target.getPoseAmbiguity();
      // SmartDashboard.putNumber("dist", dist);



      // SmartDashboard.putBoolean("front of subwoofer", frontOfSubwoofer);

      // if (camera.getLatestResult().hasTargets()) {
      //   return camera.getLatestResult().getBestTarget().getYaw();
      // }
      // return 4079;

      // if (result.getMultiTagResult().estimatedPose.isPresent) {
      //     Transform3d fieldToCamera = result.getMultiTagResult().estimatedPose.best;
      //     SmartDashboard.putNumber("field to camera", fieldToCamera.getX());
      // }
    } else {
      targetPoseAmbiguity = 7157;
    }

    if(BasePIDGlobal.TEST_MODE == true) {
      SmartDashboard.putNumber("photon yaw", targetYaw);
      SmartDashboard.putNumber("range target", rangeToTarget);
      SmartDashboard.putNumber("april tag distance", getDistanceSubwoofer());
      SmartDashboard.putNumber("april tag yaw", getSubwooferYaw());
      SmartDashboard.putNumber(" cam ambiguity", targetPoseAmbiguity);
      SmartDashboard.putBoolean("_targets", currentResult.hasTargets());
    }

    // boolean frontOfSubwoofer = false;

    for (PhotonTrackedTarget tag : currentResult.getTargets()) {
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
    if (currentResult.hasTargets())
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
    photonPoseEstimator.setReferencePose(prevEstimatedRobotPose);
    return photonPoseEstimator.update(currentResult);
  }

  public Transform3d getEstimatedGlobalPose() {
    if (currentResult.getMultiTagResult() != null) {
      Transform3d fieldToCamera = currentResult.getMultiTagResult().get().estimatedPose.best;
      return fieldToCamera;
    }
    // return photonPoseEstimator.getReferencePose();
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
    double r = getDistanceSubwoofer() + 0.6;
    double f = -1.39223; // power 5
    double e = 20.9711; // power 4
    double d = -122.485; // power 3
    double c = 342.783; // power 2
    double b = -447.743; // power 1
    double a = 230.409; // constant

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

  public double getDistanceSubwoofer() {
    currentPose = getEstimatedGlobalPose().getTranslation();
    if (false) {
      return 687;
    }
    else {
      // 0.5, 5.5 coordinate for blue subwoofer
      // 16, 5.5 coordinate for red subwoofer
      if (DriverStation.getAlliance().get().equals(DriverStation.Alliance.Red)) {
        return Math.sqrt(Math.pow(currentPose.getX() - 16.5, 2) + Math.pow(currentPose.getY() - 5.5, 2));
      } else {
        return Math.sqrt(Math.pow(currentPose.getX(), 2) + Math.pow(currentPose.getY() - 5.5, 2));
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