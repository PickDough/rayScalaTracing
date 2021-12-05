package object constants {
  val infinity = Double.PositiveInfinity
  val Pi = math.Pi
  val rand = scala.util.Random

  def deg2Rad(deg: Double) =
    deg * Pi / 180

  def clamp(x: Double, min: Double, max: Double) =
    if x < min then min
    else if x > max then max
    else x

}
