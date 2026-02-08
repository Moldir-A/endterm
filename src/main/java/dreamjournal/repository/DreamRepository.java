package dreamjournal.repository;

import dreamjournal.model.DreamEntry;
import dreamjournal.model.Emotion;
import dreamjournal.patterns.DreamFactory;
import dreamjournal.utils.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DreamRepository {

    public List<DreamEntry> findAll() throws SQLException {
        List<DreamEntry> dreams = new ArrayList<>();
        String sql = "SELECT * FROM dreams";

        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("type");
                String title = rs.getString("title");
                String desc = rs.getString("description");
                int intensity = rs.getInt("intensity");
                Date sqlDate = rs.getDate("dream_date");
                boolean extra = rs.getBoolean("extra_param");

                DreamEntry dream = DreamFactory.createDream(
                        type, title, desc, intensity,
                        sqlDate != null ? sqlDate.toLocalDate() : null,
                        extra
                );

                dream.setId(rs.getInt("id"));
                dreams.add(dream);
            }
        }
        return dreams;
    }

    public void save(DreamEntry dream) throws SQLException {
        String sql = "INSERT INTO dreams (type, title, description, intensity, dream_date, extra_param) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dream.getDreamType());
            pstmt.setString(2, dream.getTitle());
            pstmt.setString(3, dream.getDescription());
            pstmt.setInt(4, dream.getIntensity());
            pstmt.setDate(5, java.sql.Date.valueOf(dream.getDreamDate()));

            boolean extra = false;
            if (dream instanceof dreamjournal.model.LucidDream) {
                extra = ((dreamjournal.model.LucidDream) dream).isUserControlled();
            } else if (dream instanceof dreamjournal.model.Nightmare) {
                extra = ((dreamjournal.model.Nightmare) dream).isRecurring();
            }
            pstmt.setBoolean(6, extra);

            pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM dreams WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public List<Emotion> findAllEmotions() {
        List<Emotion> emotions = new ArrayList<>();
        String sql = "SELECT * FROM emotions";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                emotions.add(new Emotion(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emotions;
    }

    public void update(DreamEntry dream) throws SQLException {
        String sql = "UPDATE dreams SET type = ?, title = ?, description = ?, intensity = ?, dream_date = ?, extra_param = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dream.getDreamType());
            pstmt.setString(2, dream.getTitle());
            pstmt.setString(3, dream.getDescription());
            pstmt.setInt(4, dream.getIntensity());
            pstmt.setDate(5, java.sql.Date.valueOf(dream.getDreamDate()));

            boolean extra = false;
            if (dream instanceof dreamjournal.model.LucidDream) {
                extra = ((dreamjournal.model.LucidDream) dream).isUserControlled();
            } else if (dream instanceof dreamjournal.model.Nightmare) {
                extra = ((dreamjournal.model.Nightmare) dream).isRecurring();
            }
            pstmt.setBoolean(6, extra);
            pstmt.setInt(7, dream.getId());

            pstmt.executeUpdate();
        }
    }

    public void saveEmotion(Emotion emotion) throws SQLException {
        String sql = "INSERT INTO emotions (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, emotion.getName());
            pstmt.executeUpdate();
        }
    }

    public void deleteEmotion(int id) throws SQLException {
        String sql = "DELETE FROM emotions WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}