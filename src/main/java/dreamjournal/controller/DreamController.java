package dreamjournal.controller;

import dreamjournal.model.DreamEntry;
import dreamjournal.dto.DreamDTO;
import dreamjournal.service.DreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/dreams")
public class DreamController {

    @Autowired
    private DreamService dreamService;

    @GetMapping
    public List<DreamEntry> getAllDreams() throws SQLException {
        return dreamService.getAll();
    }
    @PutMapping("/{id}")
    public String update(@PathVariable int id, @RequestBody DreamDTO dto) throws SQLException {
        dto.setId(id);
        dreamService.update(id, dto);
        return "Updated";
    }

    @PostMapping
    public ResponseEntity<String> addDream(@RequestBody DreamDTO dto) throws SQLException {
        dreamService.createDream(dto);
        return new ResponseEntity<>("Dream added successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDream(@PathVariable int id) throws SQLException {
        dreamService.delete(id);
        return ResponseEntity.ok("Dream deleted");
    }
}