package xyz.manolol.pong.screens.game;

import xyz.manolol.pong.Constants;

public class BallCollisionController {
    private final PlayerManager playerManager;
    private final Ball ball;
    private final GameScreen gameScreen;
    private final int playerCount;

    public BallCollisionController(PlayerManager playerManager, Ball ball, GameScreen gameScreen, int playerCount) {
        this.playerManager = playerManager;
        this.ball = ball;
        this.gameScreen = gameScreen;
        this.playerCount = playerCount;
    }

    public void update(float delta) {
        // Check for collision with the walls
        if (ball.getBounds().x <= 0) { // left wall
            if (playerCount >= 3) {
                gameScreen.gameOver(3);
            } else {
                ball.swapVelocityX();
                ball.setRandomVelocity();
                ball.setPositionX(0); // Adjust position
            }
        } else if (ball.getBounds().x + ball.getBounds().width >= Constants.WORLD_WIDTH) { // right wall
            if (playerCount >= 4) {
                gameScreen.gameOver(4);
            } else {
                ball.swapVelocityX();
                ball.setRandomVelocity();
                ball.setPositionX(Constants.WORLD_WIDTH - ball.getBounds().width); // Adjust position
            }
        }

        if (ball.getBounds().y <= 0) { // bottom wall
            if (playerCount >= 2) {
                gameScreen.gameOver(2);
            } else if (playerCount == 1) {
                gameScreen.gameOver(1);
            } else {
                ball.setRandomVelocity();
                ball.swapVelocityY();
                ball.setPositionY(0); // Adjust position
            }
        } else if (ball.getBounds().y + ball.getBounds().height >= Constants.WORLD_HEIGHT) { // top wall
            if (playerCount > 1) {
                gameScreen.gameOver(1);
            } else {
                ball.swapVelocityY();
                ball.setRandomVelocity();
                ball.setPositionY(Constants.WORLD_HEIGHT - ball.getBounds().height); // Adjust position
            }
        }

        // Check for collision with the players
        for (int i = 0; i < playerCount; i++) {
            Player player = playerManager.getPlayer(i);
            if (player.getBounds().overlaps(ball.getBounds())) {

                gameScreen.score();

                if (player.getMovementType() == MovementType.LEFT_RIGHT) {
                    ball.swapVelocityY();
                } else {
                    ball.swapVelocityX();
                }

                ball.setRandomVelocity();

                // Move the ball out of the player
                if (player.getMovementType() == MovementType.UP_DOWN) {
                    if (ball.getBounds().x < Constants.WORLD_WIDTH / 2) {
                        ball.setPositionX(player.getBounds().x + Constants.BALL_SIZE + 2);
                    } else {
                        ball.setPositionX(player.getBounds().x - Constants.BALL_SIZE - 2);
                    }
                } else {
                    if (ball.getBounds().y < Constants.WORLD_HEIGHT / 2) {
                        ball.setPositionY(player.getBounds().y + Constants.BALL_SIZE + 2);
                    } else {
                        ball.setPositionY(player.getBounds().y - Constants.BALL_SIZE - 2);
                    }
                }
            }
        }
    }
}
