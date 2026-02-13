package dreamjournal.patterns;

import dreamjournal.model.DreamEntry;
import java.util.List;
import java.util.Optional;

public class DreamCache {
    private static DreamCache instance;
    private List<DreamEntry> cachedDreams;
    private DreamCache() {}

    public static synchronized DreamCache getInstance() {
        if (instance == null) {
            instance = new DreamCache();
        }
        return instance;
    }

    public Optional<List<DreamEntry>> getDreams() {
        return Optional.ofNullable(cachedDreams);
    }

    public void setDreams(List<DreamEntry> dreams) {
        this.cachedDreams = dreams;
    }

    public void clear() {
        this.cachedDreams = null;
    }
}