# Ray Tracing in One Weekend (Kotlin Implementation)

An implementation of Peter Shirley's ray tracer from the
[_Ray Tracing in One Weekend_], written in [Kotlin].

Kotlin's concise code, [operator overloading], and [infix functions] make it a
nice fit for this project.

After completing each section in the book, I commit the code changes. If you
want to see how each feature is implemented, refer to the commit history. The
`images` folder contains all images rendered along the way (see below as well).

Like the original, this code is released under the [CC0 license]. Thank you,
Peter Shirley!

## Building and Running

To build, you need Java 11 or greater installed.

Open a shell in the project directory. On macOS/Linux, use `./gradlew`. On
Windows, use `gradlew.bat`.

To build, run:

```console
$ ./gradlew clean build
```

After building, run the following:

```console
$ java -jar build/libs/raytracing-kotlin.jar > image.ppm
```

This will generate `image.ppm` in the project directory. You can view PPM images
and convert them to other formats using a program such as [GIMP], or use an
online PPM viewer such as the following:

https://www.cs.rhodes.edu/welshc/COMP141_F16/ppmReader.html


<!------------------------------------------------------------------------------
  Links
------------------------------------------------------------------------------->
[_Ray Tracing in One Weekend_]: https://raytracing.github.io/books/RayTracingInOneWeekend.html
[CC0 license]: /LICENSE
[GIMP]: https://www.gimp.org/
[infix functions]: https://kotlinlang.org/docs/functions.html#infix-notation
[Kotlin]: https://kotlinlang.org/
[operator overloading]: https://kotlinlang.org/docs/operator-overloading.html
