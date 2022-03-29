package model;

import java.util.Collection;

public class Result {
    public String type="Result";
    private String name;
    private String animal;
    private String place;
    private String object;
    private String namePoints;
    private String animalPoints;
    private String placePoints;
    private String objectPoints;
    private String totalPoints;

    public Result(String name, String animal, String place, String object) {
        this.name = name;
        this.animal = animal;
        this.place = place;
        this.object=object;
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

    public String getNamePoints() {return namePoints;}

    public void setNamePoints(String namePoints) {this.namePoints = namePoints;}

    public String getAnimalPoints() {return animalPoints;}

    public void setAnimalPoints(String animalPoints) {this.animalPoints = animalPoints;}

    public String getPlacePoints() {return placePoints; }

    public void setPlacePoints(String placePoints) {this.placePoints = placePoints;}

    public String getObjectPoints() {return objectPoints;}

    public void setObjectPoints(String objectPoints) {this.objectPoints = objectPoints;}

    public String getTotalPoints() {return totalPoints; }

    public void setTotalPoints() {
        totalPoints=String.valueOf(Integer.parseInt(namePoints)+Integer.parseInt(animalPoints)+Integer.parseInt(placePoints)+Integer.parseInt(objectPoints));
    }
}
