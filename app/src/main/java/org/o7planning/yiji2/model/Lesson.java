package org.o7planning.yiji2.model;

public class Lesson {

    int id;
    String name;
    int sothe;
    byte[] image;
    int idbook;

    public Lesson(int id, String name, int sothe, byte[] image, int idbook) {
        this.id = id;
        this.name = name;
        this.sothe = sothe;
        this.image = image;
        this.idbook = idbook;
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
