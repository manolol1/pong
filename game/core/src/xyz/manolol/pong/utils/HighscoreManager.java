package xyz.manolol.pong.utils;

public class HighscoreManager {
    private final PrefsManager prefs;

    public HighscoreManager() {
        prefs = new PrefsManager();
    }

    public void setHighscore(int playerCount, int score) {
        prefs.setHighscore(playerCount, score);
        // TODO: online stuff
    }

    public int getLocalHighscore(int playerCount) {
        return prefs.getHighscore(playerCount);
    }
}
