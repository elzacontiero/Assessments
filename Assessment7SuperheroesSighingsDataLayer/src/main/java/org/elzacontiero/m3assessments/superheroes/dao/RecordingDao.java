package org.elzacontiero.m3assessments.superheroes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.elzacontiero.m3assessments.superheroes.dto.Recording;
import org.elzacontiero.m3assessments.superheroes.dto.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

class RecordingMapper implements RowMapper<Recording> {

    @Autowired
    CharacterDao characterDao;

    public Recording mapRow(ResultSet rs, int rowNum) throws SQLException {
        long characterId = rs.getLong("character_id");
        Character character = characterDao.getById(characterId);
        Recording rec = new Recording(
            rs.getLong("id"),
            character,
            rs.getString("address"),
            rs.getBigDecimal("latitude").doubleValue(),
            rs.getBigDecimal("longitude").doubleValue(),
            rs.getTimestamp("time_of_sight")
        );
        return rec;
    }
}


public class RecordingDao implements EntityDaoInterface<Recording> {

    @Autowired
    private JdbcTemplate jdbc;

    public Recording getById(long id) {
        String sql = String.format("select id, character_id, address, latitude, longitude, time_of_sight "+
            "from recordings where id=%d", id);
        jdbc.query(sql, new RecordingMapper());
        return null;
    }

    public void delete(long id) {
        jdbc.update("delete from recordings where id=?", id);
    }

    public long insert(Recording rec) {
        String sql = "insert into recordings(character_id, address, latitude, longitude, time_of_sight) values(?, ?, ?, ?, ?)";
        jdbc.update(sql, rec.getCharacter().getId(), rec.getAddress(), rec.getLatitude(), rec.getLongitude(), rec.getTimeOfSight());
        long id = DaoUtils.getLastInsertId(jdbc);
        rec.setId(id);
        return id;
    }

}
