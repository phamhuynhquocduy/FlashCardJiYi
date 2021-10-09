package org.o7planning.yiji2.model;

public class Book {

    int id;
    String name;
    int lesson;
    int price;
    byte[] image;

    public Book(int id, String name, int lesson, int price, byte[] image) {
        this.id = id;
        this.name = name;
        this.lesson = lesson;
        this.price = price;
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

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
