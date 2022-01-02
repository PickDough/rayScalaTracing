package vector

import constants.*

import scala.annotation.tailrec

case class Vec3(x: Double, y: Double, z: Double) {
  def +(t: Double) = Vec3(this.x + t, this.y + t, this.z + t)

  def -(t: Double) = this + (-t)

  def *(t: Double) = Vec3(this.x * t, this.y * t, this.z * t)

  def /(t: Double) = this * (1 / t)

  def unary_- = Vec3(-this.x, -this.y, -this.z)

  def +(that: Vec3) = Vec3(this.x + that.x, this.y + that.y, this.z + that.z)

  def -(that: Vec3) = this + (-that)

  def *(that: Vec3) = Vec3(this.x * that.x, this.y * that.y, this.z * that.z)
  
  def length = math.sqrt(lengthSquared)
  
  def lengthSquared = x*x + y*y + z*z

  def operator_@(that: Vec3) = Vec3(
    this.y * that.z + this.z * that.y,
    this.z * that.x + this.x * that.z,
    this.x * that.y + this.y * that.x)

  override def toString: String = s"$x $y $z"
}

object Vec3 {
  def dot(v: Vec3, e: Vec3) =
    v.x * e.x + v.y * e.y + v.z * e.z

  def unit(v: Vec3) =
    v / v.length

  def zero =
    Vec3(0.0, 0.0, 0.0)

  def one =
    Vec3(1.0, 1.0, 1.0)

  def random =
    Vec3(rand.nextDouble(), rand.nextDouble(), rand.nextDouble())

  def between(min: Double, max: Double) =
    Vec3(rand.between(min, max), rand.between(min, max), rand.between(min, max))

  @tailrec
  def randomInUnitSpehere: Vec3 =
      val p = between(-1.0, 1.0)
      if p.lengthSquared < 1.0 then
        p
      else
        randomInUnitSpehere

  def randomUnitVector: Vec3 =
    unit(randomInUnitSpehere)

  def isNearZero(v: Vec3) =
    val s = 0.0000001
    (v.x < s && v.y < s && v.z < s)

  def reflect(v: Vec3, n: Vec3) = 
    v - n*Vec3.dot(v, n)*2
}