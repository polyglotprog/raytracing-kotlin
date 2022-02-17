data class Metal(val albedo: Color, val fuzz: Double) : Material {
    override fun scatter(
        rIn: Ray,
        rec: HitRecord,
        attenuation: Reference<Color>,
        scattered: Reference<Ray>
    ): Boolean {
        val reflected = reflect(unitVector(rIn.direction), rec.normal)
        scattered.value = Ray(rec.p, reflected + fuzz * randomInUnitSphere())
        attenuation.value = albedo
        return scattered.value.direction dot rec.normal > 0.0
    }
}
