package frc.robot.commands;

import frc.robot.utils.Constants;
import frc.robot.utils.Constants.MotorConstants;
import frc.robot.subsystems.SwerveSubsystem;

import java.util.List;

// import com.pathplanner.lib.commands.PathPlannerAuto;
// import com.pathplanner.lib.path.PathPlannerTrajectory;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;

public class JaydenAuto extends SequentialCommandGroup {
    public JaydenAuto(SwerveSubsystem swerveSubsystem){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.MotorConstants.MAX_SPEED,
                    3)
                .setKinematics(Constants.SwerveConstants.kinematics);

        // An example trajectory to follow.  All units in meters.
        Trajectory exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(1,0), new Translation2d(1, 1)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(2, 1, new Rotation2d(0)),
                config);

        var thetaController =
            new ProfiledPIDController(
                0.1, 0, 0,new TrapezoidProfile.Constraints(MotorConstants.MAX_ANGULAR_SPEED, 3));
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand =
                new SwerveControllerCommand(
                exampleTrajectory,
                swerveSubsystem::getPose,
                Constants.SwerveConstants.kinematics,
                new PIDController(0.1, 0, 0),
                new PIDController(0.1, 0, 0),
                thetaController,
                swerveSubsystem::outputModuleStates,
                swerveSubsystem);


        addCommands(
            new InstantCommand(() -> swerveSubsystem.resetOdometry(exampleTrajectory.getInitialPose())),
            swerveControllerCommand
        );
    }
}