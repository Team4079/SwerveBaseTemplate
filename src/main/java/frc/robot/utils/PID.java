package frc.robot.utils;

/** A class representing a PID controller. */
public class PID {
  private double p;
  private double i;
  private double d;
  private double f = 0.0;
  private int s = 0;

  private double previousError = 0.0;
  private double integral = 0.0;
  private double setpoint = 0.0;
  private double error = 0.0;
  private double output = 0.0;

  /**
   * Constructor for PID with P, I, and D values.
   *
   * @param p The proportional coefficient.
   * @param i The integral coefficient.
   * @param d The derivative coefficient.
   */
  public PID(double p, double i, double d) {
    this.p = p;
    this.i = i;
    this.d = d;
  }

  /**
   * Constructor for PID with P, I, D, F values and S value.
   *
   * @param p The proportional coefficient.
   * @param i The integral coefficient.
   * @param d The derivative coefficient.
   * @param f The feedforward coefficient.
   * @param s The S value.
   */
  public PID(double p, double i, double d, double f, int s) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.f = f;
    this.s = s;
  }

  /**
   * Constructor for PID with P, I, D, and F values.
   *
   * @param p The proportional coefficient.
   * @param i The integral coefficient.
   * @param d The derivative coefficient.
   * @param f The feedforward coefficient.
   */
  public PID(double p, double i, double d, double f) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.f = f;
    this.s = -1;
  }

  /**
   * Updates the PID coefficients and S value.
   *
   * @param p The proportional coefficient.
   * @param i The integral coefficient.
   * @param d The derivative coefficient.
   * @param f The feedforward coefficient.
   * @param s The S value.
   */
  public void updatePID(double p, double i, double d, double f, int s) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.f = f;
    this.s = s;
  }

  /**
   * Updates the PID coefficients.
   *
   * @param p The proportional coefficient.
   * @param i The integral coefficient.
   * @param d The derivative coefficient.
   * @param f The feedforward coefficient.
   */
  public void updatePID(double p, double i, double d, double f) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.f = f;
    this.s = -1;
  }

  /**
   * Updates the PID coefficients.
   *
   * @param p The proportional coefficient.
   * @param i The integral coefficient.
   * @param d The derivative coefficient.
   */
  public void updatePID(double p, double i, double d) {
    this.p = p;
    this.i = i;
    this.d = d;
    this.f = 0.0;
    this.s = -1;
  }

  /**
   * Calculates the PID output based on the actual value and setpoint.
   *
   * @param actual The actual value.
   * @param setpoint The target setpoint.
   * @return The PID output.
   */
  public double calculate(double actual, double setpoint) {
    this.error = setpoint - actual; // Error = Target - Actual
    this.integral +=
        (error
            * 0.02); // Integral is increased by the error*time (which is 0.02 seconds using normal
    double derivative = (error - this.previousError) / 0.02;
    this.output = p * error + i * this.integral + d * derivative;
    this.previousError = error;
    return output;
  }

  /** Resets the integral term. */
  public void resetI() {
    this.integral = 0.0;
  }

  // Getters and setters for the PID coefficients and other fields
  public double getP() {
    return p;
  }

  public void setP(double p) {
    this.p = p;
  }

  public double getI() {
    return i;
  }

  public void setI(double i) {
    this.i = i;
  }

  public double getD() {
    return d;
  }

  public void setD(double d) {
    this.d = d;
  }

  public double getF() {
    return f;
  }

  public void setF(double f) {
    this.f = f;
  }

  public int getS() {
    return s;
  }

  public void setS(int s) {
    this.s = s;
  }

  public double getPreviousError() {
    return previousError;
  }

  public void setPreviousError(double previousError) {
    this.previousError = previousError;
  }

  public double getIntegral() {
    return integral;
  }

  public void setIntegral(double integral) {
    this.integral = integral;
  }

  public double getSetpoint() {
    return setpoint;
  }

  public void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
  }

  public double getError() {
    return error;
  }

  public void setError(double error) {
    this.error = error;
  }

  public double getOutput() {
    return output;
  }

  public void setOutput(double output) {
    this.output = output;
  }
}
