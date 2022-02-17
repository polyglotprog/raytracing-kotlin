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

## In One Weekend

### 2.2. Creating an Image File

![Image 1: First PPM image](/images/1-in-one-weekend/image01.png)

*Image 1: First PPM image*

### 4.2. Sending Rays Into the Scene

![Image 2: A blue-to-white gradient depending on ray Y coordinate](/images/1-in-one-weekend/image02.png)

*Image 2: A blue-to-white gradient depending on ray Y coordinate*

### 5.2. Creating Our First Raytraced Image

![Image 3: A simple red sphere](/images/1-in-one-weekend/image03.png)

*Image 3: A simple red sphere*

### 6.1. Shading with Surface Normals

![Image 4: A sphere colored according to its normal vectors](/images/1-in-one-weekend/image04.png)

*Image 4: A sphere colored according to its normal vectors*

### 6.7. Common Constants and Utility Functions

![Image 5: Resulting render of normals-colored sphere with ground](/images/1-in-one-weekend/image05.png)

*Image 5: Resulting render of normals-colored sphere with ground*

### 7.2. Generating Pixels with Multiple Samples

![Image 6: After antialiasing](/images/1-in-one-weekend/image06.png)

![Image 6: Before and after antialiasing](/images/1-in-one-weekend/image06-before-after.png)

*Image 6: Before and after antialiasing*

### 8.2. Limiting the Number of Child Rays

![Image 7: First render of a diffuse sphere](/images/1-in-one-weekend/image07.png)

*Image 7: First render of a diffuse sphere*

### 8.3. Using Gamma Correction for Accurate Color Intensity

![Image 8: Diffuse sphere, with gamma correction](/images/1-in-one-weekend/image08.png)

*Image 8: Diffuse sphere, with gamma correction*

### 8.5. True Lambertian Reflection

![Image 9: Correct rendering of Lambertian spheres](/images/1-in-one-weekend/image09.png)

*Image 9: Correct rendering of Lambertian spheres*

### 8.6. An Alternative Diffuse Formulation

![Image 10: Rendering of diffuse spheres with hemispherical scattering](/images/1-in-one-weekend/image10.png)

*Image 10: Rendering of diffuse spheres with hemispherical scattering*

### 9.5. A Scene with Metal Spheres

![Image 11: Shiny metal](/images/1-in-one-weekend/image11.png)

*Image 11: Shiny metal*

### 9.6. Fuzzy Reflection

![Image 12: Fuzzed metal](/images/1-in-one-weekend/image12.png)

*Image 12: Fuzzed metal*

### 10.1. Refraction

*Image 13: Glass first* is omitted. It shows how glass can be rendered
incorrectly due to a bug, so there is no explanation given for how to generate
this image. You can view the original at
[10.1. Refraction](https://raytracing.github.io/books/RayTracingInOneWeekend.html#dielectrics/refraction).


[_Ray Tracing in One Weekend_ Series]: https://raytracing.github.io/
[_Ray Tracing in One Weekend_]: https://raytracing.github.io/books/RayTracingInOneWeekend.html
[_Ray Tracing: The Next Week_]: https://raytracing.github.io/books/RayTracingTheNextWeek.html
[_Ray Tracing: The Rest of Your Life_]: https://raytracing.github.io/books/RayTracingTheRestOfYourLife.html
[`1-in-one-weekend`]: ../../tree/1-in-one-weekend
[`2-the-next-week`]: ../../tree/2-the-next-week
[`3-the-rest-of-your-life`]: ../../tree/3-the-rest-of-your-life
[Kotlin]: https://kotlinlang.org/
[GIMP]: https://www.gimp.org/
