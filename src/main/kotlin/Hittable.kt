data class HitRecord(
    var p: Point3 = Point3(),
    var normal: Vec3 = Vec3(),
    var t: Double = 0.0
)

interface Hittable {
    fun hit(
        r: Ray,
        tMin: Double,
        tMax: Double,
        recRef: Reference<HitRecord>
    ): Boolean {
        return false
    }
}
