package frc.robot.utils;

public class PID {
	public double p;
    public double i;
    public double d;
    public double f;
    public int s;

    public PID(double p, double i, double d){
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public PID(double p, double i, double d, double f, int s) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = s;
    }
    public PID(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = -1;
    }

    public void updatePID(double p, double i, double d, double f, int s) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = s;
    }

    public void updatePID(double p, double i, double d, double f) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.s = -1;
    }

	public void updatePID(double p, double i, double d) {
		this.p = p;
		this.i = i;
		this.d = d;
		this.f = 0;
		this.s = -1;
	}
	double previous_error, integral = 0;
	double setpoint;
	double error;
	private double output;

	public double calculate(double actual, double setpoint) {
		error = setpoint - actual; // Error = Target - Actual
		this.integral += (error * .02); // Integral is increased by the error*time (which is .02 seconds using normal
		final double derivative = (error - this.previous_error) / .02;
		this.output = p * error + i * this.integral + d * derivative;
		this.previous_error = error;
		return output;
	}

	public void setSetpoint(double setpoint) {
		this.setpoint = setpoint;
	}

	public double getOutput() {
		return output;
	}
    
	public void resetI() {
		this.integral = 0;
	}

}