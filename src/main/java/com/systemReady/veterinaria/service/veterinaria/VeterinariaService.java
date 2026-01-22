package com.systemReady.veterinaria.service.veterinaria;
import com.systemReady.veterinaria.Domain.veterinaria.Veterinaria;
import com.systemReady.veterinaria.dto.veterinaria.CreateRequest;
import com.systemReady.veterinaria.dto.veterinaria.UpdateRequest;
import com.systemReady.veterinaria.dto.veterinaria.responseDTO;
import com.systemReady.veterinaria.repository.VeterinariaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VeterinariaService {
    private final VeterinariaRepository repository;

    public VeterinariaService(VeterinariaRepository repository){
        this.repository=repository;
    }

    public responseDTO crear(CreateRequest request){
        Veterinaria veterinaria = new Veterinaria();
        veterinaria.setNombre(request.nombre());
        veterinaria.setTelefono(request.telefono());
        veterinaria.setDireccion(request.direccion());

        return this.toResponse(this.repository.save(veterinaria));
    }

    public Page<responseDTO> listar(Boolean activo, Pageable pageable){
        if(activo == null){
            return this.repository.findAll(pageable).map(this::toResponse);
        }
        return this.repository.findByActivo(activo, pageable).map(this::toResponse);
    }

    public responseDTO obtener(Long id){
        Veterinaria veterinaria = this.getVeterinaria(id);
        return this.toResponse(veterinaria);
    }

    public responseDTO actualizar(Long id, UpdateRequest request){
        Veterinaria veterinaria = this.getVeterinaria(id);
        if(request.nombre() != null) veterinaria.setNombre(request.nombre());
        if(request.telefono() != null) veterinaria.setTelefono(request.telefono());
        if(request.direccion()!= null) veterinaria.setDireccion(request.direccion());
        if(request.activa() != null) veterinaria.setActivo(request.activa());
        return toResponse(this.repository.save(veterinaria));
    }

    public void eliminar(Long id){
        if(!this.repository.existsById(id)){
            throw new RuntimeException("Veterinaria No encontrada: "+ id);
        }
        this.repository.deleteById(id);
    }

    private Veterinaria getVeterinaria(Long id){
        return this.repository.findById(id)
                .orElseThrow(()->new RuntimeException("Veterinaria no encontradaa "+id));
    }
    private responseDTO toResponse(Veterinaria veterinaria) {
        return new responseDTO(
                veterinaria.getId(),
                veterinaria.getNombre(),
                veterinaria.getTelefono(),
                veterinaria.getDireccion(),
                veterinaria.getActivo(),
                veterinaria.getCreatedAt()
        );
    }
}
