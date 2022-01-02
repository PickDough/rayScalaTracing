package material

import hittable.HitRecord
import vector.Vec3
import ray.Ray

abstract class Material {
  def scatter(r: Ray, rec: HitRecord): Option[(Ray, Vec3)]
}
