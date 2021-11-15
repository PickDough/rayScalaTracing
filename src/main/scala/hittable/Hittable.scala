package hittable

trait Hittable {
  def hit(r: ray.Ray, minT: Double, maxT : Double): Option[HitRecord]
}
