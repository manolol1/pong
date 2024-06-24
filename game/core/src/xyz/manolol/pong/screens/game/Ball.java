package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import xyz.manolol.pong.Constants;

public class Ball {
    private final Rectangle bounds;
    private final Vector2 velocity;

    public Ball() {
        bounds = new Rectangle(Constants.WORLD_WIDTH / 2, Constants.WORLD_HEIGHT / 2, Constants.BALL_SIZE, Constants.BALL_SIZE);
        velocity = new Vector2();
        setRandomVelocity();
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

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setPositionX(float x) {
        bounds.x = x;
    }

    public void setPositionY(float y) {
        bounds.y = y;
    }

    // math formula from github copilot
    public void setRandomVelocity() {
        float angle = MathUtils.random(MathUtils.PI2);
        velocity.set(MathUtils.cos(angle) * Constants.BALL_DEFAULT_SPEED, MathUtils.sin(angle) * Constants.BALL_DEFAULT_SPEED);
    }
}
