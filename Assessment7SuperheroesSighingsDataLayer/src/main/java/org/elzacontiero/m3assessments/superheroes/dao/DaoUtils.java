package org.elzacontiero.m3assessments.superheroes.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public class DaoUtils {
    /**
    * This query get the last inserted ID into the database.
    * Taken from https://www.mysqltutorial.org/mysql-last_insert_id.aspx
    * @return the last inserted ID
    */
   public static long getLastInsertId(JdbcTemplate jdbc) {
       List<Map<String, Object>> ids = jdbc.queryForList("SELECT LAST_INSERT_ID() id");
       BigInteger id = (BigInteger) ids.get(0).get("id");
       return id.longValue();
   }

}
