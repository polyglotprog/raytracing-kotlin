import kotlin.math.tan

data class Camera(
    var origin: Point3,
    var lowerLeftCorner: Point3,
    var horizontal: Vec3,
    var vertical: Vec3
) {
    constructor(
        lookFrom: Point3,
        lookAt: Point3,
        vUp: Vec3,
        vFov: Double, // vertical field-of-view in degrees
        aspectRatio: Double
    ) : this(Point3(), Point3(), Vec3(), Vec3()) {
        val theta = degreesToRadians(vFov)
        val h = tan(theta / 2.0)
        val viewportHeight = 2.0 * h
        val viewportWidth = aspectRatio * viewportHeight

        val w = unitVector(lookFrom - lookAt)
        val u = unitVector(vUp cross w)
        val v = w cross u

        this.origin = lookFrom
        this.horizontal = viewportWidth * u
        this.vertical = viewportHeight * v
        this.lowerLeftCorner = origin - horizontal / 2.0 - vertical / 2.0 - w
    }

    fun getRay(s: Double, t: Double): Ray {
        return Ray(
            origin,
            lowerLeftCorner + s * horizontal + t * vertical - origin
        )
    }
}
