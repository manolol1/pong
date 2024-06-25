package xyz.manolol.pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.kotcrab.vis.ui.VisUI;
import xyz.manolol.pong.screens.mainmenu.MainMenuScreen;
import xyz.manolol.pong.screens.start.StartScreen;

public class Pong extends Game {
	public static Pong GAME;

	private Music music;

	public void create() {
		GAME = this;
		VisUI.load();
		Constants.load();
		GAME.setScreen(new MainMenuScreen());

		music = Gdx.audio.newMusic(Gdx.files.internal("music/pong.ogg"));
		music.setLooping(true);
		music.play();
	}

	public void dispose() {
		super.dispose();
		VisUI.dispose();
		music.dispose();
	}
}