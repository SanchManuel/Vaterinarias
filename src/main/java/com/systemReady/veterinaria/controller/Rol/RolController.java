package com.systemReady.veterinaria.controller.Rol;

import com.systemReady.veterinaria.dto.rol.RoleActualizarRequest;
import com.systemReady.veterinaria.dto.rol.RoleCreateRequest;
import com.systemReady.veterinaria.dto.rol.RoleResponse;
import com.systemReady.veterinaria.service.rol.RoleService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/roles")
public class RolController {
    private final RoleService roleService;

    public RolController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleResponse create(@RequestBody @Valid  RoleCreateRequest createRequest) {
        return this.roleService.create(createRequest);
    }

    @GetMapping("/all")
    public Page<RoleResponse> getAll(@RequestParam Long veterinariaId,
                                     @RequestParam(required = false) Boolean activo,
                                     @ParameterObject Pageable pageable) {
       return this.roleService.getAll(pageable,activo,veterinariaId);
    }

    @GetMapping("/{id}")
    public RoleResponse getById(@PathVariable Long id) {
        return this.roleService.get(id);
    }

    @PatchMapping("/{id}")
    public RoleResponse update(@PathVariable Long id, @RequestBody @Valid RoleActualizarRequest actualizarRequest){
        return this.roleService.update(id,actualizarRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        this.roleService.delete(id);
        return ResponseEntity.ok("Rol has been deleted");
    }

}
