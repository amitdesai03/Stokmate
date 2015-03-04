package com.stokmate.db;

import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * Created by amdesai on 3/3/15.
 */
public class DBExecutor extends DBConnector{
    protected int execute(PreparedStatement ps) throws Exception{
        return ps.executeUpdate();
    }

    protected int execute(Statement st) throws Exception{
        return -1;
    }
}
