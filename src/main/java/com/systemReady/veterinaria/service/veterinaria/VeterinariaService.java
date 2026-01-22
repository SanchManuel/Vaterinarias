package com.systemReady.veterinaria.service.veterinaria;

import com.systemReady.veterinaria.dto.veterinaria.CreateRequest;
import com.systemReady.veterinaria.repository.VeterinariaRepository;
import org.springframework.stereotype.Service;

@Service
public class VeterinariaService {
    private final VeterinariaRepository repository;

    public VeterinariaService(VeterinariaRepository repository){
        this.repository=repository;
    }

    public VeterinariaResponse crear(CreateRequest request){

    }

}
