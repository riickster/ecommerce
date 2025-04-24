package com.itesm.ecommerce.infrastructure.rest;

import com.itesm.ecommerce.application.usecase.user.LoginUseCase;
import com.itesm.ecommerce.application.usecase.user.RegisterUserUseCase;
import com.itesm.ecommerce.infrastructure.dto.user.request.LoginRequestDTO;
import com.itesm.ecommerce.infrastructure.dto.user.request.RegisterUserRequestDTO;
import com.itesm.ecommerce.lib.PublicEndpoint;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@PublicEndpoint
public class AuthController {

    @Inject RegisterUserUseCase registerUserUseCase;
    @Inject LoginUseCase loginUseCase;

    @POST
    @Path("/register")
    public Response register(RegisterUserRequestDTO dto){
        return Response.ok(registerUserUseCase.execute(dto)).build();
    }

    @POST
    @Path("/login")
    public Response login(LoginRequestDTO dto){
        return Response.ok(loginUseCase.execute(dto)).build();
    }
}
