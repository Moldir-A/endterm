package dreamjournal.model;

import dreamjournal.model.DreamEntry;

import java.time.LocalDate;

public class LucidDream extends DreamEntry {
    private boolean userControlled;

    public LucidDream() {
        super();
    }

    public LucidDream(int id, String title, String description, int intensity, LocalDate dreamDate, boolean userControlled) {
        super(id, title, description, intensity, dreamDate);
        this.userControlled = userControlled;
    }

    @Override
    public String getDreamType() {
        return "LUCID";
    }

    @Override
    public void analyze() {
        System.out.println("Analyzing lucid dream: user control level is " + (userControlled ? "high" : "low"));
    }

    public boolean isUserControlled() {
        return userControlled;
    }

    public void setUserControlled(boolean userControlled) {
        this.userControlled = userControlled;
    }
}
