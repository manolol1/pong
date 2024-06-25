package xyz.manolol.pong.screens.gameover;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.pong.Constants;
import xyz.manolol.pong.Pong;
import xyz.manolol.pong.screens.game.GameScreen;
import xyz.manolol.pong.screens.mainmenu.MainMenuScreen;
import xyz.manolol.pong.utils.FontManager;
import xyz.manolol.pong.utils.HighscoreManager;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GameOverScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Stage stage;
    private final Skin skin;
    private Label label;
    private TextButton textButton;
    private Label onlineHighscoreLabel;

    private final HighscoreManager highscoreManager;
    CompletableFuture<Integer> highscoreFuture;

    public GameOverScreen(int playerLost, int playerCount, int score) {
        Gdx.app.log("GameOverScreen", "loaded with playerLost: " + playerLost + ", playerCount: " + playerCount + ", score: " + score);

        highscoreManager = new HighscoreManager();
        highscoreFuture = highscoreManager.getOnlineHighscore(playerCount);

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        FontManager fontManager = new FontManager("fonts/Roboto-Regular.ttf");

        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(140);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("[FIREBRICK]GAME OVER", skin);
        root.add(label).padBottom(40).row();

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(100);
        label = new Label("Player " + playerLost + " lost!", skin);
        root.add(label).padBottom(50).row();

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(80);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("Score: " + score, skin);
        root.add(label).padBottom(25).row();

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(80);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("Local Highscore: " + highscoreManager.getLocalHighscore(playerCount), skin);
        root.add(label).padBottom(20).row();

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(80);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        onlineHighscoreLabel = new Label("Online Highscore: loading...", skin);
        root.add(onlineHighscoreLabel).padBottom(40).row();

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("Main Menu", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainMenuScreen screen = new MainMenuScreen();
                Pong.GAME.setScreen(screen);
            }
        });
        root.add(textButton).width(600).padBottom(50).row();

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("Play Again", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Pong.GAME.setScreen(new GameScreen(playerCount));
            }
        });
        root.add(textButton).width(600).padBottom(50).row();

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        // wait for online highscore to finish loading and set immediately
        if (highscoreFuture.isDone()) {
            try {
                onlineHighscoreLabel.setText("Online Highscore: " + highscoreFuture.get());
            } catch (InterruptedException | ExecutionException e) {
                onlineHighscoreLabel.setText("Failed to load online highscore");
            }
        } else {
            onlineHighscoreLabel.setText("Loading online highscore...");
        }

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void hide() {
        this.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}