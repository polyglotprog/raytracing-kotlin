import kotlin.math.abs
import kotlin.math.min
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

    val isNearZero: Boolean
        @JvmName("isNearZero")
        get() {
            val s = 1e-8
            return abs(x) < s && abs(y) < s && abs(z) < s
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
        val ZERO = Vec3()

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

fun randomUnitVector(): Vec3 {
    return unitVector(randomInUnitSphere())
}

fun randomInHemisphere(normal: Vec3): Vec3 {
    val inUnitSphere = randomInUnitSphere()
    return if (inUnitSphere dot normal > 0.0)
    // In the same hemisphere as the normal
        inUnitSphere
    else
        -inUnitSphere
}

fun randomInUnitDisk(): Vec3 {
    while (true) {
        val p = Vec3(randomDouble(-1.0, 1.0), randomDouble(-1.0, 1.0), 0.0)
        if (p.lengthSquared >= 1) continue
        return p
    }
}

fun reflect(v: Vec3, n: Vec3): Vec3 {
    return v - 2 * (v dot n) * n
}

fun refract(uv: Vec3, n: Vec3, etaiOverEtat: Double): Vec3 {
    val cosTheta = min((-uv) dot n, 1.0)
    val rOutPerp = etaiOverEtat * (uv + cosTheta * n)
    val rOutParallel = -sqrt(abs(1.0 - rOutPerp.lengthSquared)) * n
    return rOutPerp + rOutParallel
}
