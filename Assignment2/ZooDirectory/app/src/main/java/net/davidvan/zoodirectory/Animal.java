package net.davidvan.zoodirectory;

/**
 * Created by David on 9/29/2016.
 */

public class Animal {

    private String name;
    private String description;
    private String image;

    public Animal() {
        name = "";
        description = "";
        image = "";
    }

    public Animal(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

}
