package dreamjournal.controller;

import dreamjournal.model.Emotion;
import dreamjournal.service.DreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/emotions")
public class EmotionController {

    @Autowired
    private DreamService dreamService; // Или отдельный EmotionService, если создадите

    @GetMapping
    public List<Emotion> getAllEmotions() throws SQLException {
        return dreamService.getAllEmotions();
    }

    @PostMapping
    public ResponseEntity<String> addEmotion(@RequestBody Emotion emotion) throws SQLException {
        dreamService.saveEmotion(emotion);
        return new ResponseEntity<>("Emotion created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmotion(@PathVariable int id) throws SQLException {
        dreamService.deleteEmotion(id);
        return ResponseEntity.ok("Emotion deleted");
    }
}