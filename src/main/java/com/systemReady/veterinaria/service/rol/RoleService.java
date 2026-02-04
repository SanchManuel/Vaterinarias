package com.systemReady.veterinaria.service.rol;

import com.systemReady.veterinaria.Domain.rol.Rol;
import com.systemReady.veterinaria.Domain.veterinaria.Veterinaria;
import com.systemReady.veterinaria.dto.rol.RoleActualizarRequest;
import com.systemReady.veterinaria.dto.rol.RoleCreateRequest;
import com.systemReady.veterinaria.dto.rol.RoleResponse;
import com.systemReady.veterinaria.repository.RolRepository;
import com.systemReady.veterinaria.repository.VeterinariaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RolRepository rRepo;

    private final VeterinariaRepository vRepo;

    public RoleService(RolRepository rRepo, VeterinariaRepository vRepo) {
        this.rRepo = rRepo;
        this.vRepo = vRepo;
    }

    public RoleResponse create(RoleCreateRequest createRequest) {
        Veterinaria veterinaria = this.vRepo.getReferenceById(createRequest.veterinaria_id());
        Rol rol = new Rol();
        rol.setNombre(createRequest.nombre());
        rol.setDescripcion(createRequest.descripcion());
        rol.setVeterinaria(veterinaria);
        return this.toResponse(this.rRepo.save(rol));
    }

    public RoleResponse get(Long id){
        return this.toResponse(this.getRole(id));
    }

    public Page<RoleResponse> getAll(Pageable pageable, Boolean activo, Long veterinaria_id){
        if(activo != null) {
            return this.rRepo.findByVeterinariaIdAndActivo(veterinaria_id, activo, pageable)
                    .map(this::toResponse);
        }
        return this.rRepo.findByVeterinariaId(veterinaria_id, pageable).map(this::toResponse);
    }

    public RoleResponse update(Long id, RoleActualizarRequest updateRequest) {
        Rol role = this.getRole(id);
        if(updateRequest.descripcion() != null) role.setDescripcion( updateRequest.descripcion());
        if(updateRequest.nombre() != null) role.setNombre(updateRequest.nombre());
        return  this.toResponse(this.rRepo.save(role));
    }

    public void delete(Long id){
        Rol role = this.getRole(id);
        this.rRepo.delete(role);
    }

    private Rol getRole(Long id){
        return this.rRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Rol no encontrado "+id));
    }
    private RoleResponse toResponse(Rol role){
        return new RoleResponse(
                role.getId(),
                role.getNombre(),
                role.getDescripcion(),
                role.getActivo(),
                role.getCreatedAt()
        );
    }
}
