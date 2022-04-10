package org.elzacontiero.m3assessments.superheroes.dao;

import org.elzacontiero.m3assessments.superheroes.dto.Organization;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
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

class CharacterMapper implements RowMapper<SuperCharacter> {

    public SuperCharacter mapRow(ResultSet rs, int rowNum) throws SQLException {
        SuperCharacter character = new SuperCharacter(
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
public class CharacterDao implements EntityDaoInterface<SuperCharacter> {

    @Autowired
    private JdbcTemplate jdbc;

    public CharacterDao() {}

    public CharacterDao(DataSource ds) {
        jdbc = new JdbcTemplate(ds);
    }

    public SuperCharacter getById(long id) {
        String sql = String.format(
            "select id, name, description, superpower, character_type from characters where id=%d",
            id);
        List<SuperCharacter> someone = jdbc.query(sql, new CharacterMapper());
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
        SuperCharacter character;

        public InsertPreparedStm(SuperCharacter character) {
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
    public long insert(SuperCharacter c) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(new InsertPreparedStm(c), key);
        long id = key.getKey().longValue();
        c.setId(id);
        return id;
    }

    /**
     * List all SuperCharacters that belong to an  organisation
     * @param org A superhero/villain Organization
     * @return List of SuperCharacters
     */
    public List<SuperCharacter> getCharactersFromOrganization(Organization org) {
        String sql = String.format("select c.id, c.name, c.description, c.superpower, c.character_type "+
                                   "from characters c " +
                                   "join characters_orgs_map m " +
                                   "on c.id=m.character_id " +
                                   "where m.org_id=%d", org.getId());
        List<SuperCharacter> chars = jdbc.query(sql, new CharacterMapper());
        return chars;
    }



    public long insertRelationshipCharacterToOrganization(final SuperCharacter character, final Organization org) throws SQLException {
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                if (character.getId()<=0) {
                    throw new SQLException("Can't insert with SuperCharacter id="+character.getId());
                }
                if (org.getId()<=0) {
                    throw new SQLException("Can't insert with Organization id="+org.getId());
                }
                PreparedStatement ps = conn.prepareStatement("insert into characters_orgs_map(character_id, org_id) values (?,?)", Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, character.getId());
                ps.setLong(2, org.getId());
                return ps;
            }
        }, key);
        return key.getKey().longValue();
    }


}
