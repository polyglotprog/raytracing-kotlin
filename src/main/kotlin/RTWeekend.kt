import java.util.Random

// Constants

const val infinity = Double.POSITIVE_INFINITY
const val pi = Math.PI

// Utility Functions

inline fun degreesToRadians(degrees: Double): Double {
    return degrees * pi / 180.0
}

val random = Random(0)

inline fun randomDouble(): Double {
    // Returns a random real in [0, 1).
    return random.nextDouble()
}

inline fun randomDouble(min: Double, max: Double): Double {
    // Returns a random real in [min,max).
    return min + (max - min) * randomDouble()
}
