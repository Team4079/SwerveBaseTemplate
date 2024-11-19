package frc.robot.utils

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController

class LogitechGamingPad(usbPort: Int) : XboxController(usbPort) {
  private val gamepad = Joystick(usbPort)

  enum class DPad(val angle: Int) {
    UP(0),
    UP_RIGHT(45),
    RIGHT(90),
    DOWN_RIGHT(135),
    DOWN(180),
    DOWN_LEFT(225),
    LEFT(270),
    UP_LEFT(315),
  }

  enum class Button(val button: Int) {
    A(1),
    B(2),
    X(3),
    Y(4),
    LEFT_BUMPER(5),
    RIGHT_BUMPER(6),
    BACK(7),
    START(8),
  }

  enum class Axis(val axis: Int) {
    LEFT_ANALOG_X(0),
    LEFT_ANALOG_Y(1),
    RIGHT_ANALOG_X(4),
    RIGHT_ANALOG_Y(5),
  }

  enum class Trigger(val trigger: Int) {
    LEFT(2),
    RIGHT(3),
  }

  val leftTriggerValue: Double
    get() = gamepad.getRawAxis(Trigger.LEFT.trigger)

  val leftAnalogXAxis: Double
    get() = gamepad.getRawAxis(Axis.LEFT_ANALOG_X.axis)

  val leftAnalogYAxis: Double
    get() = gamepad.getRawAxis(Axis.LEFT_ANALOG_Y.axis)

  val rightTriggerValue: Double
    get() = gamepad.getRawAxis(Trigger.RIGHT.trigger)

  val rightAnalogXAxis: Double
    get() = gamepad.getRawAxis(Axis.RIGHT_ANALOG_X.axis)

  val rightAnalogYAxis: Double
    get() = gamepad.getRawAxis(Axis.RIGHT_ANALOG_Y.axis)

  override fun getAButton(): Boolean {
    return gamepad.getRawButton(Button.A.button)
  }

  override fun getBButton(): Boolean {
    return gamepad.getRawButton(Button.B.button)
  }

  override fun getXButton(): Boolean {
    return gamepad.getRawButton(Button.X.button)
  }

  override fun getYButton(): Boolean {
    return gamepad.getRawButton(Button.Y.button)
  }

  override fun getBackButton(): Boolean {
    return gamepad.getRawButton(Button.BACK.button)
  }

  override fun getStartButton(): Boolean {
    return gamepad.getRawButton(Button.START.button)
  }

  fun checkDPad(index: Int): Boolean {
    return if (0 <= index && index <= 7) (index * 45) == gamepad.pov else false
  }

  fun checkDPad(angle: Double, inDegrees: Boolean): Boolean {
    var angle = angle
    if (!inDegrees) angle = Math.toDegrees(angle)
    return angle.toInt() == gamepad.pov
  }

  val dPad: Int
    get() {
      val pov = gamepad.pov
      return if (pov == -1) pov else pov / 45
    }

  fun getDPad(inDegrees: Boolean): Double {
    return if (inDegrees) gamepad.pov.toDouble() else Math.toRadians(gamepad.pov.toDouble())
  }

  fun dPadIsPressed(): Boolean {
    return gamepad.pov != -1
  }

  fun setRumble(amount: Float) {
    gamepad.setRumble(RumbleType.kLeftRumble, amount.toDouble())
    gamepad.setRumble(RumbleType.kRightRumble, amount.toDouble())
  }

  override fun getRawAxis(which: Int): Double {
    return gamepad.getRawAxis(which)
  }

  override fun getRawButton(button: Int): Boolean {
    return gamepad.getRawButton(button)
  }

  override fun getPOV(pov: Int): Int {
    return gamepad.getPOV(pov)
  }

  val twist: Double
    get() = 0.0

  val throttle: Double
    get() = 0.0
}
