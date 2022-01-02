package material.metal

import vector.Vec3
import material.Material
import ray.Ray
import hittable.HitRecord

class Metal(val albedo: Vec3, val fuzziness: Double) extends Material {
  override def scatter(r: Ray, rec: HitRecord): Option[(Ray, Vec3)] =
    val reflected = Vec3.reflect(Vec3.unit(r.direction), rec.normal)
    val scattered = Ray(rec.p, reflected + Vec3.randomUnitVector*fuzziness)
    if Vec3.dot(scattered.direction, rec.normal) > 0 then
        Some(scattered, albedo)
    else
        None
}
