package com.example.riderapp;

import android.widget.Button;

public class model {

     private String discription,bikemilage,stay,food,date,image,post_id,login_id;

    public model(String discription, String bikemilage, String stay, String food, String date, String image, String post_id, String login_id) {
        this.discription = discription;
        this.bikemilage = bikemilage;
        this.stay = stay;
        this.food = food;
        this.date = date;
        this.image = image;
        this.post_id = post_id;
        this.login_id = login_id;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getBikemilage() {
        return bikemilage;
    }

    public void setBikemilage(String bikemilage) {
        this.bikemilage = bikemilage;
    }

    public String getStay() {
        return stay;
    }

    public void setStay(String stay) {
        this.stay = stay;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
}
