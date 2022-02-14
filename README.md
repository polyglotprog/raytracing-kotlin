# Ray Tracing in One Weekend (Kotlin Implementation)

An implementation of Peter Shirley's ray tracer from the
[_Ray Tracing in One Weekend_ Series], written in [Kotlin].

1. [_Ray Tracing in One Weekend_]
2. [_Ray Tracing: The Next Week_]
3. [_Ray Tracing: The Rest of Your Life_]

The code for each book is on its own branch:

* [`1-in-one-weekend`]
* [`2-the-next-week`]
* [`3-the-rest-of-your-life`]

After completing each section in the book, I commit the code changes. So if you
want to see how each feature is implemented, refer to the commit history. Each
section is also tagged for easy checking out. The `images` folder contains all
images rendered along the way (see below as well).

## Building and Running

To build, you need Java 11 or greater installed.

Open a shell in the project directory. On macOS/Linux, use `./gradlew`. On
Windows, use `gradlew.bat`.

To build, run:

```shell
./gradlew clean build
```

After building, run the following:

```shell
java -jar build/libs/raytracing-kotlin.jar > image.ppm
```

This will generate `image.ppm` in the project directory. You can view PPM images
and convert them to other formats using a program such as [GIMP], or use an
online PPM viewer such as the following:

https://www.cs.rhodes.edu/welshc/COMP141_F16/ppmReader.html


[_Ray Tracing in One Weekend_ Series]: https://raytracing.github.io/
[_Ray Tracing in One Weekend_]: https://raytracing.github.io/books/RayTracingInOneWeekend.html
[_Ray Tracing: The Next Week_]: https://raytracing.github.io/books/RayTracingTheNextWeek.html
[_Ray Tracing: The Rest of Your Life_]: https://raytracing.github.io/books/RayTracingTheRestOfYourLife.html
[`1-in-one-weekend`]: ../../tree/1-in-one-weekend
[`2-the-next-week`]: ../../tree/2-the-next-week
[`3-the-rest-of-your-life`]: ../../tree/3-the-rest-of-your-life
[Kotlin]: https://kotlinlang.org/
[GIMP]: https://www.gimp.org/
