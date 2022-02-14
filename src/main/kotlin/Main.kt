fun main() {

    // Image

    val imageWidth = 256
    val imageHeight = 256

    // Render

    println("P3\n$imageWidth $imageHeight\n255")

    for (j in (imageHeight - 1) downTo 0) {
        System.err.printf("\rScanlines remaining: %3d", j)
        for (i in 0 until imageWidth) {
            val pixelColor = Color(
                i.toDouble() / (imageWidth - 1),
                j.toDouble() / (imageHeight - 1),
                0.25
            )
            writeColor(System.out, pixelColor)
        }
    }

    System.err.println("\nDone.")
}
