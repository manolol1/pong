package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xyz.manolol.pong.Constants;

public class GameScreen extends ScreenAdapter {

    // cameras and viewports
    private final OrthographicCamera gameCamera;
    private final OrthographicCamera uiCamera;
    private final FitViewport gameViewport;
    private final FitViewport uiViewport;

    private final ShapeRenderer shapeRenderer;

    Array<Player> players = new Array<>();



    public GameScreen(int playerCount) {
        // set up cameras and viewports
        gameCamera = new OrthographicCamera();
        uiCamera = new OrthographicCamera();
        gameViewport = new FitViewport(Constants.WORLD_WIDTH, Constants.WORLD_HEIGHT, gameCamera);
        uiViewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, uiCamera);


        shapeRenderer = new ShapeRenderer();

        // set up players
        if (playerCount >= 1) {
            players.add(new Player((Constants.WORLD_WIDTH / 2) - (Constants.PLAYER_LENGTH / 2), Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, Constants.PLAYER_LENGTH, Constants.PLAYER_WIDTH));
        }
        if (playerCount >= 2) {
            players.add(new Player((Constants.WORLD_WIDTH / 2) - (Constants.PLAYER_LENGTH / 2), Constants.WORLD_HEIGHT - Constants.PLAYER_WIDTH - Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, Constants.PLAYER_LENGTH, Constants.PLAYER_WIDTH));
        }
        if (playerCount >= 3) {
            players.add(new Player(Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, (Constants.WORLD_HEIGHT / 2) - (Constants.PLAYER_LENGTH / 2), Constants.PLAYER_WIDTH, Constants.PLAYER_LENGTH));
        }
        if (playerCount >= 4) {
            players.add(new Player(Constants.WORLD_WIDTH - Constants.PLAYER_WIDTH - Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, (Constants.WORLD_HEIGHT / 2) - (Constants.PLAYER_LENGTH / 2), Constants.PLAYER_WIDTH, Constants.PLAYER_LENGTH));
        }
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        gameViewport.apply();
        shapeRenderer.setProjectionMatrix(gameCamera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        for (Player player : players) {
            player.draw(shapeRenderer);
        }

        shapeRenderer.end();
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
