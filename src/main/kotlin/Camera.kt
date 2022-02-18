import kotlin.math.tan

data class Camera(
    var origin: Point3,
    var lowerLeftCorner: Point3,
    var horizontal: Vec3,
    var vertical: Vec3,
    var u: Vec3,
    var v: Vec3,
    var w: Vec3,
    var lensRadius: Double
) {
    constructor(
        lookFrom: Point3,
        lookAt: Point3,
        vUp: Vec3,
        vFov: Double, // vertical field-of-view in degrees
        aspectRatio: Double,
        aperture: Double,
        focusDist: Double
    ) : this(Point3(), Point3(), Vec3(), Vec3(), Vec3(), Vec3(), Vec3(), 0.0) {
        val theta = degreesToRadians(vFov)
        val h = tan(theta / 2.0)
        val viewportHeight = 2.0 * h
        val viewportWidth = aspectRatio * viewportHeight

        this.w = unitVector(lookFrom - lookAt)
        this.u = unitVector(vUp cross w)
        this.v = w cross u

        this.origin = lookFrom
        this.horizontal = focusDist * viewportWidth * u
        this.vertical = focusDist * viewportHeight * v
        this.lowerLeftCorner = origin - horizontal / 2.0 - vertical / 2.0 -
                focusDist * w

        this.lensRadius = aperture / 2.0
    }

    fun getRay(s: Double, t: Double): Ray {
        val rd = lensRadius * randomInUnitDisk()
        val offset = u * rd.x + v * rd.y

        return Ray(
            origin + offset,
            lowerLeftCorner + s * horizontal + t * vertical - origin - offset
        )
    }
}
