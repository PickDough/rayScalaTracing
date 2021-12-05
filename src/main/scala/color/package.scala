import vector.Vec3
import constants.clamp

package object color {
  def writeColor(color: Vec3, samples: Int) =
    val r = clamp(math.sqrt(color.x / samples), 0, 0.9999)
    val g = clamp(math.sqrt(color.y / samples), 0, 0.9999)
    val b = clamp(math.sqrt(color.z / samples), 0, 0.9999)

    println(s" ${(256*r).toInt} ${(256*g).toInt} ${(256*b).toInt}")
}
