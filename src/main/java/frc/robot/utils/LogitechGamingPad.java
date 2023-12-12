package frc.robot.utils;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class LogitechGamingPad extends GenericHID {

  private Joystick gamepad;

  private static final int LEFT_ANALOG_X_AXIS = 0;
  private static final int LEFT_ANALOG_Y_AXIS = 1;
  private static final int RIGHT_ANALOG_X_AXIS = 4;
  private static final int RIGHT_ANALOG_Y_AXIS = 5;

  private static final int LEFT_BUMPER = 5;
  private static final int RIGHT_BUMPER = 6;
  private static final int LEFT_TRIGGER_BUTTON = 2;
  private static final int RIGHT_TRIGGER_BUTTON = 3;
  private static final int A_BUTTON = 1;
  private static final int B_BUTTON = 2;
  private static final int X_BUTTON = 3;
  private static final int Y_BUTTON = 4;
  private static final int BACK_BUTTON = 7;
  private static final int START_BUTTON = 8;

  public LogitechGamingPad(int usbPort) {
    super(0);
    gamepad = new Joystick(usbPort);
  }

  public boolean getLeftBumper() {
    return gamepad.getRawButton(LEFT_BUMPER);
  }

  public double getLeftTriggerValue() {
    return gamepad.getRawAxis(LEFT_TRIGGER_BUTTON);
  }

  public double getLeftAnalogXAxis() {
    return gamepad.getRawAxis(LEFT_ANALOG_X_AXIS);
  }

  public double getLeftAnalogYAxis() {
    return gamepad.getRawAxis(LEFT_ANALOG_Y_AXIS);
  }

  public boolean getRightBumper() {
    return gamepad.getRawButton(RIGHT_BUMPER);
  }

  public double getRightTriggerValue() {
    return gamepad.getRawAxis(RIGHT_TRIGGER_BUTTON);
  }

  public double getRightAnalogXAxis() {
    return gamepad.getRawAxis(RIGHT_ANALOG_X_AXIS);
  }

  public double getRightAnalogYAxis() {
    return gamepad.getRawAxis(RIGHT_ANALOG_Y_AXIS);
  }

  public boolean getAButton() {
    return gamepad.getRawButton(A_BUTTON);
  }

  public boolean getBButton() {
    return gamepad.getRawButton(B_BUTTON);
  }

  public boolean getXButton() {
    return gamepad.getRawButton(X_BUTTON);
  }

  public boolean getYButton() {
    return gamepad.getRawButton(Y_BUTTON);
  }

  public boolean getBackButton() {
    return gamepad.getRawButton(BACK_BUTTON);
  }

  public boolean getStartButton() {
    return gamepad.getRawButton(START_BUTTON);
  }

  public boolean checkDPad(int index) {
    if (0 <= index && index <= 7)
      return (index * 45) == gamepad.getPOV();
    else
      return false;
  }

  public boolean checkDPad(double angle, boolean inDegrees) {
    if (!inDegrees)
      angle = Math.toDegrees(angle);
    return (int) angle == gamepad.getPOV();
  }

  public int getDPad() {
    int pov = gamepad.getPOV();
    if (pov == -1)
      return pov;
    else
      return pov / 45;
  }

  public double getDPad(boolean inDegrees) {
    if (inDegrees)
      return gamepad.getPOV();
    else
      return Math.toRadians(gamepad.getPOV());
  }

  public boolean dPadIsPressed() {
    return gamepad.getPOV() != -1;
  }

  public void setRumble(float amount) {
    gamepad.setRumble(RumbleType.kLeftRumble, amount);
    gamepad.setRumble(RumbleType.kRightRumble, amount);
  }

  @Override
  public double getRawAxis(int which) {
    return gamepad.getRawAxis(which);
  }

  @Override
  public boolean getRawButton(int button) {
    return gamepad.getRawButton(button);
  }

  @Override
  public int getPOV(int pov) {
    return gamepad.getPOV(pov);
  }

  public double getTwist() {
    return 0;
  }

  public double getThrottle() {
    return 0;
  }
}