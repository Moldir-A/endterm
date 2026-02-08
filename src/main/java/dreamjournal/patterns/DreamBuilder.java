package dreamjournal.patterns;

import dreamjournal.model.LucidDream;
import dreamjournal.model.Nightmare;
import java.time.LocalDate;

public class DreamBuilder {
    private int id;
    private String title;
    private String description;
    private int intensity;
    private LocalDate dreamDate;
    private boolean userControlled;

    public DreamBuilder setId(int id) {
        this.id = id;
        return this;
    }
    public DreamBuilder setTitle(String title) {
        this.title = title;
        return this;
    }
    public DreamBuilder setDescription(String description) {
        this.description = description;
        return this;
    }
    public DreamBuilder setIntensity(int intensity) {
        this.intensity = intensity;
        return this;
    }
    public DreamBuilder setDate(LocalDate date) {
        this.dreamDate = date;
        return this;
    }
    public DreamBuilder setUserControlled(boolean controlled) {
        this.userControlled = controlled;
        return this;
    }

    public LucidDream buildLucid() {
        return new LucidDream(id, title, description, intensity, dreamDate, userControlled);
    }

    public Nightmare buildNightmare(boolean recurring) {
        return new Nightmare(id, title, description, intensity, dreamDate, recurring);
    }
}