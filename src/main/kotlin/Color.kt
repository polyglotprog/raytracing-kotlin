import java.io.PrintStream

fun writeColor(out: PrintStream, pixelColor: Color, samplesPerPixel: Int) {
    var r = pixelColor.x
    var g = pixelColor.y
    var b = pixelColor.z

    // Divide the color by the number of samples.
    val scale = 1.0 / samplesPerPixel
    r *= scale
    g *= scale
    b *= scale

    // Write the translated [0,255] value of each color component.
    out.println(
        (256.0 * clamp(r, 0.0, 0.999)).toInt().toString() + " " +
        (256.0 * clamp(g, 0.0, 0.999)).toInt().toString() + " " +
        (256.0 * clamp(b, 0.0, 0.999)).toInt().toString()
    )
}
