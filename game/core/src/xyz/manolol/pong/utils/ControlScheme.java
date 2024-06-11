package xyz.manolol.pong.utils;

public class ControlScheme {
    String leftText, rightText;
    int leftKey, rightKey;

    public ControlScheme(String leftText, String rightText, int leftKey, int rightKey) {
        this.leftText = leftText;
        this.rightText = rightText;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }
}
