fun rayColor(r: Ray, world: Hittable): Color {
    val recRef = Reference(HitRecord.NONE)
    if (world.hit(r, 0.0, infinity, recRef)) {
        val rec = recRef.value
        return 0.5 * (rec.normal + Color(1.0, 1.0, 1.0))
    }
    val unitDirection = unitVector(r.direction)
    val t = 0.5 * (unitDirection.y + 1.0)
    return (1.0 - t) * Color(1.0, 1.0, 1.0) +
            t * Color(0.5, 0.7, 1.0)
}

fun main() {

    // Image

    val aspectRatio = 16.0 / 9.0
    val imageWidth = 400
    val imageHeight = (imageWidth / aspectRatio).toInt()

    // World

    val world = HittableList()
    world.add(Sphere(Point3(0.0, 0.0, -1.0), 0.5))
    world.add(Sphere(Point3(0.0, -100.5, -1.0), 100.0))

    // Camera

    val viewportHeight = 2.0
    val viewportWidth = aspectRatio * viewportHeight
    val focalLength = 1.0

    val origin = Point3(0.0, 0.0, 0.0)
    val horizontal = Vec3(viewportWidth, 0.0, 0.0)
    val vertical = Vec3(0.0, viewportHeight, 0.0)
    val lowerLeftCorner = origin - horizontal / 2.0 - vertical / 2.0 -
            Vec3(0.0, 0.0, focalLength)

    // Render

    println("P3\n$imageWidth $imageHeight\n255")

    for (j in (imageHeight - 1) downTo 0) {
        System.err.printf("\rScanlines remaining: %3d", j)
        for (i in 0 until imageWidth) {
            val u = i.toDouble() / (imageWidth - 1)
            val v = j.toDouble() / (imageHeight - 1)
            val r = Ray(
                origin,
                lowerLeftCorner + u * horizontal + v * vertical - origin
            )
            val pixelColor = rayColor(r, world)
            writeColor(System.out, pixelColor)
        }
    }

    System.err.println("\nDone.")
}
