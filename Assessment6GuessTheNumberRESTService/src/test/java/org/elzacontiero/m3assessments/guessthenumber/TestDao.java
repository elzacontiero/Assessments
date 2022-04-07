package org.elzacontiero.m3assessments.guessthenumber;


import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.elzacontiero.m3assessments.guessthenumber.dao.GameDao;
import org.elzacontiero.m3assessments.guessthenumber.dto.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDao {

    /**
     * Retrieves a new DataSource to test database.
     * @return A new data source.
     */
    DataSource createDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setDatabaseName("bullstest");
        ds.setServerName("192.168.0.23");
        ds.setUser("elza");
        ds.setPassword("Shh!! I'll tell you a secret...");
        return ds;
    }

    @Test
    public void testCreateGame() {
        DataSource ds = createDataSource();
        GameDao dao = new GameDao(ds);
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus("INPROGRESS");
        dao.createNew(game);
        assertTrue(game.getStatus().equals("INPROGRESS"));
    }
}
