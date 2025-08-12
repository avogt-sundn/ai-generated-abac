package com.example.abac.rest;

import com.example.abac.service.PolicyEvaluationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;

@Path("/access-check")
@Produces(MediaType.APPLICATION_JSON)
public class AccessCheckResource {

    @Inject
    PolicyEvaluationService evalService;

    @GET
    public Response check(@QueryParam("subject") String sid,
                          @QueryParam("action") String aid,
                          @QueryParam("resource") String rid,
                          @QueryParam("context") String cid) {

        boolean allowed = evalService.isAllowed(sid, aid, rid, cid);
        return Response.ok(Map.of("allowed", allowed)).build();
    }
}
