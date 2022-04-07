package org.elzacontiero.m3assessments.guessthenumber.dao;

import org.elzacontiero.m3assessments.guessthenumber.GameStatus;
import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.elzacontiero.m3assessments.guessthenumber.dto.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
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

class GameMapper implements RowMapper<Game>  {
    @Override
    public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
        Game game = new Game();
        game.setId(rs.getLong(1));
        game.setAnswer(rs.getString(2));
        game.setStatus(rs.getString(3));
        return game;
    }
}

// Do we need another type of annotation?
@Component
public class GameDao {

    @Autowired
    private JdbcTemplate jdbc;

    /**
     * This query get the last inserted ID into the database.
     * Taken from https://www.mysqltutorial.org/mysql-last_insert_id.aspx
     * @return the last inserted ID
     */
    private long getLastInsertId() {
        List<Map<String, Object>> ids = jdbc.queryForList("SELECT LAST_INSERT_ID() id");
        BigInteger id = (BigInteger) ids.get(0).get("id");
        return id.longValue();
    }

    public Timestamp dbNow() {
        TimestampExtractor map = new TimestampExtractor();
        List<Timestamp> result = jdbc.query("select now()", map);
        return result.get(0);
    }

    public Game createNew(Game game) {
        String sql = String.format("insert into game(answer, gamestatus) values ('%s','%s')",
                game.getAnswer(), game.getStatus());
        jdbc.execute(sql);
        long id = getLastInsertId();
        game.setId(id);
        return game;
    }

    public void update(Game game) {
        String sql = String.format("update game set gamestatus='%s' where id=%d", game.getStatus(), game.getId());
        jdbc.execute(sql);
    }

    public Round insert(Round round) {
        String sql = String.format("insert into round(game_id, attempt, result, tstamp)" +
                " values (%d, '%s', '%s', now())",
                round.getGame_id(), round.getAttempt(), round.getResult());
        jdbc.execute(sql);
        long id = getLastInsertId();
        round.setId(id);
        return round;
    }

    public Game get(long id) {
        String sql = String.format("select id, answer, gamestatus from game where id=%d", id);
        List<Game> games = jdbc.query(sql, new GameMapper());
        if (games.isEmpty())  {
            return null;
        } else {
            return games.get(0);
        }
    }

    public List<Game> getAll() {
        String sql = "select id, answer, gamestatus from game order by id";
        List<Game> games = jdbc.query(sql, new GameMapper());
        return games;
    }



}
