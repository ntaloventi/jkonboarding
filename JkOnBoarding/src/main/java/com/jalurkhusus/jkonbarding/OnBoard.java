package com.jalurkhusus.jkonbarding;

public class OnBoard {

    private int drawable;
    private String title;
    private String body;

    public OnBoard(int drawable, String title, String body){
        this.drawable = drawable;
        this.title = title;
        this.body = body;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
