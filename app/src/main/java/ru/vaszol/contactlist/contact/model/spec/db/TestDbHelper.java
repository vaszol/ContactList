package ru.vaszol.contactlist.contact.model.spec.db;

/**
 * Created by vas on 09.08.2015.
 */
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;



public class TestDbHelper {
    private static final String DB_NAME = "unit_tests.db";
    private ConnectionSource connectionSource;
    private String databaseUrl = "jdbc:sqlite:" + DB_NAME;

    // create a connection source to our database

    public ConnectionSource getConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(databaseUrl);
            return connectionSource;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}