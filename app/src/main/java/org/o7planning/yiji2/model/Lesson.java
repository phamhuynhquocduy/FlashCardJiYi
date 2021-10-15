package org.o7planning.yiji2.model;

import java.io.Serializable;
import java.util.Arrays;

public class Lesson implements Serializable {

    int id;
    String name;
    int sothe;
    byte[] image;
    int idbook;
    int countLearn;

    public Lesson(int id, String name, int sothe, byte[] image, int idbook, int countLearn) {
        this.id = id;
        this.name = name;
        this.sothe = sothe;
        this.image = image;
        this.idbook = idbook;
        this.countLearn = countLearn;
    }

    public int getCountLearn() {
        return countLearn;
    }

    public void setCountLearn(int countLearn) {
        this.countLearn = countLearn;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSothe() {
        return sothe;
    }

    public void setSothe(int sothe) {
        this.sothe = sothe;
    }

    public int getIdbook() {
        return idbook;
    }

    public void setIdbook(int idbook) {
        this.idbook = idbook;
    }
}
