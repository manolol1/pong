package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xyz.manolol.pong.Constants;
import xyz.manolol.pong.utils.ControlScheme;

public class Player {
    private final Rectangle bounds;
    private ControlScheme controls;
    private MovementType movementType;

    public Player(float posX, float posY, float sizeX, float sizeY, MovementType movementType) {
        bounds = new Rectangle(posX, posY, sizeX, sizeY);
        controls = Constants.CONTROL_SCHEMES[0][0]; // set default control scheme (should be overwritten later)
        this.movementType = movementType;
    }

    public void setControlScheme(ControlScheme controls) {
        this.controls = controls;
    }

    public void draw(ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void update(float delta) {
        // movement (controls)
        if (Gdx.input.isKeyPressed(controls.leftKey)) {
            if (movementType == movementType.UP_DOWN) {
                bounds.y -= Constants.PLAYER_MOVEMENT_SPEED * delta;
            } else if (movementType == MovementType.LEFT_RIGHT) {
                bounds.x -= Constants.PLAYER_MOVEMENT_SPEED * delta;
            }
        }
        else if (Gdx.input.isKeyPressed(controls.rightKey)) {
            if (movementType == movementType.UP_DOWN) {
                bounds.y += Constants.PLAYER_MOVEMENT_SPEED * delta;
            }
            else if (movementType == MovementType.LEFT_RIGHT) {
                bounds.x += Constants.PLAYER_MOVEMENT_SPEED * delta;
            }
        }

        ensureBounds();

    }

    private void ensureBounds() {
        if (movementType == MovementType.LEFT_RIGHT) {
            bounds.x = MathUtils.clamp(bounds.x,Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, Constants.WORLD_WIDTH - Constants.PLAYER_LENGTH - Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER);
        }
        else if (movementType == MovementType.UP_DOWN) {
            bounds.y = MathUtils.clamp(bounds.y, Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, Constants.WORLD_WIDTH - Constants.PLAYER_LENGTH - Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER);
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public MovementType getMovementType() {
        return movementType;
    }
}
