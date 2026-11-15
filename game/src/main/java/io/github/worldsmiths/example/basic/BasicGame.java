package io.github.worldsmiths.example.basic;

import io.github.worldsmiths.core.Scene;
import io.github.worldsmiths.core.WorldsmithsGame;

/**
 * Blank game hand-ported from {@code samples/minimal} for the external example (Dp AC5).
 * The engine/backend clears the window; this class only satisfies the {@link WorldsmithsGame}
 * contract. Bar C: no direct LibGDX package imports in game sources.
 */
public final class BasicGame implements WorldsmithsGame {

    @Override
    public Scene createScene() {
        return new Scene();
    }

    @Override
    public void create() {}

    @Override
    public void update(float delta) {}

    @Override
    public void render() {}
}
