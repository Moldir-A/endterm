package dreamjournal.service;

import dreamjournal.dto.DreamDTO;
import dreamjournal.model.DreamEntry;
import dreamjournal.patterns.DreamFactory;
import dreamjournal.repository.DreamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DreamService {

    private final DreamRepository dreamRepository;

    @Autowired
    public DreamService(DreamRepository dreamRepository) {
        this.dreamRepository = dreamRepository;
    }


    public List<DreamDTO> getAllDreams() throws SQLException {
        DreamCache cache = DreamCache.getInstance();
        String cacheKey = "all_dreams_list";


        List<DreamDTO> cachedData = cache.get(cacheKey);
        if (cachedData != null) {
            System.out.println("Возврат данных из кэша (In-Memory)...");
            return cachedData;
        }

        // Если в кэше пусто, запрашиваем БД [cite: 8]
        List<DreamEntry> entries = dreamRepository.findAll();
        List<DreamDTO> dtos = entries.stream().map(e -> {
            DreamDTO d = new DreamDTO();
            d.setTitle(e.getTitle());
            d.setDescription(e.getDescription());
            d.setType(e.getType());
            return d;
        }).collect(Collectors.toList());

        cache.put(cacheKey, dtos);
        return dtos;
    }

    public void addDream(DreamDTO dto) throws SQLException {
        DreamEntry dream = DreamFactory.createDream(
                dto.getType(), dto.getTitle(), dto.getDescription(),
                dto.getIntensity(), LocalDate.parse(dto.getDate()), dto.isExtraParam()
        );

        dreamRepository.save(dream);


        DreamCache.getInstance().invalidate();
    }

    public void delete(int id) throws SQLException {
        dreamRepository.delete(id);

        DreamCache.getInstance().invalidate();
    }
}
