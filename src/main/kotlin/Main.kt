import kotlin.math.sqrt

fun hitSphere(center: Point3, radius: Double, r: Ray): Double {
    val oc = r.origin - center
    val a = r.direction dot r.direction
    val b = 2.0 * (oc dot r.direction)
    val c = (oc dot oc) - radius * radius
    val discriminant = b * b - 4 * a * c
    return if (discriminant < 0.0) {
        -1.0
    } else {
        (-b - sqrt(discriminant)) / (2.0 * a)
    }
}

fun rayColor(r: Ray): Color {
    var t = hitSphere(Point3(0.0, 0.0, -1.0), 0.5, r)
    if (t > 0.0) {
        val n = unitVector(r.at(t) - Vec3(0.0, 0.0, -1.0))
        return 0.5 * Color(n.x + 1.0, n.y + 1.0, n.z + 1.0)
    }
    val unitDirection = unitVector(r.direction)
    t = 0.5 * (unitDirection.y + 1.0)
    return (1.0 - t) * Color(1.0, 1.0, 1.0) +
            t * Color(0.5, 0.7, 1.0)
}

fun main() {

    // Image

    val aspectRatio = 16.0 / 9.0
    val imageWidth = 400
    val imageHeight = (imageWidth / aspectRatio).toInt()

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
            val pixelColor = rayColor(r)
            writeColor(System.out, pixelColor)
        }
    }

    System.err.println("\nDone.")
}
