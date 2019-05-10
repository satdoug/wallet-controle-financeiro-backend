package com.wallet.controllers;

import com.wallet.domain.Carteira;
import com.wallet.repositories.CarteiraRepository;
import com.wallet.services.CarteiraService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Carteira")
//@CrossOrigin(origins = "*")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping(path = "/carteiras/{id}")
    @ApiOperation(value = "Retorna uma única carteira pelo seu ID.")
    public ResponseEntity<Carteira> find(@PathVariable Integer id) {
        Carteira carteira = carteiraService.find(id);
        return ResponseEntity.ok().body(carteira);
    }

    @PostMapping(path = "/carteiras")
    @ApiOperation(value = "Salva uma nova carteira.")
    public ResponseEntity<Void> insert(Carteira carteira) {
        Carteira newCarteira = carteiraService.insert(carteira);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/carteiras/{id}")
    @ApiOperation(value = "Atualizar uma carteira pelo seu ID.")
    public ResponseEntity<Void> update(@PathVariable Carteira carteira, @PathVariable Integer id) {
        carteira.setId(id);
        carteira = carteiraService.update(carteira);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/carteiras/{id}")
    @ApiOperation(value = "Remover uma determinada carteira pelo seu ID.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        carteiraService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/carteiras")
    @ApiOperation(value = "Trazer todas as Carteiras.")
    public ResponseEntity<List<Carteira>> findAll() {
        List<Carteira> lista = carteiraService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


}
