data class Lambertian(val albedo: Color) : Material {
    override fun scatter(
        rIn: Ray,
        rec: HitRecord,
        attenuation: Reference<Color>,
        scattered: Reference<Ray>
    ): Boolean {
        var scatterDirection = rec.normal + randomUnitVector()

        // Catch degenerate scatter direction
        if (scatterDirection.isNearZero)
            scatterDirection = rec.normal

        scattered.value = Ray(rec.p, scatterDirection)
        attenuation.value = albedo
        return true
    }
}
