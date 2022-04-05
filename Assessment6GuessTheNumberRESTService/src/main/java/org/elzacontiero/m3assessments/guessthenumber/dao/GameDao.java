package org.elzacontiero.m3assessments.guessthenumber.dao;

import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


class TimestampExtractor implements RowMapper<Timestamp> {
    @Override
    public Timestamp mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getTimestamp(1);
    }
}

// Do we need another type of annotation?
@Component
public class GameDao {

    @Autowired
    private JdbcTemplate jdbc;

    public Timestamp dbNow() {
        TimestampExtractor map = new TimestampExtractor();
        List<Timestamp> result = jdbc.query("select now()", map);
        return result.get(0);
    }

    public Game createNew(Game game) {
        String sql = String.format("insert into game(answer, gamestatus) "+
            "values ('%s',%d)", game.getAnswer(), game.getStatus());
        jdbc.execute(sql);
        List<Map<String, Object>> ids = jdbc.queryForList("SELECT LAST_INSERT_ID() id");
        Integer id = (Integer) ids.get(0).get("id");
        game.setId(id);
        return game;
    }
}
