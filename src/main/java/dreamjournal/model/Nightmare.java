package dreamjournal.model;

import java.time.LocalDate;

public class Nightmare extends DreamEntry {
    private boolean recurring;

    public Nightmare() {
        super();
    }

    public Nightmare(int id, String title, String description, int intensity, LocalDate dreamDate, boolean recurring) {
        super(id, title, description, intensity, dreamDate);
        this.recurring = recurring;
    }

    @Override
    public String getDreamType() {
        return "NIGHTMARE";
    }

    @Override
    public void analyze() {
        System.out.println("Analyzing nightmare: " + (recurring ? "This is a recurring bad dream." : "This is a one-time nightmare."));
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }
}