package xyz.manolol.pong;

import com.badlogic.gdx.Input;
import xyz.manolol.pong.utils.ControlScheme;

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
    public static ControlScheme[][] CONTROL_SCHEMES = new ControlScheme[4][4];

    static {
        // one player
        CONTROL_SCHEMES[0][0] = new ControlScheme("A", "D", Input.Keys.A, Input.Keys.D);

        // two players
        CONTROL_SCHEMES[1][0] = new ControlScheme("A", "D", Input.Keys.A, Input.Keys.D);
        CONTROL_SCHEMES[1][1] = new ControlScheme("LeftArrow", "RightArrow", Input.Keys.LEFT, Input.Keys.RIGHT);

        // three players
        CONTROL_SCHEMES[2][0] = new ControlScheme("LeftArrow", "RightArrow", Input.Keys.LEFT, Input.Keys.RIGHT);
        CONTROL_SCHEMES[2][1] = new ControlScheme("A", "D", Input.Keys.A, Input.Keys.D);
        CONTROL_SCHEMES[2][2] = new ControlScheme("G", "J", Input.Keys.G, Input.Keys.J);

        // four players
        CONTROL_SCHEMES[3][0] = new ControlScheme("LeftArrow", "RightArrow", Input.Keys.LEFT, Input.Keys.RIGHT);
        CONTROL_SCHEMES[3][1] = new ControlScheme("A", "S", Input.Keys.A, Input.Keys.S);
        CONTROL_SCHEMES[3][2] = new ControlScheme("F", "G", Input.Keys.F, Input.Keys.G);
        CONTROL_SCHEMES[3][3] = new ControlScheme("J", "K", Input.Keys.J, Input.Keys.K);
    }
}
