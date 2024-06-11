package xyz.manolol.pong;

import com.badlogic.gdx.Game;
import xyz.manolol.pong.screens.game.GameScreen;

public class Pong extends Game {
	public static Pong GAME;

	public void create() {
		GAME = this;

		GAME.setScreen(new GameScreen(4));
	}
}
