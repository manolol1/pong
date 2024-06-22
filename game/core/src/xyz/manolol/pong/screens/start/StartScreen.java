package xyz.manolol.pong.screens.start;

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
import xyz.manolol.pong.utils.ControlScheme;
import xyz.manolol.pong.utils.FontManager;

public class StartScreen extends ScreenAdapter {
    private final OrthographicCamera camera;
    private final FitViewport viewport;
    private final Stage stage;
    private final Skin skin;
    private final FontManager fontManager;
    private final Table root;
    private final Table controlSchemeTable;
    private final Table buttonTable;
    private Label label;
    private TextButton textButton;

    public StartScreen(int playerCount) {
        Gdx.app.log("StartScreen", "loaded with playerCount: " + playerCount);

        camera = new OrthographicCamera();
        viewport = new FitViewport(Constants.UI_WIDTH, Constants.UI_HEIGHT, camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        skin = VisUI.getSkin();
        fontManager = new FontManager("fonts/Roboto-Regular.ttf");

        root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(100);
        skin.get(Label.LabelStyle.class).font.getData().markupEnabled = true;
        label = new Label("[FOREST]Controls", skin);
        root.add(label).padBottom(40).row();

        // set up controlSchemeTable
        controlSchemeTable = new Table();
        ControlScheme[][] cs = Constants.CONTROL_SCHEMES;
        String left, right;

        skin.get(Label.LabelStyle.class).font = fontManager.getFont(80);
        for (int i = 1; i <= playerCount; i++) {
            left = cs[playerCount - 1][i - 1].leftText;
            right = cs[playerCount - 1][i - 1].rightText;

            label = new Label(String.format("Player %d: %s and %s", i, left, right), skin);
            controlSchemeTable.add(label).padRight(80).padBottom(40);

            if (i == 2) controlSchemeTable.row();
        }

        root.add(controlSchemeTable).padBottom(40).row();

        // set up buttonTable
        buttonTable = new Table();

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("Back", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Pong.GAME.setScreen(new MainMenuScreen());
            }
        });
        buttonTable.add(textButton).padBottom(50).width(400).padRight(50);

        skin.get(TextButton.TextButtonStyle.class).font = fontManager.getFont(80);
        textButton = new TextButton("Start", skin);
        textButton.pad(15);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Pong.GAME.setScreen(new GameScreen(playerCount));
            }
        });
        buttonTable.add(textButton).padBottom(50).width(400).padRight(50);

        root.add(buttonTable).padBottom(40).row();
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
