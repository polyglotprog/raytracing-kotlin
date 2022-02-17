data class Ray(
    @JvmField val origin: Point3 = Point3(),
    @JvmField val direction: Vec3 = Vec3()
) {
    fun at(t: Double): Vec3 {
        return origin + t * direction
    }

    companion object {
        val NONE = Ray()
    }
}
