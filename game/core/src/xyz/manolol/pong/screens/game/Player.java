package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import xyz.manolol.pong.utils.ControlScheme;

public class Player {
    private final Rectangle bounds;
    private ControlScheme controls;

    public Player(float posX, float posY, float sizeX, float sizeY) {
        bounds = new Rectangle(posX, posY, sizeX, sizeY);
    }

    public void setControlScheme(ControlScheme controls) {
        this.controls = controls;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
