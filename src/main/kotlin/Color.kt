import java.io.PrintStream
import kotlin.math.sqrt

fun writeColor(out: PrintStream, pixelColor: Color, samplesPerPixel: Int) {
    var r = pixelColor.x
    var g = pixelColor.y
    var b = pixelColor.z

    // Divide the color by the number of samples and gamma-correct for
    // gamma=2.0.
    val scale = 1.0 / samplesPerPixel
    r = sqrt(scale * r)
    g = sqrt(scale * g)
    b = sqrt(scale * b)

    // Write the translated [0,255] value of each color component.
    out.println(
        (256.0 * clamp(r, 0.0, 0.999)).toInt().toString() + " " +
        (256.0 * clamp(g, 0.0, 0.999)).toInt().toString() + " " +
        (256.0 * clamp(b, 0.0, 0.999)).toInt().toString()
    )
}
