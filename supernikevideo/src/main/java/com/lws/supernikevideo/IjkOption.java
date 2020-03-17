package com.lws.supernikevideo;

public class IjkOption {

    int category;
    String name;
    Object value;

    public IjkOption(int category, String name, Object value) {
        this.category = category;
        this.name = name;
        this.value = value;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
