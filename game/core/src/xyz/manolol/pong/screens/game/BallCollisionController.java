package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import xyz.manolol.pong.Constants;

public class BallCollisionController {
    private final PlayerManager playerManager;
    private final Ball ball;
    private final int playerCount;
    Array<Wall> walls;

    public BallCollisionController(PlayerManager playerManager, Ball ball, int playerCount) {
        this.playerManager = playerManager;
        this.ball = ball;
        this.playerCount = playerCount;

        // TODO: Add walls
        walls.add(new Wall(1, new Rectangle(Constants.WORLD_HEIGHT, Constants.WORLD_WIDTH, 20, 20)));
    }

    public void update() {
        // bounce off of players
        for (Player player : playerManager.getPlayers()) {
            if (player.getBounds().overlaps(ball.getBounds())) {
                if (player.getMovementType() == MovementType.LEFT_RIGHT) {
                    ball.swapVelocityY();
                    ball.setVelocityX(MathUtils.random(-Constants.BALL_BOUNCE_RANGE, Constants.BALL_BOUNCE_RANGE));
                }
                else if (player.getMovementType() == MovementType.UP_DOWN) {
                    ball.swapVelocityX();
                    ball.setVelocityY(MathUtils.random(-Constants.BALL_BOUNCE_RANGE, Constants.BALL_BOUNCE_RANGE));
                }
            }
        }

        // bounce off walls or make player loose
        if (ball.getBounds().x < 0 || ball.getBounds().x > Constants.WORLD_WIDTH || ball.getBounds().y < 0 || ball.getBounds().y > Constants.WORLD_HEIGHT) {
            // check if player has lost

        }
    }
}
