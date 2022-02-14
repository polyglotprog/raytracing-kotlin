import kotlin.math.sqrt

data class Sphere(
    val center: Point3 = Point3(),
    val radius: Double = 1.0
) : Hittable {
    override fun hit(
        r: Ray,
        tMin: Double,
        tMax: Double,
        recRef: Reference<HitRecord>
    ): Boolean {
        val oc = r.origin - center
        val a = r.direction.lengthSquared
        val halfB = oc dot r.direction
        val c = oc.lengthSquared - radius * radius

        val discriminant = halfB * halfB - a * c
        if (discriminant < 0.0) {
            return false
        }
        val sqrtD = sqrt(discriminant)

        // Find the nearest root that lies in the acceptable range.
        var root = (-halfB - sqrtD) / a
        if (root < tMin || tMax < root) {
            root = (-halfB + sqrtD) / a
            if (root < tMin || tMax < root)
                return false
        }

        val rec = recRef.value
        rec.t = root
        rec.p = r.at(rec.t)
        val outwardNormal = (rec.p - center) / radius
        rec.setFaceNormal(r, outwardNormal)

        return true
    }
}
