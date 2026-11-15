#!/usr/bin/env bash
# Bar C gate (Dp AC5): no direct gdx dep; no com.badlogic.gdx imports in game sources.
set -euo pipefail

root="$(cd "$(dirname "$0")/.." && pwd)"
game_src="$root/game/src"
game_build="$root/game/build.gradle.kts"

# Match import lines only (ignore comments / javadoc that mention the package name).
hits="$(grep -RIn --include='*.java' -E '^[[:space:]]*import[[:space:]]+(static[[:space:]]+)?com\.badlogic\.gdx(\.|;)' "$game_src" || true)"
if [[ -n "$hits" ]]; then
  echo "bar-C FAIL: com.badlogic.gdx import(s) in game sources:" >&2
  echo "$hits" >&2
  exit 1
fi

# Dependency notations only (ignore comments that name the forbidden group).
if grep -E '^[[:space:]]*(implementation|api|compileOnly|runtimeOnly)\([^)]*com\.badlogicgames\.gdx' "$game_build" >/dev/null; then
  echo "bar-C FAIL: direct com.badlogicgames.gdx dependency in game/build.gradle.kts" >&2
  exit 1
fi

echo "bar-C OK: no direct gdx dep; no com.badlogic.gdx imports in game sources"
