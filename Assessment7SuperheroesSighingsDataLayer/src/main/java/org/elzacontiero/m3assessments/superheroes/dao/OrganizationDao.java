package org.elzacontiero.m3assessments.superheroes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.elzacontiero.m3assessments.superheroes.dto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
    public void delete(long id) {
        jdbc.update("delete from organizations where id=?", id);
    }

    public long insert(Organization x) {
        jdbc.update("insert into organizations(name, description, address) values (?,?,?)",
            x.getName(), x.getDescription(), x.getAddress());
        long id = DaoUtils.getLastInsertId(jdbc);
        x.setId(id);
        return id;
    }

}
