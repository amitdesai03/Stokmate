package com.stokmate.db;

import java.io.*;
import java.sql.*;
import com.google.appengine.api.utils.SystemProperty;
/**
 * Created by amdesai on 3/3/15.
 */
public class DBConnector {

    protected Connection connect()  throws Exception{
        Connection conn = null;

        String url = null;
        if (SystemProperty.environment.value() ==
                SystemProperty.Environment.Value.Production) {
            // Load the class that provides the new "jdbc:google:mysql://" prefix.
            Class.forName("com.mysql.jdbc.GoogleDriver");
            url = "jdbc:google:mysql://your-project-id:your-instance-name/guestbook?user=root";
        } else {
            // Local MySQL instance to use during development.
            Class.forName("com.mysql.jdbc.Driver");
            url = "jdbc:mysql://127.0.0.1:3306/stokmatedb?user=root";

            // Alternatively, connect to a Google Cloud SQL instance using:
            // jdbc:mysql://ip-address-of-google-cloud-sql-instance:3306/guestbook?user=root
        }

        conn = DriverManager.getConnection(url);
        return conn;
    }

    protected void disconnect(Connection conn)  throws Exception{
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
