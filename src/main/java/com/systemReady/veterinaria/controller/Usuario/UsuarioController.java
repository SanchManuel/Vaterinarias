package com.systemReady.veterinaria.controller.Usuario;

import com.systemReady.veterinaria.dto.Usuario.UsuarioCreatedRequest;
import com.systemReady.veterinaria.dto.Usuario.UsuarioResponse;
import com.systemReady.veterinaria.service.Usuario.UsuarioServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    public final UsuarioServices usuarioServices;

    public UsuarioController(UsuarioServices usuarioServices) {
        this.usuarioServices = usuarioServices;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponse create(@RequestBody UsuarioCreatedRequest usuarioCreatedRequest){
        return this.usuarioServices.create(usuarioCreatedRequest);
    }


}
