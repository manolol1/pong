package xyz.manolol.pong.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;

import java.util.concurrent.CompletableFuture;

public class HighscoreManager {
    private final PrefsManager prefs;

    public HighscoreManager() {
        prefs = new PrefsManager();
    }

    public CompletableFuture<Void> setHighscore(int playerCount, int score) {
        CompletableFuture<Void> future = new CompletableFuture<>();

        // set online highscore
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET)
                .url(prefs.getHighscoreServer() + "/setHighscore/" + playerCount + "?score=" + score).build();
        Net.HttpResponseListener httpResponseListener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HighscoreManager", "Online Highscore set successfully to " + score);
                future.complete(null);
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.error("HighscoreManager", "Failed to set online highscore: " + t.getMessage());
                future.completeExceptionally(t);
            }

            @Override
            public void cancelled() {
                Gdx.app.error("HighscoreManager", "Online Highscore set request cancelled");
                future.cancel(true);
            }
        };
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);

        // set local highscore
        if (score > getLocalHighscore(playerCount)) {
            Gdx.app.log("HighscoreManager", "New local highscore: " + score);
            prefs.setHighscore(playerCount, score);
        }

        return future;
    }

    public int getLocalHighscore(int playerCount) {
        return prefs.getHighscore(playerCount);
    }

    public CompletableFuture<Integer> getOnlineHighscore(int playerCount) {
        CompletableFuture<Integer> futureHighscore = new CompletableFuture<>();
        HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET)
                .url(prefs.getHighscoreServer() + "/highscore/" + playerCount).build();
        Net.HttpResponseListener httpResponseListener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Gdx.app.log("HighscoreManager", "Online Highscore response: " + response);
                futureHighscore.complete(Integer.parseInt(response));
            }

            @Override
            public void failed(Throwable t) {
                futureHighscore.completeExceptionally(t);
            }

            @Override
            public void cancelled() {
                futureHighscore.cancel(true);
            }
        };
        Gdx.net.sendHttpRequest(httpRequest, httpResponseListener);

        return futureHighscore;
    }
}
