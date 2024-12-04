//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[PID](index.md)

# PID

[jvm]\
public class [PID](index.md)

A class representing a PID controller.

## Constructors

| | |
|---|---|
| [PID](-p-i-d.md) | [jvm]<br>public void[PID](-p-i-d.md)(doublep, doublei, doubled)<br>Constructor for PID with P, I, and D values.<br>public void[PID](-p-i-d.md)(doublep, doublei, doubled, doublef, ints)<br>Constructor for PID with P, I, D, F values and S value.<br>public void[PID](-p-i-d.md)(doublep, doublei, doubled, doublef)<br>Constructor for PID with P, I, D, and F values. |

## Properties

| Name | Summary |
|---|---|
| [d](index.md#970337878%2FProperties%2F-1216412040) | [jvm]<br>public double[d](index.md#970337878%2FProperties%2F-1216412040) |
| [error](index.md#-63949134%2FProperties%2F-1216412040) | [jvm]<br>public double[error](index.md#-63949134%2FProperties%2F-1216412040) |
| [f](index.md#1032377492%2FProperties%2F-1216412040) | [jvm]<br>public double[f](index.md#1032377492%2FProperties%2F-1216412040) |
| [i](index.md#1125436913%2FProperties%2F-1216412040) | [jvm]<br>public double[i](index.md#1125436913%2FProperties%2F-1216412040) |
| [integral](index.md#-1156915596%2FProperties%2F-1216412040) | [jvm]<br>public double[integral](index.md#-1156915596%2FProperties%2F-1216412040) |
| [output](index.md#-1416826497%2FProperties%2F-1216412040) | [jvm]<br>public double[output](index.md#-1416826497%2FProperties%2F-1216412040) |
| [p](index.md#1342575562%2FProperties%2F-1216412040) | [jvm]<br>public double[p](index.md#1342575562%2FProperties%2F-1216412040) |
| [previousError](index.md#-1284708727%2FProperties%2F-1216412040) | [jvm]<br>public double[previousError](index.md#-1284708727%2FProperties%2F-1216412040) |
| [s](index.md#1435634983%2FProperties%2F-1216412040) | [jvm]<br>public int[s](index.md#1435634983%2FProperties%2F-1216412040) |
| [setpoint](index.md#-1371558926%2FProperties%2F-1216412040) | [jvm]<br>public double[setpoint](index.md#-1371558926%2FProperties%2F-1216412040) |

## Functions

| Name | Summary |
|---|---|
| [calculate](calculate.md) | [jvm]<br>public double[calculate](calculate.md)(doubleactual, doublesetpoint)<br>Calculates the PID output based on the actual value and setpoint. |
| [getD](get-d.md) | [jvm]<br>public double[getD](get-d.md)() |
| [getError](get-error.md) | [jvm]<br>public double[getError](get-error.md)() |
| [getF](get-f.md) | [jvm]<br>public double[getF](get-f.md)() |
| [getI](get-i.md) | [jvm]<br>public double[getI](get-i.md)() |
| [getIntegral](get-integral.md) | [jvm]<br>public double[getIntegral](get-integral.md)() |
| [getOutput](get-output.md) | [jvm]<br>public double[getOutput](get-output.md)() |
| [getP](get-p.md) | [jvm]<br>public double[getP](get-p.md)() |
| [getPreviousError](get-previous-error.md) | [jvm]<br>public double[getPreviousError](get-previous-error.md)() |
| [getS](get-s.md) | [jvm]<br>public int[getS](get-s.md)() |
| [getSetpoint](get-setpoint.md) | [jvm]<br>public double[getSetpoint](get-setpoint.md)() |
| [resetI](reset-i.md) | [jvm]<br>public void[resetI](reset-i.md)()<br>Resets the integral term. |
| [setD](set-d.md) | [jvm]<br>public void[setD](set-d.md)(doubled) |
| [setError](set-error.md) | [jvm]<br>public void[setError](set-error.md)(doubleerror) |
| [setF](set-f.md) | [jvm]<br>public void[setF](set-f.md)(doublef) |
| [setI](set-i.md) | [jvm]<br>public void[setI](set-i.md)(doublei) |
| [setIntegral](set-integral.md) | [jvm]<br>public void[setIntegral](set-integral.md)(doubleintegral) |
| [setOutput](set-output.md) | [jvm]<br>public void[setOutput](set-output.md)(doubleoutput) |
| [setP](set-p.md) | [jvm]<br>public void[setP](set-p.md)(doublep) |
| [setPreviousError](set-previous-error.md) | [jvm]<br>public void[setPreviousError](set-previous-error.md)(doublepreviousError) |
| [setS](set-s.md) | [jvm]<br>public void[setS](set-s.md)(ints) |
| [setSetpoint](set-setpoint.md) | [jvm]<br>public void[setSetpoint](set-setpoint.md)(doublesetpoint) |
| [updatePID](update-p-i-d.md) | [jvm]<br>public void[updatePID](update-p-i-d.md)(doublep, doublei, doubled)<br>public void[updatePID](update-p-i-d.md)(doublep, doublei, doubled, doublef)<br>Updates the PID coefficients.<br>[jvm]<br>public void[updatePID](update-p-i-d.md)(doublep, doublei, doubled, doublef, ints)<br>Updates the PID coefficients and S value. |
