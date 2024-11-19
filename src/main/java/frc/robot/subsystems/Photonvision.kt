package frc.robot.subsystems

import edu.wpi.first.apriltag.AprilTagFieldLayout
import edu.wpi.first.apriltag.AprilTagFields
import edu.wpi.first.math.geometry.*
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.utils.GlobalsValues.PhotonVisionConstants
import frc.robot.utils.GlobalsValues.SwerveGlobalValues.BasePIDGlobal
import java.util.*
import kotlin.math.pow
import kotlin.math.sqrt
import org.photonvision.EstimatedRobotPose
import org.photonvision.PhotonCamera
import org.photonvision.PhotonPoseEstimator
import org.photonvision.PhotonPoseEstimator.PoseStrategy
import org.photonvision.targeting.PhotonPipelineResult
import org.photonvision.targeting.PhotonTrackedTarget

/** The PhotonVision subsystem handles vision processing using PhotonVision cameras. */
class Photonvision : SubsystemBase() {
  // PhotonVision cameras
  var camera: PhotonCamera = PhotonCamera("Camera")

  // Pose estimator for determining the robot's position on the field
  var photonPoseEstimator: PhotonPoseEstimator

  private val cameraTrans = Translation2d(0.31, 0.0)

  // AprilTag field layout for the 2024 Crescendo field
  var aprilTagFieldLayout: AprilTagFieldLayout =
    AprilTagFieldLayout.loadField(AprilTagFields.k2024Crescendo)

  // Transformation from the robot to the camera
  // TODO: Make function to convert Translation2d to Translation3d
  var CameraPos: Transform3d =
    Transform3d(
      conv2dTo3d(cameraTrans, PhotonVisionConstants.CAMERA_ONE_HEIGHT_METER),
      Rotation3d(
        0.0,
        Math.toRadians(360 - PhotonVisionConstants.CAMERA_ONE_ANGLE_DEG),
        Math.toRadians(180.0),
      ),
    )

  var target: PhotonTrackedTarget? = null
  var targetVisible: Boolean = false
  /**
   * Horizontal
   *
   * See
   * [NetworkTables API](https://docs.photonvision.org/en/latest/docs/additional-resources/nt-api.html#getting-target-information)
   */
  var yaw: Double = -15.0
  var targetPoseAmbiguity: Double = 7157.0
  var range: Double = 0.0

  var rangeToTarget: Double = 0.0

  var result: List<PhotonPipelineResult>
  var currentResult: PhotonPipelineResult? = null

  var camTag: Boolean = false

  var currentPose: Translation3d? = null

  /** Constructs a new PhotonVision subsystem. */
  init {
    // Intialize result
    result = camera.allUnreadResults
    photonPoseEstimator =
      PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, CameraPos)
  }

  /**
   * This method is called periodically by the scheduler. It updates the tracked targets and
   * displays relevant information on the SmartDashboard.
   */
  override fun periodic() {
    result = camera.allUnreadResults
    if (result.isEmpty()) {
      return
    }
    currentResult = result[0]

    photonPoseEstimator.update(currentResult)

    if (currentResult!!.hasTargets()) {
      val localTarget = currentResult!!.bestTarget
      target = localTarget
      targetPoseAmbiguity = localTarget.getPoseAmbiguity()
    } else {
      targetPoseAmbiguity = 7157.0
    }

    if (BasePIDGlobal.TEST_MODE == true) {
      SmartDashboard.putNumber("photon yaw", yaw)
      SmartDashboard.putNumber("range target", rangeToTarget)
      SmartDashboard.putNumber("april tag distance", distanceSubwoofer)
      SmartDashboard.putNumber("april tag yaw", subwooferYaw)
      SmartDashboard.putNumber(" cam ambiguity", targetPoseAmbiguity)
      SmartDashboard.putBoolean("_targets", currentResult!!.hasTargets())
    }

    for (tag in currentResult!!.getTargets()) {
      if (tag.getFiducialId() == 7 || tag.getFiducialId() == 4) {
        yaw = tag.getYaw()
      }
    }

    SmartDashboard.putNumber("yaw to target", yaw)
  }

  fun hasTag(): Boolean {
    return currentResult!!.hasTargets()
  }

  /**
   * Gets the estimated global pose of the robot.
   *
   * @param prevEstimatedRobotPose The previous estimated pose of the robot.
   * @return An Optional containing the estimated robot pose, or empty if no pose could be
   *   estimated.
   */
  fun getEstimatedGlobalPose(prevEstimatedRobotPose: Pose2d?): Optional<EstimatedRobotPose> {
    photonPoseEstimator.setReferencePose(prevEstimatedRobotPose)
    return photonPoseEstimator.update(currentResult)
  }

  val estimatedGlobalPose: Transform3d
    get() {
      if (currentResult!!.multiTagResult != null) {
        val fieldToCamera = currentResult!!.multiTagResult.get().estimatedPose.best
        return fieldToCamera
      }
      // return photonPoseEstimator.getReferencePose();
      return Transform3d(0.0, 0.0, 0.0, Rotation3d())
    }

  val pivotPosition: Double
    /**
     * Forward distance to target
     *
     * See
     * [NetworkTables API](https://docs.photonvision.org/en/latest/docs/additional-resources/nt-api.html#getting-target-information)
     */
    get() {
      // 10/14/2024 outside tuning
      // jayden why are you so bad at tuning
      // Desmos: https://www.desmos.com/calculator/naalukjxze
      val r = distanceSubwoofer + 0.6
      val f = -1.39223 // power 5
      val e = 20.9711 // power 4
      val d = -122.485 // power 3
      val c = 342.783 // power 2
      val b = -447.743 // power 1
      val a = 230.409 // constant

      return (((f * r.pow(5.0)) +
        (e * r.pow(4.0)) +
        (d * r.pow(3.0)) +
        (c * r.pow(2.0)) +
        (b * r) +
        a))
    }

  fun conv2dTo3d(translation2d: Translation2d, z: Double): Translation3d {
    return Translation3d(translation2d.x, translation2d.y, z)
  }

  val distanceSubwoofer: Double
    get() {
      currentPose = estimatedGlobalPose.translation
      val localCurrentPose = currentPose
      return if (localCurrentPose != null) {
        if (DriverStation.getAlliance().get() == DriverStation.Alliance.Red) {
          sqrt((localCurrentPose.x - 16.5).pow(2.0) + (localCurrentPose.y - 5.5).pow(2.0))
        } else {
          sqrt(localCurrentPose.x.pow(2.0) + (localCurrentPose.y - 5.5).pow(2.0))
        }
      } else {
        687.0
      }
    }

  val subwooferYaw: Double
    get() = // currentPose = getEstimatedGlobalPose().getRotation().getAngle();
    180 - Math.toDegrees(estimatedGlobalPose.rotation.angle)
}
