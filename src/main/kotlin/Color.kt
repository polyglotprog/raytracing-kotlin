import java.io.PrintStream

fun writeColor(out: PrintStream, pixelColor: Color) {
    out.println(
        (255.999 * pixelColor.x).toInt().toString() + " " +
        (255.999 * pixelColor.y).toInt().toString() + " " +
        (255.999 * pixelColor.z).toInt().toString()
    )
}
