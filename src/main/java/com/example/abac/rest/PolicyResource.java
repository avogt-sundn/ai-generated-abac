package com.example.abac.rest;

import com.example.abac.entity.Policy;
import com.example.abac.repo.PolicyRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/policies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PolicyResource {

    @Inject
    PolicyRepository policyRepo;

    @GET
    public List<Policy> list() { return policyRepo.listAll(); }

    @POST
    @Transactional
    public Policy create(Policy p) {
        policyRepo.persist(p);
        return p;
    }
}
