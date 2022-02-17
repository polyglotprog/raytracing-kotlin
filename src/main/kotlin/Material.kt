fun interface Material {
    fun scatter(
        rIn: Ray,
        rec: HitRecord,
        attenuation: Reference<Color>,
        scattered: Reference<Ray>
    ): Boolean

    companion object {
        val NONE = Material { _, _, _, _ -> false }
    }
}
