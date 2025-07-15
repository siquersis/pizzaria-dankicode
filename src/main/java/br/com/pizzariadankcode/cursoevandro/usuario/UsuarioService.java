package br.com.pizzariadankcode.cursoevandro.usuario;

import br.com.pizzariadankcode.cursoevandro.config.CriptografiaSenha;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        Usuario usuario = modelMapper.map(dto, Usuario.class);

        //Cripotrafando a senha para salvar no DB.
        String senhaCriptografada = CriptografiaSenha.criptografia(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);

        usuarioRepository.save(usuario);

        return modelMapper.map(usuario, UsuarioDTO.class);
    }
}
