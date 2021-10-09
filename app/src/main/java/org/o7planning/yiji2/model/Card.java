package org.o7planning.yiji2.model;

import java.io.Serializable;
import java.util.Arrays;

public class Card implements Serializable {

    int id;
    byte [] gif;
    byte [] image;
    String music;
    byte [] interpret;
    int idlesson;
    int idbook;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", gif=" + Arrays.toString(gif) +
                ", image=" + Arrays.toString(image) +
                ", music='" + music + '\'' +
                ", interpret=" + Arrays.toString(interpret) +
                ", idlesson=" + idlesson +
                ", idbook=" + idbook +
                '}';
    }

    public Card(int id, byte[] gif, byte[] image, String music, byte[] interpret, int idlesson, int idbook) {
        this.id = id;
        this.gif = gif;
        this.image = image;
        this.music = music;
        this.interpret = interpret;
        this.idlesson = idlesson;
        this.idbook = idbook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getGif() {
        return gif;
    }

    public void setGif(byte[] gif) {
        this.gif = gif;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public byte[] getInterpret() {
        return interpret;
    }

    public void setInterpret(byte[] interpret) {
        this.interpret = interpret;
    }

    public int getIdlesson() {
        return idlesson;
    }

    public void setIdlesson(int idlesson) {
        this.idlesson = idlesson;
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

}
