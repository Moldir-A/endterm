package dreamjournal.dto;

public class DreamDTO {
    private int id;
    private String title;
    private String description;
    private int intensity;
    private String date;
    private String type;
    private boolean extraParam;

    public DreamDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getIntensity() { return intensity; }
    public void setIntensity(int intensity) { this.intensity = intensity; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isExtraParam() { return extraParam; }
    public void setExtraParam(boolean extraParam) { this.extraParam = extraParam; }
}