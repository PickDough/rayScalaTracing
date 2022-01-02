package geometry.sphere

import hittable.{HitRecord, Hittable}
import material.Material
import ray.Ray
import vector.Vec3

class Sphere(center: Vec3, radius: Double, material: Material) extends Hittable {
  override def hit(r: Ray, minT: Double, maxT: Double): Option[HitRecord] =
    val oc = r.origin - center
    val a = r.direction.lengthSquared
    val halfB = Vec3.dot(oc, r.direction)
    val c = oc.lengthSquared - radius * radius

    val discriminant = halfB * halfB - a * c

    def outOfBoundaries(root: Double): Boolean =
      root < minT || root > maxT

    if discriminant < 0 then None
    else
      val sqrtd = math.sqrt(discriminant)
      var root = (-halfB - sqrtd) / a

      if outOfBoundaries(root) then
        root = (-halfB + sqrtd) / a
        if outOfBoundaries(root) then
          None
        else
          Some(HitRecord(r.at(root), (r.at(root) - center) / radius, root, r, material))
      else
          Some(HitRecord(r.at(root), (r.at(root) - center) / radius, root, r, material))

}


