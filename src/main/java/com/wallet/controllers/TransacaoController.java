package com.wallet.controllers;

import com.wallet.domain.Transacao;
import com.wallet.dtos.TransacaoDTO;
import com.wallet.services.TransacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.table.TableRowSorter;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Rest Transação")
@CrossOrigin(origins = "*")
public class TransacaoController {


    @Autowired
    private TransacaoService transacaoService;

    @GetMapping(path = "/transacoes/{id}")
    @ApiOperation(value = "Retorna uma única transação pelo seu ID.")
    public ResponseEntity<Transacao> find(@PathVariable Integer id) {
        Transacao transacao = transacaoService.find(id);
        return ResponseEntity.ok().body(transacao);
    }

    @PostMapping(path = "/transacoes")
    @ApiOperation(value = "Salva uma nova transação.")
    public ResponseEntity<Transacao> insert(@Valid @RequestBody TransacaoDTO transacaoDTO) {
        Transacao newTransacao = transacaoService.insert(transacaoDTO.transformaParaObjeto());
        return new ResponseEntity<>(newTransacao, HttpStatus.CREATED);
    }

    @PutMapping(value = "/transacoes/{id}")
    @ApiOperation(value = "Atualizar uma transação pelo seu ID.")
    public ResponseEntity<Transacao> update(@PathVariable Integer id, @Valid @RequestBody TransacaoDTO transacaoDTO) {
        Transacao updateTransacao = transacaoService.update(transacaoDTO.transformaParaObjeto());
        return new ResponseEntity<>(updateTransacao, HttpStatus.OK);
    }

    @DeleteMapping(value = "/transacoes/{id}")
    @ApiOperation(value = "Remover uma determinada transação pelo seu ID.")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        transacaoService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/transacoes")
    @ApiOperation(value = "Trazer todas as Transações.")
    public ResponseEntity<List<Transacao>> findAll() {
        List<Transacao> lista = transacaoService.findAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
