package hittable

import material.Material
import ray.Ray
import vector.Vec3

case class HitRecord(p: Vec3, normal: Vec3, t: Double, frontFace: Boolean = false, material: Material) {

}

object HitRecord {
  def apply(p: Vec3, normal: Vec3, t: Double, ray: Ray, material: Material): HitRecord =
    val frontFace = Vec3.dot(ray.direction, normal) < 0
    if frontFace then
      new HitRecord(p, normal, t, frontFace, material)
    else
      new HitRecord(p, -normal, t, frontFace, material)
}
