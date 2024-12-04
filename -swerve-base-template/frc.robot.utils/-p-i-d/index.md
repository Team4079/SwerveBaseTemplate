---
title: PID
---
//[SwerveBaseTemplate](../../../index.html)/[frc.robot.utils](../index.html)/[PID](index.html)



# PID



[jvm]\
open class [PID](index.html)

A class representing a PID controller.



## Constructors


| | |
|---|---|
| [PID](-p-i-d.html) | [jvm]<br>constructor(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Constructor for PID with P, I, and D values.<br>constructor(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), s: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Constructor for PID with P, I, D, F values and S value.<br>constructor(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Constructor for PID with P, I, D, and F values. |


## Properties


| Name | Summary |
|---|---|
| [d](d.html) | [jvm]<br>open var [d](d.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [error](error.html) | [jvm]<br>open var [error](error.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [f](f.html) | [jvm]<br>open var [f](f.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [i](i.html) | [jvm]<br>open var [i](i.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [integral](integral.html) | [jvm]<br>open var [integral](integral.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [output](output.html) | [jvm]<br>open var [output](output.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [p](p.html) | [jvm]<br>open var [p](p.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [previousError](previous-error.html) | [jvm]<br>open var [previousError](previous-error.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [s](s.html) | [jvm]<br>open var [s](s.html): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [setpoint](setpoint.html) | [jvm]<br>open var [setpoint](setpoint.html): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |


## Functions


| Name | Summary |
|---|---|
| [calculate](calculate.html) | [jvm]<br>open fun [calculate](calculate.html)(actual: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), setpoint: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)): [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)<br>Calculates the PID output based on the actual value and setpoint. |
| [resetI](reset-i.html) | [jvm]<br>open fun [resetI](reset-i.html)()<br>Resets the integral term. |
| [updatePID](update-p-i-d.html) | [jvm]<br>open fun [updatePID](update-p-i-d.html)(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>open fun [updatePID](update-p-i-d.html)(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html))<br>Updates the PID coefficients.<br>[jvm]<br>open fun [updatePID](update-p-i-d.html)(p: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), i: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), d: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), f: [Double](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html), s: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html))<br>Updates the PID coefficients and S value. |

