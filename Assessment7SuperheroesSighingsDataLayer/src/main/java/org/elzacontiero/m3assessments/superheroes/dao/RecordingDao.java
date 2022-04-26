package org.elzacontiero.m3assessments.superheroes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.elzacontiero.m3assessments.superheroes.dto.Recording;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

class RecordingMapper implements RowMapper<Recording> {

    @Autowired
    CharacterDao characterDao;

    public Recording mapRow(ResultSet rs, int rowNum) throws SQLException {
        long characterId = rs.getLong("character_id");
        // SuperCharacter character = characterDao.getById(characterId);
        Recording rec = new Recording(
            rs.getLong("id"),
            characterId,
            rs.getString("address"),
            rs.getBigDecimal("latitude").doubleValue(),
            rs.getBigDecimal("longitude").doubleValue(),
            rs.getTimestamp("time_of_sight")
        );
        return rec;
    }
}

@Component
public class RecordingDao implements EntityDaoInterface<Recording> {

    @Autowired
    private JdbcTemplate jdbc;

    public RecordingDao() { }

    public RecordingDao(DataSource ds) {
        this.jdbc = new JdbcTemplate(ds);
    }

    @Override
    public Recording getById(long id) {
        String sql = String.format("select id, character_id, address, latitude, longitude, time_of_sight "+
            "from recordings where id=%d", id);
        System.out.println("SQL="+sql);

        List<Recording> recs = jdbc.query(sql, new RecordingMapper());
        if (recs.size()==1) {
            return recs.get(0);
        }
        return null;
    }

    @Override
    public List<Recording> getAll() {
        return jdbc.query("select id, character_id, address, latitude, longitude, time_of_sight from recordings",
                new RecordingMapper());
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
            String sql = "insert into recordings(character_id, address, latitude, longitude, time_of_sight) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, recording.getCharacterId());
            ps.setString(2, recording.getAddress());
            ps.setDouble(3, recording.getLatitude());
            ps.setDouble(4, recording.getLongitude());
            ps.setTimestamp(5, recording.getTimeOfSight());
            return ps;
        }
    }

    @Override
    public long insert(Recording rec) {
        InsertPreparedStm ps = new InsertPreparedStm(rec);
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(ps, key);
        long id = key.getKey().longValue();
        rec.setId(id);
        return id;
    }

    @Override
    public void update(Recording rec) {
        jdbc.update("update recordings set address=?, latitude=?, longitude=? where id=?",
            rec.getAddress(),
            rec.getLatitude(),
            rec.getLongitude(),
            rec.getId()
        );
    }

}
