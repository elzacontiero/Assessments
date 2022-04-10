package org.elzacontiero.m3assessments.superheroes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.elzacontiero.m3assessments.superheroes.dto.Recording;
import org.elzacontiero.m3assessments.superheroes.dto.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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


    class InsertPreparedStm implements PreparedStatementCreator {
        Recording recording;
        public InsertPreparedStm(Recording rec) {
            this.recording = rec;
        }

        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
            String sql = "insert into recordings(character_id, address, latitude, longitude, time_of_sight) values(?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, recording.getCharacter().getId());
            ps.setString(1, recording.getAddress());
            ps.setDouble(1, recording.getLatitude());
            ps.setDouble(1, recording.getLongitude());
            ps.setTimestamp(1, recording.getTimeOfSight());
            return ps;
        }
    }

    public long insert(Recording rec) {
        InsertPreparedStm ps = new InsertPreparedStm(rec);
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(ps, key);
        long id = key.getKey().longValue();
        rec.setId(id);
        return id;
    }

}
