package model;

public class Message {
    public String type="Msg";
    private String message;


    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
