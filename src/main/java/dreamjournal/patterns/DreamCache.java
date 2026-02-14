package dreamjournal.patterns;

import dreamjournal.dto.DreamDTO;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DreamCache {
    private static DreamCache instance;

    private final Map<String, List<DreamDTO>> cache = new ConcurrentHashMap<>();

    private DreamCache() {}

    public static synchronized DreamCache getInstance() {
        if (instance == null) {
            instance = new DreamCache();
        }
        return instance;
    }

    public void put(String key, List<DreamDTO> data) {
        cache.put(key, data);
    }

    public List<DreamDTO> get(String key) {
        return cache.get(key);
    }

    public void invalidate() {
        cache.clear();
        System.out.println("Cache invalidated after update/delete operation.");
    }
}
