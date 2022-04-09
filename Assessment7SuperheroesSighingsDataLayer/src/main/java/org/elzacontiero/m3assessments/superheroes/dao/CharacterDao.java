package org.elzacontiero.m3assessments.superheroes.dao;

import org.elzacontiero.m3assessments.superheroes.dto.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;

class CharacterMapper implements RowMapper<Character> {

    @Override
    public Character mapRow(ResultSet rs, int rowNum) throws SQLException {
        Character character = new Character(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("superpower"),
            rs.getString("character_type")
        );
        return character;
    }
}

@Component
public class CharacterDao implements EntityDaoInterface<Character> {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Character getById(long id) {
        String sql = String.format(
            "select id, name, description, superpower, character_type from characters where id=%d",
            id);
        List<Character> someone = jdbc.query(sql, new CharacterMapper());
        if (someone.size()==1) {
            return someone.get(0);
        }
        else {
            return null;
        }
    }

    // public void update(Character x) {
    // }

    @Override
    public void delete(long id) {
        String sql = String.format("delete from characters where id=%d", id);
        jdbc.execute(sql);
    }

    @Override
    public long insert(Character c) {
        String sql = String.format("insert into character(name, description, superpower, character_type)" +
            " values ('%s','%s','%s','%s')",
            c.getName(), c.getDescription(), c.getSuperpower(), c.getCharacterType()
        );
        jdbc.execute(sql);
        long id = DaoUtils.getLastInsertId(jdbc);
        c.setId(id);
        return id;
    }

}
