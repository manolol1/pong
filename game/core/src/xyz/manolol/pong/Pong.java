package xyz.manolol.pong;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.pong.screens.gameover.GameOverScreen;
import xyz.manolol.pong.screens.mainmenu.MainMenuScreen;

public class Pong extends Game {
	public static Pong GAME;

	public void create() {
		GAME = this;
		VisUI.load();
		GAME.setScreen(new MainMenuScreen());
	}
}