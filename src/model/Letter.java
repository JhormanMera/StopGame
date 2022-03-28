package model;

public class Letter {
    public String type="Letter";
    private String letter;

    public Letter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }
}
