package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import xyz.manolol.pong.Constants;

public class Ball {
    private final Rectangle bounds;
    private Vector2 velocity = new Vector2(Constants.BALL_DEFAULT_SPEED, 0f);

    public Ball() {
        bounds = new Rectangle(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, Constants.BALL_SIZE, Constants.BALL_SIZE);
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void update(float delta) {
        bounds.x += velocity.x * delta;
        bounds.y += velocity.y * delta;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void swapVelocityX() {
        velocity.x = -velocity.x;
    }

    public void swapVelocityY() {
        velocity.y = -velocity.y;
    }

    public void setVelocityX(float velocityX) {
        velocity.x = velocityX;
    }

    public void setVelocityY(float velocityY) {
        velocity.y = velocityY;
    }
}
