package nl.allergieradar.persistence;

import nl.allergieradar.service.DatabaseService;

import javax.inject.Singleton;
import java.sql.Connection;

/**
 * Created by bernd on 16-5-2017.
 */
public abstract class DatabaseDAO {
    /**
     * The Conn.
     */
    @Singleton
    protected Connection conn;

    /**
     * Instantiates a new Database dao.
     *
     * @throws Exception the exception
     */
    public DatabaseDAO() throws Exception {
        this.conn = DatabaseService.getInstance().getConnection("IPSEN3G10", "1");

    }
}
