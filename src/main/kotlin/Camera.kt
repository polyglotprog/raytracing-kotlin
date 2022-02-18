import kotlin.math.tan

data class Camera(
    var origin: Point3,
    var lowerLeftCorner: Point3,
    var horizontal: Vec3,
    var vertical: Vec3
) {
    constructor(
        vFov: Double, // vertical field-of-view in degrees
        aspectRatio: Double
    ) : this(Point3(), Point3(), Vec3(), Vec3()) {
        val theta = degreesToRadians(vFov)
        val h = tan(theta / 2.0)
        val viewportHeight = 2.0 * h
        val viewportWidth = aspectRatio * viewportHeight
        val focalLength = 1.0

        this.origin = Point3(0.0, 0.0, 0.0)
        this.horizontal = Vec3(viewportWidth, 0.0, 0.0)
        this.vertical = Vec3(0.0, viewportHeight, 0.0)
        this.lowerLeftCorner = origin - horizontal / 2.0 - vertical / 2.0 -
                Vec3(0.0, 0.0, focalLength)
    }

    fun getRay(u: Double, v: Double): Ray {
        return Ray(
            origin,
            lowerLeftCorner + u * horizontal + v * vertical - origin
        )
    }
}
