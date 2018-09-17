package com.wankys.www.swadeshurja.Models;

/**
 * Created by Elakiya on 5/2/2018.
 */

public class Sliders {
    public int[] Image;

    public String[] Name;

    public int[] getImage() {
        return Image;
    }

    public void setImage(int[] image) {
        Image = image;
    }

    public String[] getName() {
        return Name;
    }

    public void setName(String[] name) {
        Name = name;
    }

    public Sliders(int[] image, String[] name) {
        Image = image;
        Name = name;
    }

}
