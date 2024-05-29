package com.example.javaquizapp.Domain;

import java.util.Objects;

public class UserModel {
    private int id;
    private String name;
    private String pic;
    private int score;

    // Constructor
    public UserModel(int id, String name, String pic, int score) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.score = score;
    }

    // Getters and Setters
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // toString method
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", score=" + score +
                '}';
    }

    // equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                score == userModel.score &&
                Objects.equals(name, userModel.name) &&
                Objects.equals(pic, userModel.pic);
    }

    // hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name, pic, score);
    }
}