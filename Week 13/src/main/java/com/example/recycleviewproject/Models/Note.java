package com.example.recycleviewproject.Models;

public class Note {

    private int id;
    private String text;
    private int pictureId;
    private String buttonText;

    public Note(int id, String text, int pictureId, String buttonText) {
        this.id = id;
        this.text = text;
        this.pictureId = pictureId;
        this.buttonText = buttonText;
    }

    public Note() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPicture() {
        return pictureId;
    }

    public void setPicture(int picture) {
        this.pictureId = picture;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }
}
