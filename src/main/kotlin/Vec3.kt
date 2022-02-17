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

    inline operator fun plus(that: Vec3): Vec3 {
        return Vec3(this.x + that.x, this.y + that.y, this.z + that.z)
    }

    operator fun plusAssign(that: Vec3) {
        this.x += that.x
        this.y += that.y
        this.z += that.z
    }

    inline operator fun minus(that: Vec3): Vec3 {
        return Vec3(this.x - that.x, this.y - that.y, this.z - that.z)
    }

    inline operator fun times(that: Vec3): Vec3 {
        return Vec3(this.x * that.x, this.y * that.y, this.z * that.z)
    }

    inline operator fun times(t: Double): Vec3 {
        return Vec3(t * x, t * y, t * z)
    }

    operator fun timesAssign(t: Double) {
        this.x *= t
        this.y *= t
        this.z *= t
    }

    inline operator fun div(t: Double): Vec3 {
        return (1 / t) * this
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

    inline infix fun dot(that: Vec3): Double {
        return this.x * that.x + this.y * that.y + this.z * that.z
    }

    inline infix fun cross(that: Vec3): Vec3 {
        return Vec3(
            this.y * that.z - this.z * that.y,
            this.z * that.x - this.x * that.z,
            this.x * that.y - this.y * that.x
        )
    }

    companion object {
        inline fun random(): Vec3 {
            return Vec3(randomDouble(), randomDouble(), randomDouble())
        }

        inline fun random(min: Double, max: Double): Vec3 {
            return Vec3(
                randomDouble(min, max),
                randomDouble(min, max),
                randomDouble(min, max)
            )
        }
    }
}

// Type aliases for vec3
typealias Point3 = Vec3  // 3D point
typealias Color = Vec3   // RGB color

inline operator fun Double.times(v: Vec3): Vec3 {
    return v * this
}

inline fun unitVector(v: Vec3): Vec3 {
    return v / v.length
}

fun randomInUnitSphere(): Vec3 {
    while (true) {
        val p = Vec3.random(-1.0, 1.0)
        if (p.lengthSquared >= 1) continue
        return p
    }
}
