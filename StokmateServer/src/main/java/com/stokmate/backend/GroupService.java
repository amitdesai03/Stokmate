package com.stokmate.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.NotFoundException;
import com.stokmate.db.SMDBExecutor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Api(
        name = "groups",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.stokmate.com",
                ownerName = "backend.stokmate.com",
                packagePath = ""
        )
)
public class GroupService {
    public static List<Group> groups = new ArrayList<Group>();

    @ApiMethod(
            name = "get",
            path = "{id}",
            httpMethod = ApiMethod.HttpMethod.GET
    )
    public Group getGroup(@Named("id") long id)  throws Exception{
        SMDBExecutor executor = new SMDBExecutor();
        Group response = executor.getGroup(id);
        if(response == null){
            throw new Exception("Group not found!");
        }
        response.setStatus(0);
        response.setMessage("SUCCESS");
        return response;
    }

    @ApiMethod(
            name = "add",
            httpMethod = ApiMethod.HttpMethod.POST
    )
    public Response addGroup(Group group) throws NotFoundException{
        int index = groups.indexOf(group);
        if (index != -1) throw new NotFoundException("Group record already exists");
        groups.add(group);

        Response response = new Response();
        response.setStatus(0);
        response.setMessage("SUCCESS");
        return response;
    }

    @ApiMethod(
            name = "update",
            httpMethod = ApiMethod.HttpMethod.PUT
    )
    public Response updateGroup(Group group) throws NotFoundException{
        int index = groups.indexOf(group);
        if (index == -1)
            throw new NotFoundException("Group record does not exist");
        Group currentGroup = groups.get(index);
        currentGroup.setName(group.getName());
        currentGroup.setAdmin(group.getAdmin());

        Response response = new Response();
        response.setStatus(0);
        response.setMessage("SUCCESS");
        return response;
    }

    @ApiMethod(
            name = "remove",
            path = "{id}",
            httpMethod = ApiMethod.HttpMethod.DELETE
    )
    public Response removeGroup(@Named("id") long id) throws NotFoundException{
        int index = groups.indexOf(new Group(id));
        if (index == -1)
            throw new NotFoundException("Group record does not exist");
        groups.remove(index);

        Response response = new Response();
        response.setStatus(0);
        response.setMessage("SUCCESS");
        return response;
    }
}
