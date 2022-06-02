package com.example.alwaqr;

public class item {
    private String id;
    private String code;
    private String price;
    private String image;

    public item(){

    }

    public item(String id, String code, String price, String image) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "item{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
