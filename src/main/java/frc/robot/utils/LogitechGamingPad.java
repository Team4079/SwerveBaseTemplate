package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;

public class LogitechGamingPad extends XboxController {

  private Joystick gamepad;

  public enum DPad {
    UP(0), UP_RIGHT(45), RIGHT(90), DOWN_RIGHT(135), DOWN(180), DOWN_LEFT(225), LEFT(270), UP_LEFT(315);

    private final int angle;

    DPad(int angle) {
      this.angle = angle;
    }

    public int getAngle() {
      return angle;
    }
  }

  public enum Button {
    A(1), B(2), X(3), Y(4), LEFT_BUMPER(5), RIGHT_BUMPER(6), BACK(7), START(8);

    private final int button;

    Button(int button) {
      this.button = button;
    }

    public int getButton() {
      return button;
    }
  }

  public enum Axis {
    LEFT_ANALOG_X(0), LEFT_ANALOG_Y(1), RIGHT_ANALOG_X(4), RIGHT_ANALOG_Y(5);

    private final int axis;

    Axis(int axis) {
      this.axis = axis;
    }

    public int getAxis() {
      return axis;
    }
  }

  public enum Trigger {
    LEFT(2), RIGHT(3);

    private final int trigger;

    Trigger(int trigger) {
      this.trigger = trigger;
    }

    public int getTrigger() {
      return trigger;
    }
  }

  // private static final int LEFT_ANALOG_X_AXIS = 0;
  // private static final int LEFT_ANALOG_Y_AXIS = 1;
  // private static final int RIGHT_ANALOG_X_AXIS = 4;
  // private static final int RIGHT_ANALOG_Y_AXIS = 5;

  // private static final int LEFT_BUMPER = 5;
  // private static final int RIGHT_BUMPER = 6;
  // private static final int LEFT_TRIGGER_BUTTON = 2;
  // private static final int RIGHT_TRIGGER_BUTTON = 3;
  // private static final int A_BUTTON = 1;
  // private static final int B_BUTTON = 2;
  // private static final int X_BUTTON = 3;
  // private static final int Y_BUTTON = 4;
  // private static final int BACK_BUTTON = 7;
  // private static final int START_BUTTON = 8;

  public LogitechGamingPad(int usbPort) {
    super(usbPort);
    gamepad = new Joystick(usbPort);
  }

  public boolean getLeftBumper() {
    return gamepad.getRawButton(Button.LEFT_BUMPER.getButton());
  }

  public double getLeftTriggerValue() {
    return gamepad.getRawAxis(Trigger.LEFT.getTrigger());
  }

  public double getLeftAnalogXAxis() {
    return gamepad.getRawAxis(Axis.LEFT_ANALOG_X.getAxis());
  }

  public double getLeftAnalogYAxis() {
    return gamepad.getRawAxis(Axis.LEFT_ANALOG_Y.getAxis());
  }

  public boolean getRightBumper() {
    return gamepad.getRawButton(Button.RIGHT_BUMPER.getButton());
  }

  public double getRightTriggerValue() {
    return gamepad.getRawAxis(Trigger.RIGHT.getTrigger());
  }

  public double getRightAnalogXAxis() {
    return gamepad.getRawAxis(Axis.RIGHT_ANALOG_X.getAxis());
  }

  public double getRightAnalogYAxis() {
    return gamepad.getRawAxis(Axis.RIGHT_ANALOG_Y.getAxis());
  }

  public boolean getAButton() {
    return gamepad.getRawButton(Button.A.getButton());
  }

  public boolean getBButton() {
    return gamepad.getRawButton(Button.B.getButton());
  }

  public boolean getXButton() {
    return gamepad.getRawButton(Button.X.getButton());
  }

  public boolean getYButton() {
    return gamepad.getRawButton(Button.Y.getButton());
  }

  public boolean getBackButton() {
    return gamepad.getRawButton(Button.BACK.getButton());
  }

  public boolean getStartButton() {
    return gamepad.getRawButton(Button.START.getButton());
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