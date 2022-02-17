fun rayColor(r: Ray, world: Hittable): Color {
    val recRef = Reference(HitRecord.NONE)
    if (world.hit(r, 0.0, infinity, recRef)) {
        val rec = recRef.value
        val target = rec.p + rec.normal + randomInUnitSphere()
        return 0.5 * rayColor(Ray(rec.p, target - rec.p), world)
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
    val samplesPerPixel = 100

    // World

    val world = HittableList()
    world.add(Sphere(Point3(0.0, 0.0, -1.0), 0.5))
    world.add(Sphere(Point3(0.0, -100.5, -1.0), 100.0))

    // Camera
    val cam = Camera()

    // Render

    println("P3\n$imageWidth $imageHeight\n255")

    for (j in (imageHeight - 1) downTo 0) {
        System.err.printf("\rScanlines remaining: %3d", j)
        for (i in 0 until imageWidth) {
            val pixelColor = Color(0.0, 0.0, 0.0)
            for (s in 0 until samplesPerPixel) {
                val u = (i + randomDouble()) / (imageWidth - 1)
                val v = (j + randomDouble()) / (imageHeight - 1)
                val r = cam.getRay(u, v)
                pixelColor += rayColor(r, world)
            }
            writeColor(System.out, pixelColor, samplesPerPixel)
        }
    }

    System.err.println("\nDone.")
}
