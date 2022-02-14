import kotlin.math.sqrt

data class Vec3(
    @JvmField var x: Double = 0.0,
    @JvmField var y: Double = 0.0,
    @JvmField var z: Double = 0.0
) {
    operator fun unaryMinus(): Vec3 {
        return Vec3(-x, -y, -z)
    }

    operator fun get(i: Int): Double {
        return when (i) {
            0 -> x
            1 -> y
            2 -> z
            else -> throw IllegalArgumentException(
                "Value must be in range 0 to 2 inclusive"
            )
        }
    }

    operator fun plusAssign(that: Vec3) {
        this.x += that.x
        this.y += that.y
        this.z += that.z
    }

    operator fun timesAssign(t: Double) {
        this.x *= t
        this.y *= t
        this.z *= t
    }

    operator fun divAssign(t: Double) {
        this *= 1 / t
    }

    val length: Double
        @JvmName("length")
        get() {
            return sqrt(lengthSquared)
        }

    val lengthSquared: Double
        @JvmName("lengthSquared")
        get() {
            return x * x + y * y + z * z
        }
}

// Type aliases for vec3
typealias Point3 = Vec3  // 3D point
typealias Color = Vec3   // RGB color
