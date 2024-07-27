package com.flowerorder;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import com.flowerorder.util.DBConnection;

public class DBConnectionTest {

    @Test
    public void testGetConnection() {
        try {
            Connection connection = DBConnection.getConnection();
            assertNotNull("Connection should not be null", connection);
        } catch (SQLException e) {
            fail("SQLException should not be thrown");
        }
    }
}
