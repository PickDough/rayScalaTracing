package material.lambertian

import hittable.HitRecord
import material.Material
import ray.Ray
import vector.Vec3

class Lambertian(val albedo: Vec3) extends Material {
  override def scatter(r: Ray, rec: HitRecord): Option[(Ray, Vec3)] =
    val scatterDirection = rec.normal + Vec3.randomUnitVector
    if Vec3.isNearZero(scatterDirection) then
      Some((Ray(rec.p, rec.normal), albedo))
    else
      Some((Ray(rec.p, scatterDirection), albedo))
}
