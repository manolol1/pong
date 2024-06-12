package xyz.manolol.pong.screens.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import xyz.manolol.pong.Constants;

public class PlayerManager {
    private final Array<Player> players = new Array<>();

    public PlayerManager(int playerCount) {
        // set up players
        if (playerCount >= 1) {
            players.add(new Player((Constants.WORLD_WIDTH / 2) - (Constants.PLAYER_LENGTH / 2), Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, Constants.PLAYER_LENGTH, Constants.PLAYER_WIDTH, MovementType.LEFT_RIGHT));
        }
        if (playerCount >= 2) {
            players.add(new Player((Constants.WORLD_WIDTH / 2) - (Constants.PLAYER_LENGTH / 2), Constants.WORLD_HEIGHT - Constants.PLAYER_WIDTH - Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, Constants.PLAYER_LENGTH, Constants.PLAYER_WIDTH, MovementType.LEFT_RIGHT));
        }
        if (playerCount >= 3) {
            players.add(new Player(Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, (Constants.WORLD_HEIGHT / 2) - (Constants.PLAYER_LENGTH / 2), Constants.PLAYER_WIDTH, Constants.PLAYER_LENGTH, MovementType.UP_DOWN));
        }
        if (playerCount >= 4) {
            players.add(new Player(Constants.WORLD_WIDTH - Constants.PLAYER_WIDTH - Constants.PLAYER_DISTANCE_TO_SCREEN_BORDER, (Constants.WORLD_HEIGHT / 2) - (Constants.PLAYER_LENGTH / 2), Constants.PLAYER_WIDTH, Constants.PLAYER_LENGTH, MovementType.UP_DOWN));
        }

        // set control schemes
        for (int i = 0; i < playerCount; i++) {
            players.get(i).setControlScheme(Constants.CONTROL_SCHEMES[playerCount - 1][i]);
        }
    }

    public void draw(ShapeRenderer shapeRenderer) {
        for (Player player : players) {
            player.draw(shapeRenderer);
        }
    }

    public void update(float delta) {
        for (Player player : players) {
            player.update(delta);
        }
    }

    public Array<Player> getPlayers() {
        return players;
    }
}
