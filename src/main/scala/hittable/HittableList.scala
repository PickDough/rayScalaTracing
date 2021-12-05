package hittable

import ray.Ray

import scala.collection.mutable.ListBuffer

class HittableList(var hittables: ListBuffer[Hittable] = ListBuffer.empty) extends Hittable {

  def add(hittable: Hittable) =
    hittables += hittable

  def clear() =
    hittables = ListBuffer.empty

  def hit(r: Ray, tMin: Double, tMax: Double): Option[HitRecord] =
    hittables
      .flatMap(_.hit(r, tMin, tMax))
      .minByOption(h => h.t)
}
