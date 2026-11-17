# worldsmiths-example-basic

External hand-port of the Worldsmiths `samples/minimal` game with **desktop + web** targets (Dp AC5). Proves the hobby ship loop against an unpublished engine via `includeBuild` (ADR-0003) — not Maven Central.

## Bar C

- No direct `com.badlogicgames.gdx` dependency in `:game`
- No `com.badlogic.gdx` imports in game sources
- Transitive LibGDX via `ws-core` is OK

CI enforces this with `ci/assert-bar-c.sh`.

## Requirements

- JDK 21+ to run Gradle
- A checkout of [Worldsmiths/worldsmiths](https://github.com/Worldsmiths/worldsmiths) (engine)
- Game module compiles for **Java 8** (`options.release = 8`)

## Engine path

| Context | Path |
| --- | --- |
| Local default | Sibling `../worldsmiths` |
| Override | `WORLDSMITHS_ENGINE_ROOT` or `-Dworldsmiths.engineRoot=...` |
| CI | Dual-checkout at `engine/` + `game/`; pin SHA in `.github/workflows/ci.yml` |

Uses bare `includeBuild` against the engine checkout (no `dependencySubstitution`). See engine [`docs/includebuild-substitutions.md`](https://github.com/Worldsmiths/worldsmiths/blob/implement/da-architecture-seams/docs/includebuild-substitutions.md).

## Build ship archives

```bash
export WORLDSMITHS_ENGINE_ROOT=/path/to/worldsmiths   # if not ../worldsmiths
./gradlew :desktop:distZip :web:wsItchWebZip
```

Marker file `assets/ws-ship-marker.txt` must appear in both archives (`ci/assert-ship-marker.sh`).

## Run desktop (manual)

```bash
./gradlew :desktop:run
```

## Generator

Project generator / template CLI is deferred; this repo is the hand-maintained external example.
