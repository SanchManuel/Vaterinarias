package com.systemReady.veterinaria.controller.veterinaria;

import com.systemReady.veterinaria.dto.veterinaria.CreateRequest;
import com.systemReady.veterinaria.dto.veterinaria.UpdateRequest;
import com.systemReady.veterinaria.dto.veterinaria.responseDTO;
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
    public responseDTO crear(@RequestBody @Valid CreateRequest request){
        return this.service.crear(request);
    }

    @GetMapping("/{id}")
    public responseDTO obtener(@PathVariable Long id){
        return this.service.obtener(id);
    }

    @GetMapping
    public Page<responseDTO> listar(@RequestParam(required = false) Boolean activo,  @ParameterObject Pageable pageable){
        System.out.println("SORT = " + pageable.getSort());
        return this.service.listar(activo,pageable);
    }

    @PatchMapping("/{id}")
    public responseDTO actaulizar(@PathVariable Long id, @RequestBody @Valid UpdateRequest request){
        return this.service.actualizar(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id){
        this.service.eliminar(id);
    }
}
