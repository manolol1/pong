package xyz.manolol.pong.screens.mainmenu;

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
import xyz.manolol.pong.screens.start.StartScreen;
import xyz.manolol.pong.utils.FontManager;

public class MainMenuScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Stage stage;
    private final Skin skin;
    private final FontManager fontManager;
    private final Table root;
    private final Table playerSelectionTable;
    private Label label;
    private TextButton textButton;

    public MainMenuScreen() {
        Gdx.app.log("MainMenuScreen", "loaded");

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        fontManager = new FontManager("fonts/Roboto-Regular.ttf");

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        playerSelectionTable = new Table();

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(130);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("[FOREST]PONG", skin);
        root.add(label).padBottom(40).row();

        // Player Selection Buttons //
        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("1 Player", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Pong.GAME.setScreen(new StartScreen(1));
            }
        });
        playerSelectionTable.add(textButton).padBottom(50).width(400).padRight(50);

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("2 Players", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Pong.GAME.setScreen(new StartScreen(2));
            }
        });
        playerSelectionTable.add(textButton).padBottom(50).width(400).row();

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("3 Players", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Pong.GAME.setScreen(new StartScreen(3));
            }
        });
        playerSelectionTable.add(textButton).padBottom(50).width(400).padRight(50);

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("4 Players", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Pong.GAME.setScreen(new StartScreen(4));
            }
        });
        playerSelectionTable.add(textButton).padBottom(50).width(400).row();

        root.add(playerSelectionTable).padBottom(40).row();

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("Exit", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        root.add(textButton).padBottom(50).width(400).padRight(50);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

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
