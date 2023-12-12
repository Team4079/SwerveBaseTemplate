// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot;

// import java.util.function.Consumer;

// import com.pathplanner.lib.PathPlanner;
// import com.pathplanner.lib.PathPlannerTrajectory;
// import com.pathplanner.lib.commands.PPSwerveControllerCommand;

// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.kinematics.SwerveModuleState;
// import frc.robot.subsystems.SwerveSubsystem;
// import frc.robot.utils.Constants.MotorConstants;
// import frc.robot.utils.Constants.SwerveConstants;

// /** Add your docs here. */
// public class PathLoader {
//     static PIDController x = new PIDController(0.0, 0.0, 0.0);
//     static PIDController y = new PIDController(0.0, 0.0, 0.0);
//     static PIDController theta = new PIDController(0.0, 0.0, 0.0);

//     public static PathPlannerTrajectory getPath(String path) {
//         return PathPlanner.loadPath(
//                 path,
//                 2,
//                 1,
//                 false);
//     }

//     public static Pose2d getPathInitial(String name) {
//         Pose2d newPose = getPath(name).sample(0).poseMeters;
//         newPose = new Pose2d(-newPose.getX(), newPose.getY(), newPose.getRotation());
//         return newPose;
//     }

//     // public static PPSwerveControllerCommand loadPath(String name, SwerveSubsystem swerveSubsystem) {
//     //     Consumer<SwerveModuleState[]> consumer = a -> swerveSubsystem.setAutonSwerveModuleStates(a);
//     //     return new PPSwerveControllerCommand(
//     //             getPath(name),
//     //             swerveSubsystem::getOdoPose,
//     //             SwerveConstants.kinematics,
//     //             x,
//     //             y,
//     //             theta,
//     //             consumer,
//     //             swerveSubsystem);
//     // }
// }