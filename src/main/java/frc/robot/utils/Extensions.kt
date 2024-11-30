package frc.robot.utils

import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.geometry.Translation3d

/**
 * Extension function to convert a 2D translation to a 3D translation.
 *
 * @param z The z-coordinate for the 3D translation.
 * @return [Translation3d] The resulting 3D translation.
 * @receiver [Translation2d] The 2D translation to convert.
 */
fun Translation2d.to3D(z: Double): Translation3d = Translation3d(x, y, z)
