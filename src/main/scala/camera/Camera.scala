package camera

import vector.Vec3
import ray.Ray

class Camera(aspectRatio: Double = 16.0/9.0, viewportHeight: Double = 2.0, focalLength: Double = 1.0, origin: Vec3 = Vec3.zero) {
  val viewportWidth = aspectRatio * viewportHeight
  private val horizontal = Vec3(viewportWidth, 0, 0)
  private val vertical = Vec3(0, viewportHeight, 0)
  private val lowerLeftCorner = origin - horizontal / 2 - vertical / 2 - Vec3(0, 0, focalLength)

  def getRay(u: Double, v: Double) =
    Ray(origin, lowerLeftCorner + horizontal*u + vertical*v - origin)
}
