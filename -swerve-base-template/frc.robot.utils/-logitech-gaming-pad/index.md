//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[LogitechGamingPad](index.md)

# LogitechGamingPad

[jvm]\
public class [LogitechGamingPad](index.md) extends XboxController

A class representing a Logitech Gaming Pad.

## Constructors

| | |
|---|---|
| [LogitechGamingPad](-logitech-gaming-pad.md) | [jvm]<br>public void[LogitechGamingPad](-logitech-gaming-pad.md)(intusbPort)<br>Constructor for the LogitechGamingPad. |

## Types

| Name | Summary |
|---|---|
| [Axis](-axis/index.md) | [jvm]<br>public enum [Axis](-axis/index.md)<br>Enum representing the axes on the gamepad. |
| [Button](-button/index.md) | [jvm]<br>public enum [Button](-button/index.md)<br>Enum representing the buttons on the gamepad. |
| [DPad](-d-pad/index.md) | [jvm]<br>public enum [DPad](-d-pad/index.md)<br>Enum representing the DPad directions. |
| [Trigger](-trigger/index.md) | [jvm]<br>public enum [Trigger](-trigger/index.md)<br>Enum representing the triggers on the gamepad. |

## Functions

| Name | Summary |
|---|---|
| [checkDPad](check-d-pad.md) | [jvm]<br>public boolean[checkDPad](check-d-pad.md)(intindex)<br>Checks if the DPad is pressed in a specific direction.<br>[jvm]<br>public boolean[checkDPad](check-d-pad.md)(doubleangle, booleaninDegrees)<br>Checks if the DPad is pressed at a specific angle. |
| [dPadIsPressed](d-pad-is-pressed.md) | [jvm]<br>public boolean[dPadIsPressed](d-pad-is-pressed.md)()<br>Checks if the DPad is pressed. |
| [getAButton](get-a-button.md) | [jvm]<br>public boolean[getAButton](get-a-button.md)() |
| [getBackButton](get-back-button.md) | [jvm]<br>public boolean[getBackButton](get-back-button.md)() |
| [getBButton](get-b-button.md) | [jvm]<br>public boolean[getBButton](get-b-button.md)() |
| [getDPad](get-d-pad.md) | [jvm]<br>public int[getDPad](get-d-pad.md)()<br>Gets the DPad value.<br>[jvm]<br>public double[getDPad](get-d-pad.md)(booleaninDegrees)<br>Gets the DPad value in degrees or radians. |
| [getLeftAnalogXAxis](get-left-analog-x-axis.md) | [jvm]<br>public double[getLeftAnalogXAxis](get-left-analog-x-axis.md)()<br>Gets the value of the left analog X axis. |
| [getLeftAnalogYAxis](get-left-analog-y-axis.md) | [jvm]<br>public double[getLeftAnalogYAxis](get-left-analog-y-axis.md)()<br>Gets the value of the left analog Y axis. |
| [getLeftTriggerValue](get-left-trigger-value.md) | [jvm]<br>public double[getLeftTriggerValue](get-left-trigger-value.md)()<br>Gets the value of the left trigger. |
| [getPOV](get-p-o-v.md) | [jvm]<br>public int[getPOV](get-p-o-v.md)(intpov) |
| [getRawAxis](get-raw-axis.md) | [jvm]<br>public double[getRawAxis](get-raw-axis.md)(intwhich) |
| [getRawButton](get-raw-button.md) | [jvm]<br>public boolean[getRawButton](get-raw-button.md)(intbutton) |
| [getRightAnalogXAxis](get-right-analog-x-axis.md) | [jvm]<br>public double[getRightAnalogXAxis](get-right-analog-x-axis.md)()<br>Gets the value of the right analog X axis. |
| [getRightAnalogYAxis](get-right-analog-y-axis.md) | [jvm]<br>public double[getRightAnalogYAxis](get-right-analog-y-axis.md)()<br>Gets the value of the right analog Y axis. |
| [getRightTriggerValue](get-right-trigger-value.md) | [jvm]<br>public double[getRightTriggerValue](get-right-trigger-value.md)()<br>Gets the value of the right trigger. |
| [getStartButton](get-start-button.md) | [jvm]<br>public boolean[getStartButton](get-start-button.md)() |
| [getThrottle](get-throttle.md) | [jvm]<br>public double[getThrottle](get-throttle.md)()<br>Gets the throttle value. |
| [getTwist](get-twist.md) | [jvm]<br>public double[getTwist](get-twist.md)()<br>Gets the twist value. |
| [getXButton](get-x-button.md) | [jvm]<br>public boolean[getXButton](get-x-button.md)() |
| [getYButton](get-y-button.md) | [jvm]<br>public boolean[getYButton](get-y-button.md)() |
| [setRumble](set-rumble.md) | [jvm]<br>public void[setRumble](set-rumble.md)(floatamount)<br>Sets the rumble amount for the gamepad. |
