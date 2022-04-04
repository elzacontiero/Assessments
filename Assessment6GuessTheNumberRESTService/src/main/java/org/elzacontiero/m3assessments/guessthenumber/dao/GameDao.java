package org.elzacontiero.m3assessments.guessthenumber.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


class DateExtractor implements RowMapper<Date>  {
    @Override
    public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getDate(1);
    }
}

// Do we need another type of annotation?
@Component
public class GameDao {

    @Autowired
    private JdbcTemplate jdbc;

    public Date dbNow() {
        DateExtractor map = new DateExtractor();
        List<Date> result = jdbc.query("select now()", map);
        return result.get(0);
    }
}
