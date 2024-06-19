package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xyz.manolol.pong.Constants;
import xyz.manolol.pong.Pong;
import xyz.manolol.pong.screens.gameover.GameOverScreen;

public class GameScreen extends ScreenAdapter {

    // cameras and viewports
    private final OrthographicCamera gameCamera;
    private final OrthographicCamera uiCamera;
    private final FitViewport gameViewport;
    private final FitViewport uiViewport;

    private final ShapeRenderer shapeRenderer;

    private final PlayerManager playerManager;
    private final Ball ball;
    private final BallCollisionController ballCollisionController;

    private final int playerCount;
    private int gameOverState = -1;

    public GameScreen(int playerCount) {
        this.playerCount = playerCount;

        // set up cameras and viewports
        gameCamera = new OrthographicCamera();
        uiCamera = new OrthographicCamera();
        gameViewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, gameCamera);
        uiViewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, uiCamera);

        shapeRenderer = new ShapeRenderer();

        playerManager = new PlayerManager(playerCount);
        ball = new Ball();
        ballCollisionController = new BallCollisionController(playerManager, ball, this, playerCount);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        gameViewport.apply();
        shapeRenderer.setProjectionMatrix(gameCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // update game logic
        ball.update(delta);
        playerManager.update(delta);
        ballCollisionController.update(delta);

        // draw everything
        ball.draw(shapeRenderer);
        playerManager.draw(shapeRenderer);

        shapeRenderer.end();

        // workaround to avoid crash
        if (gameOverState != -1) {
            Pong.GAME.setScreen(new GameOverScreen(gameOverState, playerCount));
            return;
        }
    }

    public void gameOver(int player) {
        gameOverState = player;
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        uiViewport.update(width,height, true);
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }
}
