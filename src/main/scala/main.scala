import vector.*
import ray.Ray

import java.io.{FileInputStream, PrintStream}

def rayColor(r: Ray): Vec3 =
  val unitDirection = Vec3.unit(r.direction)
  val t = 0.5 * (unitDirection.y + 1.0)
  Vec3(1, 1, 1)*(1.0-t) + Vec3(0.5, 0.4, 1.0)*t

@main def rayTracer() =
  System.setOut( new PrintStream("image.ppm"))
  val aspectRatio = 16.0 / 9.0
  val imageWidth = 400
  val imageHeight = (imageWidth / aspectRatio).toInt

  val viewportHeight = 2.0
  val viewportWidth = aspectRatio * viewportHeight
  val focalLength = 1.0

  val origin = Vec3(0, 0, 0)
  val horizontal = Vec3(viewportWidth, 0, 0)
  val vertical = Vec3(0, viewportHeight, 0)
  val lowerLeftCorner = origin - horizontal / 2 - vertical / 2 - Vec3(0, 0, focalLength)

  print(s"P3\n $imageWidth $imageHeight \n255\n")

  for (j <- imageHeight - 1 to 0 by -1)
    System.err.print(s"\rScanlines remaining: $j")
    System.err.flush
    for (i <- 0 to imageWidth)
      val u = i.toDouble / (imageWidth - 1)
      val v = j.toDouble / (imageHeight - 1)


      val r = Ray(origin, lowerLeftCorner + horizontal * u + vertical * v - origin)

      val pixelColor = rayColor(r)

      color.write(pixelColor)

  System.err.print(s"\nDone\n")



