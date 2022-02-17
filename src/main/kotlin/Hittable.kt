data class HitRecord(
    var p: Point3 = Point3(),
    var normal: Vec3 = Vec3(),
    var mat: Material = Material.NONE,
    var t: Double = 0.0,
    var frontFace: Boolean = false
) {
    inline fun setFaceNormal(r: Ray, outwardNormal: Vec3) {
        frontFace = (r.direction dot outwardNormal) < 0.0
        normal = if (frontFace) outwardNormal else -outwardNormal
    }

    companion object {
        val NONE = HitRecord()
    }
}

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
