package ru.job4j.pooh;

public class MessagePojo {
    private String mode;
    private String text;

    public MessagePojo() {
    }

    public MessagePojo(String mode, String text) {
        this.mode = mode;
        this.text = text;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
