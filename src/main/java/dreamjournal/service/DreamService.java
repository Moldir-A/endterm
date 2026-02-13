package dreamjournal.service;

import dreamjournal.dto.DreamDTO;
import dreamjournal.model.DreamEntry;
import dreamjournal.model.Emotion;
import dreamjournal.patterns.DreamCache;
import dreamjournal.patterns.DreamFactory;
import dreamjournal.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class DreamService {

    @Autowired
    private DreamRepository dreamRepository;

    private final DreamCache cache = DreamCache.getInstance();

    public List<DreamEntry> getAll() throws SQLException
        return cache.getDreams().orElseGet(() -> {
            try {
                List<DreamEntry> dreams = dreamRepository.findAll();
                cache.setDreams(dreams);
                return dreams;
            } catch (SQLException e) {
                throw new RuntimeException("Database error during cache fill", e);
            }
        });
    }

    public void createDream(DreamDTO dto) throws SQLException {
        DreamEntry dream = DreamFactory.createDream(
                dto.getType(), dto.getTitle(), dto.getDescription(),
                dto.getIntensity(), LocalDate.parse(dto.getDate()), dto.isExtraParam()
        );
        dreamRepository.save(dream);
        cache.clear();
    }

    public void update(int id, DreamDTO dto) throws SQLException {
        DreamEntry updatedDream = DreamFactory.createDream(
                dto.getType(), dto.getTitle(), dto.getDescription(),
                dto.getIntensity(), LocalDate.parse(dto.getDate()), dto.isExtraParam()
        );
        updatedDream.setId(id);
        dreamRepository.update(updatedDream);
        cache.clear();
    }

    public void delete(int id) throws SQLException {
        dreamRepository.delete(id);
        cache.clear();
    }

    public List<Emotion> getAllEmotions() { return dreamRepository.findAllEmotions(); }
    public void saveEmotion(Emotion emotion) throws SQLException { dreamRepository.saveEmotion(emotion); }
    public void deleteEmotion(int id) throws SQLException { dreamRepository.deleteEmotion(id); }
}}