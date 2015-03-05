package com.stokmate.backend.db;

import com.stokmate.backend.sm.api.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amdesai on 3/3/15.
 */
public class SMDAO extends DAO{

    private static final String GET_GROUP="select name,admin from groups where id=?";
    private static final String ADD_GROUP="insert into groups(name,admin) values(?,?)";
    private static final String UPDATE_GROUP="update groups set name=?,admin=? where id=?";
    private static final String REMOVE_GROUP="delete from groups where id=?";
    private static final String LIST_GROUP="select id,name,admin from groups";

    public List<Group> getGroups()  throws Exception{
        Connection conn = null;
        List<Group> groups = new ArrayList<Group>();
        try {
            conn = connect();
            PreparedStatement ps = conn.prepareStatement(LIST_GROUP);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String admin = rs.getString("admin");
                Group group  = new Group(id, name, admin);
                groups.add(group);
            }
            return groups;
        }finally {

            disconnect(conn);
        }

    }

    public Group getGroup( long id)  throws Exception{
        Connection conn = null;

        try {
            conn = connect();
            PreparedStatement ps = conn.prepareStatement(GET_GROUP);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String admin = rs.getString("admin");
                Group group  = new Group(id, name, admin);
                return group;
            }else{
                throw new Exception("Group not found!");
            }
        }finally {

            disconnect(conn);
        }

    }


    public void addGroup(Group group) throws Exception{
        Connection conn = null;
        try {
            conn = connect();
            PreparedStatement ps = conn.prepareStatement(ADD_GROUP);
            ps.setString(1, group.getName());
            ps.setString(2,group.getAdmin());
            ps.executeUpdate();
        }finally {

            disconnect(conn);
        }
    }


    public void updateGroup(Group group) throws Exception{
        Connection conn = null;
        try {
            conn = connect();
            Group foundGroup = getGroup(group.getId());
            if(foundGroup == null){
                throw new SMException("Group not found!",null);
            }
            PreparedStatement ps = conn.prepareStatement(UPDATE_GROUP);
            if(group.getName()!=null) {
                ps.setString(1, group.getName());
            }else{
                ps.setString(1, foundGroup.getName());
            }
            if(group.getAdmin()!=null) {
                ps.setString(2, group.getAdmin());
            }else{
                ps.setString(2,foundGroup.getAdmin());
            }

            ps.setLong(3,foundGroup.getId());
            int count = ps.executeUpdate();
            if(count==0){
                throw new SMException("Group could not be updated!",null);
            }

        }finally {

            disconnect(conn);
        }
    }


    public void removeGroup( long id) throws Exception{
        Connection conn = null;
        try {
            conn = connect();
            PreparedStatement ps = conn.prepareStatement(REMOVE_GROUP);
            ps.setLong(1,id);
            int count = ps.executeUpdate();
            if(count==0){
                throw new SMException("Group could not be removed!",null);
            }
        }finally {

            disconnect(conn);
        }
    }
}
