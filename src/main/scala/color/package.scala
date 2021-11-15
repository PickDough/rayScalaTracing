import vector.Vec3

package object color {
  def write(color: Vec3) =
    println(s" ${(255.999*color.x).toInt} ${(255.999*color.y).toInt} ${(255.999*color.z).toInt}")
}
