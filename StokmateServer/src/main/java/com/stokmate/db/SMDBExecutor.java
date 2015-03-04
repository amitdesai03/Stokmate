package com.stokmate.db;

import com.stokmate.backend.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by amdesai on 3/3/15.
 */
public class SMDBExecutor extends DBExecutor{

    private static final String GET_GROUP="select name,admin from groups where id=?";

    public Group getGroup( long id)  throws Exception{
        Connection conn = null;
        Group group = null;
        try {
            conn = connect();
            PreparedStatement ps = conn.prepareStatement(GET_GROUP);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            boolean found = false;


            if (rs.next()) {
                String name = rs.getString("name");
                String admin = rs.getString("admin");
                group = new Group(id, name, admin);

            }
        }finally {

            disconnect(conn);
        }
        return group;
    }

//
//    public Response addGroup(Group group) throws Exception{
//        int index = groups.indexOf(group);
//        if (index != -1) throw new Exception("Group record already exists");
//        groups.add(group);
//
//        Response response = new Response();
//        response.setStatus(0);
//        response.setMessage("SUCCESS");
//        return response;
//    }
//
//
//    public Response updateGroup(Group group) throws Exception{
//        int index = groups.indexOf(group);
//        if (index == -1)
//            throw new Exception("Group record does not exist");
//        Group currentGroup = groups.get(index);
//        currentGroup.setName(group.getName());
//        currentGroup.setAdmin(group.getAdmin());
//
//        Response response = new Response();
//        response.setStatus(0);
//        response.setMessage("SUCCESS");
//        return response;
//    }
//
//
//    public Response removeGroup( long id) throws Exception{
//        int index = groups.indexOf(new Group(id));
//        if (index == -1)
//            throw new Exception("Group record does not exist");
//        groups.remove(index);
//
//        Response response = new Response();
//        response.setStatus(0);
//        response.setMessage("SUCCESS");
//        return response;
//    }
}
