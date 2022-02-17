import kotlin.math.min
import kotlin.math.sqrt

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
        val cosTheta = min(-unitDirection dot rec.normal, 1.0)
        val sinTheta = sqrt(1.0 - cosTheta * cosTheta)

        val cannotRefract = refractionRatio * sinTheta > 1.0

        val direction = if (cannotRefract)
            reflect(unitDirection, rec.normal)
        else
            refract(unitDirection, rec.normal, refractionRatio)

        scattered.value = Ray(rec.p, direction)
        return true
    }
}
