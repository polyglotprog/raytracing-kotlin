data class Dielectric(val indexOfRefraction: Double) : Material {
    override fun scatter(
        rIn: Ray,
        rec: HitRecord,
        attenuation: Reference<Color>,
        scattered: Reference<Ray>
    ): Boolean {
        attenuation.value = Color(1.0, 1.0, 1.0)
        val refractionRatio = if (rec.frontFace)
            1.0 / indexOfRefraction
        else
            indexOfRefraction

        val unitDirection = unitVector(rIn.direction)
        val refracted = refract(unitDirection, rec.normal, refractionRatio)

        scattered.value = Ray(rec.p, refracted)
        return true
    }
}
