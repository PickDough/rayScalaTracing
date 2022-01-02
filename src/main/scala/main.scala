import camera.Camera
import vector.*
import ray.*
import hittable.*
import constants.*
import color.*
import geometry.sphere.Sphere
import material.lambertian.Lambertian
import material.metal.Metal

import java.io.{FileInputStream, PrintStream}

def rayColor(r: Ray, world: Hittable, depth: Int): Vec3 =
  if depth <= 0 then
    return Vec3.zero

  world.hit(r, 0.001, infinity) match {
    case Some(hitRecord) =>
      hitRecord.material.scatter(r, hitRecord) match
        case Some((scattered, color)) =>
          color*(rayColor(scattered, world, depth-1))
        case None =>
          Vec3(0.0, 0.0, 0.0)
    case None =>
      val t = (Vec3.unit(r.direction).y + 1.0) * 0.5
      Vec3(1, 1, 1) * (1.0-t) + Vec3(0.5, 0.7, 1.0) * t
  }


@main def rayTracer() =
  System.setOut( new PrintStream("image.ppm"))
  //Image
  val aspectRatio = 16.0 / 9.0
  val imageWidth = 600
  val imageHeight = (imageWidth / aspectRatio).toInt
  val samplesPerPixel = 100
  val maxDepth = 50

  //World
  val world = new HittableList()

  val materialGround = Lambertian(Vec3(0.8, 0.8, 0.0))
  val materialCenter = Lambertian(Vec3(0.7, 0.3, 0.3))
  val materialLeft = Metal(Vec3(0.8, 0.8, 0.8), 0.2)
  val materialRight = Metal(Vec3(0.8, 0.6, 0.2), 0.8)

  world.add(new Sphere(Vec3(0, -100.5, -1), 100, materialGround))
  world.add(new Sphere(Vec3(0, 0, -1), 0.5, materialCenter))
  world.add(new Sphere(Vec3(-1.0, 0, -1), 0.5, materialLeft))
  world.add(new Sphere(Vec3(1.0, 0, -1), 0.5, materialRight))

  //Camera
  val camera = new Camera()

  //Render
  print(s"P3\n $imageWidth $imageHeight \n255\n")

  for (j <- imageHeight - 1 to 0 by -1)
    System.err.print(s"\rScanlines remaining: $j")
    System.err.flush
    for (i <- 0 to imageWidth-1)
      var pixelColor = Vec3.zero

      for (s <- 0 to samplesPerPixel)
        val u = (i.toDouble + rand.nextDouble()) / (imageWidth - 1)
        val v = (j.toDouble + rand.nextDouble()) / (imageHeight - 1)

        val r = camera.getRay(u, v)
        pixelColor = pixelColor + rayColor(r, world, maxDepth)

      writeColor(pixelColor, samplesPerPixel)

  System.err.print(s"\nDone\n")



