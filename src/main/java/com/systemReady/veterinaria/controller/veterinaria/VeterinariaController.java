package com.systemReady.veterinaria.controller.veterinaria;

import com.systemReady.veterinaria.dto.veterinaria.VeterinariaCreateRequest;
import com.systemReady.veterinaria.dto.veterinaria.VeterinariaUpdateRequest;
import com.systemReady.veterinaria.dto.veterinaria.VeterinariaResponse;
import com.systemReady.veterinaria.service.veterinaria.VeterinariaService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veterinarias")
public class VeterinariaController {

    private final VeterinariaService service;

    public VeterinariaController(VeterinariaService service){
        this.service=service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeterinariaResponse crear(@RequestBody @Valid VeterinariaCreateRequest request){
        return this.service.crear(request);
    }

    @GetMapping("/{id}")
    public VeterinariaResponse obtener(@PathVariable Long id){
        return this.service.obtener(id);
    }

    @GetMapping
    public Page<VeterinariaResponse> listar(@RequestParam(required = false) Boolean activo, @ParameterObject Pageable pageable){
        return this.service.listar(activo,pageable);
    }

    @PatchMapping("/{id}")
    public VeterinariaResponse actaulizar(@PathVariable Long id, @RequestBody @Valid VeterinariaUpdateRequest request){
        return this.service.actualizar(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        this.service.eliminar(id);
    }
}
