package xyz.manolol.pong;

import com.badlogic.gdx.Game;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.pong.screens.mainmenu.MainMenuScreen;
import xyz.manolol.pong.screens.start.StartScreen;

public class Pong extends Game {
	public static Pong GAME;

	public void create() {
		GAME = this;
		VisUI.load();
		Constants.load();
		GAME.setScreen(new MainMenuScreen());
	}
}