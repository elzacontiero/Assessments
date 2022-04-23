package org.elzacontiero.m3assessments.superheroes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.elzacontiero.m3assessments.superheroes.dto.Organization;
import org.elzacontiero.m3assessments.superheroes.dto.SuperCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

class OrganizationMapper implements RowMapper<Organization> {

    public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
        Organization org = new Organization(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getString("address")
        );
        return org;
    }
}

@Component
public class OrganizationDao implements EntityDaoInterface<Organization> {

    @Autowired
    private JdbcTemplate jdbc;

    public OrganizationDao() {}

    public OrganizationDao(DataSource ds) {
        jdbc = new JdbcTemplate(ds);
    }

    @Override
    public Organization getById(long id) {
        String sql = String.format(
            "select id, name, description, address from organizations where id=%d",
            id);
        List<Organization> someone = jdbc.query(sql, new OrganizationMapper());
        // There should be only one for a given ID, or zero, if the id doesn't exist.
        if (someone.size()==1) {
            return someone.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public List<Organization> getAll() {
        return jdbc.query("select id, name, description, address from organizations",
            new OrganizationMapper());
    }


    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
    public void delete(long id) {
        jdbc.update("delete from organizations where id=?", id);
    }

    public long insert(Organization org) {
        InsertPreparedStm ps = new InsertPreparedStm(org);
        KeyHolder key = new GeneratedKeyHolder();
        jdbc.update(ps, key);
        org.setId(key.getKey().longValue());
        return org.getId();
    }

    class InsertPreparedStm implements PreparedStatementCreator {

        Organization organization;

        public InsertPreparedStm(Organization org) {
            this.organization = org;
        }

        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
            String sql = "insert into organizations(name, description, address) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, organization.getName());
            ps.setString(2, organization.getDescription());
            ps.setString(3, organization.getAddress());
            return ps;
        }
    }

    /**
     * Get organizations this Character belongs to.
     */
    public List<Organization> getOrganizations(SuperCharacter character) {
        String sql = String.format("select o.id, o.name, o.description, o.address from organizations o " +
                     "join characters_orgs_map m " +
                     "on m.org_id=o.id " +
                     "and m.character_id=%d", character.getId());
        List<Organization> orgs = jdbc.query(sql, new OrganizationMapper());
        return orgs;
    }

    @Override
    public void update(Organization org) {
        jdbc.update("update organizations set name=?, description=?, address=? where id=?",
            org.getName(), org.getDescription(), org.getAddress(), org.getId());
    }

}
