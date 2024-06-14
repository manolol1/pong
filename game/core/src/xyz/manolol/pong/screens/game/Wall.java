package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.math.Rectangle;

public class Wall {
    private final int player;
    private final Rectangle bounds;

    public Wall(int player, Rectangle bounds) {
        this.player = player;
        this.bounds = bounds;
    }

    public int getPlayer() {
        return player;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
