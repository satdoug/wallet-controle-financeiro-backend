package com.wallet.controllers;

import com.wallet.domain.Usuario;
import com.wallet.services.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Usuário")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(path = "/usuarios/{id}")
    @ApiOperation(value = "Retorna uma única usuário pelo seu ID.")
    public ResponseEntity<Usuario> find(@PathVariable Integer id) {
        Usuario usuario = usuarioService.find(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping(path = "/usuarios")
    @ApiOperation(value = "Salva uma novo usuário.")
    public ResponseEntity<Void> insert(Usuario usuario) {
        Usuario newUsuario = usuarioService.insert(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/usuarios/{id}")
    @ApiOperation(value = "Atualizar um usuário pelo seu ID.")
    public ResponseEntity<Void> update(@PathVariable Usuario usuario, @PathVariable Integer id) {
        usuario.setId(id);
        usuario = usuarioService.update(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/usuarios/{id}")
    @ApiOperation(value = "Remover uma determinado usuário pelo seu ID.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/usuarios")
    @ApiOperation(value = "Trazer todos os Usuários.")
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> lista = usuarioService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


}
