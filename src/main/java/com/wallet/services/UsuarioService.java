package com.wallet.services;

import com.wallet.domain.Usuario;
import com.wallet.repositories.UsuarioRepositoty;
import com.wallet.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepositoty usuarioRepositoty;

    public Usuario find(Integer id){
        Optional<Usuario> obj = usuarioRepositoty.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()));
    }

    public Usuario insert(Usuario usuario){
        usuario.setId(null);
        usuario = usuarioRepositoty.save(usuario);

        return usuario;
    }

    public Usuario update(Usuario usuario){
        verifyIfUsuarioExits(usuario.getId());
        Usuario newUsuario = find(usuario.getId());
        upDataUsuario(newUsuario, usuario);
        return usuarioRepositoty.save(newUsuario);
    }

    public void delete(Integer id){
        verifyIfUsuarioExits(id);
        find(id);
        try {
            usuarioRepositoty.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é possível excluir um usuário que possui associados! ");
        }
    }

    public List<Usuario> findAll(){
        return (List<Usuario>) usuarioRepositoty.findAll();
    }

    private void upDataUsuario(Usuario newUsuario, Usuario usuario){
        newUsuario.setLogin(usuario.getLogin());
        newUsuario.setSenha(usuario.getSenha());
        newUsuario.setDataInsercao(usuario.getDataInsercao());
        newUsuario.setDataAtualizacao(usuario.getDataAtualizacao());
    }

    private void verifyIfUsuarioExits(Integer id) {
        Optional<Usuario> usuario = usuarioRepositoty.findById(id);
        if (!usuario.isPresent())
            throw new ResourceNotFoundException("Usuário não foi encontrada ID: " + id);
    }
}

