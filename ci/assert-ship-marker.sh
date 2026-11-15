#!/usr/bin/env bash
# Assert ship archives contain the assets marker (and itch zip has index.html at/under root listing).
set -euo pipefail

root="$(cd "$(dirname "$0")/.." && pwd)"
marker="ws-ship-marker.txt"
desk_zip="$(find "$root/.worldsmiths/gen/desktop/build/distributions" -name '*.zip' | head -n 1)"
web_zip="$root/.worldsmiths/gen/web/build/distributions/web-itch.zip"

test -n "$desk_zip"
test -f "$web_zip"
unzip -l "$desk_zip" | grep -F "$marker"
unzip -l "$web_zip" | grep -F "$marker"
# unzip -l prints size/date columns before the entry name; match root or nested index.html.
unzip -l "$web_zip" | grep -E '(^|[[:space:]]|/)index\.html$'
echo "ship-marker OK: $marker present in desktop distZip and web-itch.zip"
