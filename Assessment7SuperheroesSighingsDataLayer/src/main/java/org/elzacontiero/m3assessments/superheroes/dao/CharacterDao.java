package org.elzacontiero.m3assessments.superheroes.dao;

import org.elzacontiero.m3assessments.superheroes.dto.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

class CharacterMapper implements RowMapper<Character> {

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

    public CharacterDao() {}

    public CharacterDao(DataSource ds) {
        jdbc = new JdbcTemplate(ds);
    }

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

    public void delete(long id) {
        jdbc.update("delete from characters where id=?", id);
    }


    class InsertPreparedStm implements PreparedStatementCreator {
        Character character;

        public InsertPreparedStm(Character character) {
            this.character = character;
        }

        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
             String sql = "insert into characters(name, description, superpower, character_type) values (?,?,?,?)";
             // See https://docs.oracle.com/en/java/javase/14/docs/api/java.sql/java/sql/Connection.html#prepareStatement(java.lang.String,int)
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
             ps.setString(1, character.getName());
             ps.setString(2, character.getDescription());
             ps.setString(3, character.getSuperpower());
             ps.setString(4, character.getCharacterType());
             return ps;
       }
    }

    /**
     * When an insert is done, what was the ID generated in the database for that insert?
     * The JDBCTemplate way of retrieving an inserted ID is to create a PreparedStatementCreator.
     * https://www.developinjava.com/spring/retrieve-auto-generated-key/
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/PreparedStatementCreator.html
     *
     * */
    public long insert(Character c) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(new InsertPreparedStm(c), key);
        return key.getKey().longValue();
    }
}
