// Engine checkout path: CI sets WORLDSMITHS_ENGINE_ROOT; local default is sibling ../worldsmiths.
// Override: WORLDSMITHS_ENGINE_ROOT=/path/to/worldsmiths or -Dworldsmiths.engineRoot=/path
// pluginManagement is an isolated script scope — resolve the path inside each block that needs it.
pluginManagement {
    val engineRoot =
        sequenceOf(
                System.getenv("WORLDSMITHS_ENGINE_ROOT"),
                System.getProperty("worldsmiths.engineRoot"),
            )
            .firstOrNull { !it.isNullOrBlank() }
            ?: "../worldsmiths"
    // AGP lives on the engine plugin classpath (TeaVM-style); Plugin Portal alone cannot resolve it.
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    includeBuild("$engineRoot/gradle-plugin")
}

plugins {
    id("io.github.worldsmiths.engine")
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

val engineRoot =
    sequenceOf(
            System.getenv("WORLDSMITHS_ENGINE_ROOT"),
            System.getProperty("worldsmiths.engineRoot"),
        )
        .firstOrNull { !it.isNullOrBlank() }
        ?: "../worldsmiths"

// Resolve published-looking coordinates from the engine checkout via bare includeBuild (ADR-0003).
// Project names match Maven artifact ids, so Gradle auto-substitutes — no dependencySubstitution.
includeBuild(engineRoot)

rootProject.name = "worldsmiths-example-basic"

include(":game")

worldsmiths {
    gameModule = ":game"
    gameClass = "io.github.worldsmiths.example.basic.BasicGame"
    assetsDir = "assets"
    targets {
        desktop {
            title.set("Worldsmiths Example Basic")
            width.set(800)
            height.set(600)
        }
        web {
            title.set("Worldsmiths Example Basic")
            width.set(800)
            height.set(600)
        }
    }
}
