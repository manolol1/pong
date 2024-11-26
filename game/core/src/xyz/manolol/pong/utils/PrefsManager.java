package xyz.manolol.pong.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class PrefsManager {
    Preferences prefs;

    public PrefsManager() {
        prefs = Gdx.app.getPreferences("PongData");
    }

    public void setHighscore(int playerCount, int score) {
        prefs.putInteger(String.valueOf(playerCount), score);
        prefs.flush();
    }

    public int getHighscore(int playerCount) {
        return prefs.getInteger(String.valueOf(playerCount), 0);
    }

    public void setHighscoreServer(String address) {
        prefs.putString("highscore_server", address);
    }

    public String getHighscoreServer() {
        return prefs.getString("highscore_server", "https://pong.manolol.xyz");
    }
}