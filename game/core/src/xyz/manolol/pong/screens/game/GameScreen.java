package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.pong.Constants;
import xyz.manolol.pong.Pong;
import xyz.manolol.pong.screens.gameover.GameOverScreen;
import xyz.manolol.pong.utils.FontManager;
import xyz.manolol.pong.utils.HighscoreManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    private final HighscoreManager highscoreManager;

    private int score = 0;

    private final Stage stage;
    private final Skin skin;
    private Label scoreLabel;
    private final Table root;
    private final FontManager fontManager;

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

        highscoreManager = new HighscoreManager();

        stage = new Stage(uiViewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        fontManager = new FontManager("fonts/Roboto-Regular.ttf");

        // Score Text
        skin.get(Label.LabelStyle.class).font = fontManager.getFont(80);
        scoreLabel = new Label("", skin);
        root = new Table();
        root.setFillParent(true);
        root.top().right();
        root.add(scoreLabel).pad(20);
        stage.addActor(root);
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

        // update score / UI
        uiViewport.apply();
        scoreLabel.setText(score);
        stage.act();
        stage.draw();

        // workaround for checking game over state to avoid crash
        if (gameOverState != -1) {
            Pong.GAME.setScreen(new GameOverScreen(gameOverState, playerCount, score));
            return; // we need to return here to avoid crashing later
        }
    }

    public void gameOver(int player) {
       // set highscore and wait for it to complete before switching screens
        // always sending current score (even if it isn't a new highscore), highscore server will do validation
        try {
            highscoreManager.setHighscore(playerCount, score).get(5, TimeUnit.SECONDS); // wait up to 5 seconds for the request to complete
        } catch (InterruptedException | ExecutionException e) {
            Gdx.app.error("GameScreen", "Failed to set highscore: " + e.getMessage());
        } catch (TimeoutException e) {
            Gdx.app.error("GameScreen", "Highscore set request timed out");
        }

        gameOverState = player;
    }

    public void score() {
        score++;
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        uiViewport.update(width, height, true);
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
