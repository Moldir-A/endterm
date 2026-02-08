package dreamjournal.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = LucidDream.class, name = "LUCID"),
        @JsonSubTypes.Type(value = Nightmare.class, name = "NIGHTMARE")
})
public abstract class DreamEntry {
    protected int id;
    protected String title;
    protected String description;
    protected int intensity;
    protected LocalDate dreamDate;

    public DreamEntry() {}

    public DreamEntry(int id, String title, String description, int intensity, LocalDate dreamDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        setIntensity(intensity);
        this.dreamDate = dreamDate;
    }

    public abstract String getDreamType();
    public abstract void analyze();

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getIntensity() { return intensity; }

    public void setIntensity(int intensity) {
        if (intensity < 1 || intensity > 10) {
            this.intensity = (intensity < 1) ? 1 : 10;
        } else {
            this.intensity = intensity;
        }
    }

    public LocalDate getDreamDate() { return dreamDate; }
    public void setDreamDate(LocalDate dreamDate) { this.dreamDate = dreamDate; }
}