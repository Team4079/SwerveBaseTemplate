//[SwerveBaseTemplate](../../../index.md)/[frc.robot.utils](../index.md)/[PID](index.md)

# PID

[jvm]\
open class [PID](index.md)

A class representing a PID controller.

## Constructors

| | |
|---|---|
| [PID](-p-i-d.md) | [jvm]<br>constructor(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Constructor for PID with P, I, and D values.<br>constructor(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), s: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Constructor for PID with P, I, D, F values and S value.<br>constructor(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Constructor for PID with P, I, D, and F values. |

## Properties

| Name | Summary |
|---|---|
| [d](d.md) | [jvm]<br>open var [d](d.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [error](error.md) | [jvm]<br>open var [error](error.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [f](f.md) | [jvm]<br>open var [f](f.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [i](i.md) | [jvm]<br>open var [i](i.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [integral](integral.md) | [jvm]<br>open var [integral](integral.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [output](output.md) | [jvm]<br>open var [output](output.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [p](p.md) | [jvm]<br>open var [p](p.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [previousError](previous-error.md) | [jvm]<br>open var [previousError](previous-error.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [s](s.md) | [jvm]<br>open var [s](s.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [setpoint](setpoint.md) | [jvm]<br>open var [setpoint](setpoint.md): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

## Functions

| Name | Summary |
|---|---|
| [calculate](calculate.md) | [jvm]<br>open fun [calculate](calculate.md)(actual: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), setpoint: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates the PID output based on the actual value and setpoint. |
| [resetI](reset-i.md) | [jvm]<br>open fun [resetI](reset-i.md)()<br>Resets the integral term. |
| [updatePID](update-p-i-d.md) | [jvm]<br>open fun [updatePID](update-p-i-d.md)(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>open fun [updatePID](update-p-i-d.md)(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Updates the PID coefficients.<br>[jvm]<br>open fun [updatePID](update-p-i-d.md)(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), s: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Updates the PID coefficients and S value. |
