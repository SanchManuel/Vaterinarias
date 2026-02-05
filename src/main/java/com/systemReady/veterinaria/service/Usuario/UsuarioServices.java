package com.systemReady.veterinaria.service.Usuario;
import com.systemReady.veterinaria.Domain.Usuario.Usuario;
import com.systemReady.veterinaria.dto.Usuario.UsuarioCreatedRequest;
import com.systemReady.veterinaria.dto.Usuario.UsuarioResponse;
import com.systemReady.veterinaria.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder  passwordEncoder;

    public UsuarioServices(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponse create(UsuarioCreatedRequest usuarioCreatedRequest){
        Usuario usuario = new Usuario();
        String password = this.passwordEncoder.encode(usuarioCreatedRequest.password());
        usuario.setNombre(usuarioCreatedRequest.nombre());
        usuario.setEmail(usuarioCreatedRequest.email());
        usuario.setPassword(password);
        return this.toResponse(this.usuarioRepository.save(usuario));
    }



    private UsuarioResponse toResponse(Usuario usuario){
        return new  UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getActivo(),
                usuario.getCreatedAt()
        );
    }


}
