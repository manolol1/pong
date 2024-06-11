package xyz.manolol.pong;

public class InputMode {
    String leftText, rightText;
    int leftKey, rightKey;

    public InputMode(String leftText, String rightText, int leftKey, int rightKey) {
        this.leftText = leftText;
        this.rightText = rightText;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }
}
