fun rayColor(r: Ray, world: Hittable, depth: Int): Color {
    val recRef = Reference(HitRecord.NONE)

    // If we've exceeded the ray bounce limit, no more light is gathered.
    if (depth <= 0) {
        return Color.ZERO
    }

    if (world.hit(r, 0.001, infinity, recRef)) {
        val rec = recRef.value
        val scattered = Reference(Ray.NONE)
        val attenuation = Reference(Color.ZERO)
        if (rec.mat.scatter(r, rec, attenuation, scattered))
            return attenuation.value * rayColor(
                scattered.value, world,
                depth - 1
            )
        return Color.ZERO
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
    val maxDepth = 50

    // World

    val world = HittableList()

    val materialGround = Lambertian(Color(0.8, 0.8, 0.0))
    val materialCenter = Lambertian(Color(0.1, 0.2, 0.5))
    val materialLeft = Dielectric(1.5)
    val materialRight = Metal(Color(0.8, 0.6, 0.2), 0.0)

    world.add(Sphere(Point3( 0.0, -100.5, -1.0), 100.0, materialGround))
    world.add(Sphere(Point3( 0.0,    0.0, -1.0),   0.5, materialCenter))
    world.add(Sphere(Point3(-1.0,    0.0, -1.0),   0.5, materialLeft))
    world.add(Sphere(Point3(-1.0,    0.0, -1.0), -0.45, materialLeft))
    world.add(Sphere(Point3( 1.0,    0.0, -1.0),   0.5, materialRight))

    // Camera
    val cam = Camera(
        Point3(-2.0, 2.0, 1.0),
        Point3(0.0, 0.0, -1.0),
        Vec3(0.0, 1.0, 0.0),
        20.0,
        aspectRatio
    )

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
                pixelColor += rayColor(r, world, maxDepth)
            }
            writeColor(System.out, pixelColor, samplesPerPixel)
        }
    }

    System.err.println("\nDone.")
}
