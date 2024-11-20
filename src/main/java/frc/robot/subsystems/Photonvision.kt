@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package frc.robot.subsystems

import edu.wpi.first.apriltag.AprilTagFieldLayout
import edu.wpi.first.apriltag.AprilTagFields
import edu.wpi.first.math.geometry.*
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj2.command.SubsystemBase
import frc.robot.utils.RobotParameters.PhotonVisionConstants
import frc.robot.utils.dash
import frc.robot.utils.to3D
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
  private var camera: PhotonCamera = PhotonCamera("Camera")

  // Pose estimator for determining the robot's position on the field
  private var photonPoseEstimator: PhotonPoseEstimator

  private val cameraTrans = Translation2d(0.31, 0.0)

  // AprilTag field layout for the 2024 Crescendo field
  private var aprilTagFieldLayout: AprilTagFieldLayout =
    AprilTagFieldLayout.loadField(AprilTagFields.k2024Crescendo)

  // Transformation from the robot to the camera
  private var cameraPos: Transform3d =
    Transform3d(
      cameraTrans.to3D(PhotonVisionConstants.CAMERA_ONE_HEIGHT_METER),
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
      PhotonPoseEstimator(aprilTagFieldLayout, PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR, cameraPos)
  }

  /**
   * This method is called periodically by the scheduler. It updates the tracked targets and
   * displays relevant information on the SmartDashboard.
   */
  override fun periodic() {
    result = camera.allUnreadResults
    currentResult = result.firstOrNull() ?: return

    photonPoseEstimator.update(currentResult)

    target = currentResult?.bestTarget
    targetPoseAmbiguity = target?.poseAmbiguity ?: 7157.0

    currentResult?.targets?.forEach { tag ->
      if (tag.fiducialId == 7 || tag.fiducialId == 4) {
        yaw = tag.yaw
      }
    }

    dash(
      "yaw to target" to yaw,
      "range target" to rangeToTarget,
      "april tag distance" to distanceSubwoofer,
      "april tag yaw" to subwooferYaw,
      "cam ambiguity" to targetPoseAmbiguity,
      "_targets" to (currentResult?.hasTargets() ?: false),
    )
  }

  fun hasTag(): Boolean = currentResult?.hasTargets() ?: false

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
    get() =
      currentResult?.multiTagResult?.get()?.estimatedPose?.best
        ?: Transform3d(0.0, 0.0, 0.0, Rotation3d())

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

      return (f * r.pow(5.0)) + (e * r.pow(4.0)) + (d * r.pow(3.0)) + (c * r.pow(2.0)) + (b * r) + a
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
