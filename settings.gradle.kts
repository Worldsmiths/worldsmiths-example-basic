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
    includeBuild("$engineRoot/gradle-plugin")
}

plugins {
    id("io.github.worldsmiths.engine")
}

dependencyResolutionManagement {
    repositories {
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

// Documented consumer snippet: docs/includebuild-substitutions.md in the engine repo.
includeBuild(engineRoot) {
    dependencySubstitution {
        substitute(module("io.github.worldsmiths:backends-desktop"))
            .using(project(":backends:desktop"))
        substitute(module("io.github.worldsmiths:backends-web"))
            .using(project(":backends:web"))
        substitute(module("io.github.worldsmiths:ws-core"))
            .using(project(":runtime:ws-core"))
    }
}

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
