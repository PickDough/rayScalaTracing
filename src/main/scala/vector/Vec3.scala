package vector

case class Vec3(x: Double, y: Double, z: Double) {
  def +(t: Double) = Vec3(this.x + t, this.y + t, this.z + t)

  def -(t: Double) = this + (-t)

  def *(t: Double) = Vec3(this.x * t, this.y * t, this.z * t)

  def /(t: Double) = this * (1 / t)

  def unary_- = Vec3(-this.x, -this.y, -this.z)

  def +(that: Vec3) = Vec3(this.x + that.x, this.y + that.y, this.z + that.z)

  def -(that: Vec3) = this + (-that)
  
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
    v.x*e.x + v.y*e.y +v.z*e.z
    
  def unit(v: Vec3) =
    v / v.length
}