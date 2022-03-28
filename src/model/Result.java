package model;

public class Result {
    public String type="Result";
    private String name;
    private String animal;
    private String place;
    private String object;

    public Result(String name, String animal, String place, String object) {
        this.name = name;
        this.animal = animal;
        this.place = place;
        this.object = object;
    }

    public String getName() {
        return name;
    }

    public String getAnimal() {
        return animal;
    }

    public String getPlace() {
        return place;
    }

    public String getObject() {
        return object;
    }
}
