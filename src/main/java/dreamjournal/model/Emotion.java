package dreamjournal.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Emotion {
    private int id;
    private String name;

    public Emotion() {}

    public Emotion(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emotion{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}