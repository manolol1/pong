package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.math.MathUtils;

public class BallCollisionController {
    private final PlayerManager playerManager;
    private final Ball ball;

    public BallCollisionController(PlayerManager playerManager, Ball ball) {
        this.playerManager = playerManager;
        this.ball = ball;
    }

    public void update() {
        // bounce off of players
        for (Player player : playerManager.getPlayers()) {
            if (player.getBounds().overlaps(ball.getBounds())) {
                if (player.getMovementType() == MovementType.LEFT_RIGHT) {
                    ball.swapVelocityY();
                    ball.setVelocityX(MathUtils.random(-2, 2));
                }
                else if (player.getMovementType() == MovementType.UP_DOWN) {
                    ball.swapVelocityX();
                    ball.setVelocityY(MathUtils.random(-10, 2));
                }
            }
        }
    }
}
