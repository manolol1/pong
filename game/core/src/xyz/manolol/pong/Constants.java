package xyz.manolol.pong;

import com.badlogic.gdx.Input;

public class Constants
{
    /* Viewports */
    public static final float WORLD_WIDTH = 128;
    public static final float WORLD_HEIGHT = 72;
    public static final float UI_WIDTH = 1920;
    public static final float UI_HEIGHT = 1080;

    /* Player */
    public static final float PLAYER_WIDTH = 4f;
    public static final float PLAYER_LENGTH= 16f;
    public static final float PLAYER_DISTANCE_TO_SCREEN_BORDER = 1.2f;

    /* Input Modes */

    // one player
    InputMode INPUT_MODE_1_1 = new InputMode("A", "D", Input.Keys.A, Input.Keys.D);

    // two players
    InputMode INPUT_MODE_2_1 = new InputMode("A", "D", Input.Keys.A, Input.Keys.D);
    InputMode INPUT_MODE_2_2 = new InputMode("LeftArrow", "RightArrow", Input.Keys.LEFT, Input.Keys.RIGHT);

    // three players
    InputMode INPUT_MODE_3_1 = new InputMode("LeftArrow", "RightArrow", Input.Keys.LEFT, Input.Keys.RIGHT);
    InputMode INPUT_MODE_3_2 = new InputMode("A", "D", Input.Keys.A, Input.Keys.D);
    InputMode INPUT_MODE_3_3 = new InputMode("G", "J", Input.Keys.G, Input.Keys.J);

    // four players
    InputMode INPUT_MODE_4_1 = new InputMode("LeftArrow", "RightArrow", Input.Keys.LEFT, Input.Keys.RIGHT);
    InputMode INPUT_MODE_4_2 = new InputMode("A", "S", Input.Keys.A, Input.Keys.S);
    InputMode INPUT_MODE_4_3 = new InputMode("F", "G", Input.Keys.F, Input.Keys.G);
    InputMode INPUT_MODE_4_4 = new InputMode("J", "K", Input.Keys.J, Input.Keys.K);
}
