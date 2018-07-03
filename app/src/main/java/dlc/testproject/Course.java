package dlc.testproject;

import java.io.Serializable;

public class Course implements Serializable
{
    String name, description, img;

    public Course(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Course(String name, String description, String img)
    {
        this.name = name;
        this.description = description;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
