package com.stokmate.backend.sm.api.service;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.stokmate.backend.db.SMDAO;
import com.stokmate.backend.db.SMException;
import com.stokmate.backend.sm.api.model.Group;
import com.stokmate.backend.sm.api.model.SMResponse;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Named;

@Api(
        name = "sm",
        version = "v1",
        description = "Stokmate service",
        namespace = @ApiNamespace(
                ownerDomain = "backend.stokmate.com",
                ownerName = "backend.stokmate.com",
                packagePath = ""
        )
)
public class GroupService{

    private final static Logger logger = Logger.getLogger(GroupService.class.getName());

    @ApiMethod(
            name = "getGroups",
            path = "groups",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public List<Group> getGroups()  {
        logger.log(Level.INFO,"get groups called! yay!");
        List<Group> response = null;
        try {
            SMDAO executor = new SMDAO();
            response = executor.getGroups();

        }catch (Exception e){
            e.printStackTrace();

        }
        return response;
    }

    @ApiMethod(
            name = "getGroup",
            path = "groups/{id}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Group getGroup(@Named("id") long id)  {
        Group response = new Group();
        try {
            SMDAO executor = new SMDAO();
            response = executor.getGroup(id);
            response.setStatus(0);
            response.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(1);
            String message = "Group could not be found!";
            if(e instanceof SMException){
                message = ((SMException)e).getMessage();
            }
            response.setMessage(message);
        }
        return response;
    }

    @ApiMethod(
            name = "addGroup",
            path = "groups",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public SMResponse addGroup(Group group) {
        SMResponse response = new SMResponse();
        try {
            SMDAO executor = new SMDAO();
            executor.addGroup(group);
            response.setStatus(0);
            response.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(1);
            String message = "Group could not be added!";
            if(e instanceof SMException){
                message = ((SMException)e).getMessage();
            }
            response.setMessage(message);
        }
        return response;
    }

    @ApiMethod(
            name = "updateGroup",
            path = "groups",
            httpMethod = ApiMethod.HttpMethod.PUT
    )
    public SMResponse updateGroup(Group group) {
        Group response = new Group();
        try {
            SMDAO executor = new SMDAO();
            executor.updateGroup(group);
            response.setStatus(0);
            response.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(1);
            String message = "Group could not be updated!";
            if(e instanceof SMException){
                message = ((SMException)e).getMessage();
            }
            response.setMessage(message);
        }
        return response;
    }

    @ApiMethod(
            name = "removeGroup",
            path = "groups/{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE
    )
    public SMResponse removeGroup(@Named("id") long id) {
        Group response = new Group();
        try {
            SMDAO executor = new SMDAO();
            executor.removeGroup(id);
            response.setStatus(0);
            response.setMessage("SUCCESS");
        }catch (Exception e){
            e.printStackTrace();
            response.setStatus(1);
            String message = "Group could not be removed!";
            if(e instanceof SMException){
                message = ((SMException)e).getMessage();
            }
            response.setMessage(message);
        }
        return response;
    }
}
