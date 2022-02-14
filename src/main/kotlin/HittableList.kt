data class HittableList(
    val objects: MutableList<Hittable> =
        MutableList<Hittable>(0) { Sphere() }
) : Hittable {
    fun clear() {
        objects.clear()
    }

    fun add(obj: Hittable) {
        objects.add(obj)
    }

    override fun hit(
        r: Ray,
        tMin: Double,
        tMax: Double,
        recRef: Reference<HitRecord>
    ): Boolean {
        val tempRec = Reference(HitRecord.NONE)
        var hitAnything = false
        var closestSoFar = tMax

        objects.forEach {
            if (it.hit(r, tMin, closestSoFar, tempRec)) {
                hitAnything = true
                closestSoFar = tempRec.value.t
                recRef.value = tempRec.value
            }
        }

        return hitAnything
    }
}
