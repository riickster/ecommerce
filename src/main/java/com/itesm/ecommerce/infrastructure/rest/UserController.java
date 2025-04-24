package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.usecase.user.GetUserByFirebaseIdUseCase;
import com.itesm.ecommerce.lib.UserContext;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject GetUserByFirebaseIdUseCase getUserByFirebaseIdUseCase;
    @Inject UserContext userContext;

    @GET
    public Response getUser() {
        String firebaseId = userContext.getFirebaseId();
        return Response.ok(getUserByFirebaseIdUseCase.execute(firebaseId)).build();
    }
}
