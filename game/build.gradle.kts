plugins {
    `java-library`
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(8)
    options.compilerArgs.add("-Xlint:-options")
}

dependencies {
    // Bar C: ws-core only — no direct LibGDX artifact dependency.
    implementation("io.github.worldsmiths:ws-core:0.0.1-SNAPSHOT")
}
