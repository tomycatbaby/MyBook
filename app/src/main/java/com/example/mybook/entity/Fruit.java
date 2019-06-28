package com.example.mybook.entity;

import android.os.Handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Fruit  implements Cloneable, Serializable {
    private String name;
    private int imageId;

    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }


    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
