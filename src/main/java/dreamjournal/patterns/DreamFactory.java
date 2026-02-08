package dreamjournal.patterns;

import dreamjournal.model.DreamEntry;
import java.time.LocalDate;

public class DreamFactory {
    public static DreamEntry createDream(String type, String title, String desc, int intensity, LocalDate date, boolean extra) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Dream type cannot be null or empty");
        }

        DreamBuilder builder = new DreamBuilder()
                .setTitle(title)
                .setDescription(desc)
                .setIntensity(intensity)
                .setDate(date);

        if ("LUCID".equalsIgnoreCase(type)) {
            return builder.setUserControlled(extra).buildLucid();
        } else if ("NIGHTMARE".equalsIgnoreCase(type)) {
            return builder.buildNightmare(extra);
        }

        throw new IllegalArgumentException("Unknown dream type: " + type);
    }
}