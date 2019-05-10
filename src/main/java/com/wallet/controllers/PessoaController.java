package com.wallet.controllers;

import com.wallet.domain.Pessoa;
import com.wallet.services.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Pessoa")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping(path = "/pessoas/{id}")
    @ApiOperation(value = "Retorna uma única pessoa pelo seu ID.")
    public ResponseEntity<Pessoa> find(@PathVariable Integer id) {
        Pessoa pessoa = pessoaService.find(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping(path = "/pessoas")
    @ApiOperation(value = "Salva uma nova pessoa.")
    public ResponseEntity<Void> insert(Pessoa pessoa) {
        Pessoa newPessoa = pessoaService.insert(pessoa);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/pessoas/{id}")
    @ApiOperation(value = "Atualizar uma pessao pelo seu ID.")
    public ResponseEntity<Void> update(@PathVariable Pessoa Pessoa, @PathVariable Integer id) {
        Pessoa.setId(id);
        Pessoa = pessoaService.update(Pessoa);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/pessoas/{id}")
    @ApiOperation(value = "Remover uma determinada pessoa pelo seu ID.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pessoaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/pessoas")
    @ApiOperation(value = "Trazer todas as pessoas.")
    public ResponseEntity<List<Pessoa>> findAll() {
        List<Pessoa> lista = pessoaService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


}

