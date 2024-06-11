package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private Rectangle bounds;

    public Player(float posX, float posY, float sizeX, float sizeY) {
        bounds = new Rectangle(posX, posY, sizeX, sizeY);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
