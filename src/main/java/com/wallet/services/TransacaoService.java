package com.wallet.services;

import com.wallet.domain.Transacao;
import com.wallet.dtos.TransacaoDTO;
import com.wallet.repositories.TransacaoRepository;
import com.wallet.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;


    public Transacao find(Integer id) {
        Optional<Transacao> obj = transacaoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Transacao.class.getName()));
    }

    public Transacao insert(Transacao transacao) {
        transacao.setId(null);
        return transacaoRepository.save(transacao);
    }

    public Transacao update(Transacao transacao) {
        verifyIfTransacaoExits(transacao.getId());
        return transacaoRepository.save(transacao);
    }

    public void delete(Integer id) {
        verifyIfTransacaoExits(id);
        try {
            transacaoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir uma transação que possui associados! ");
        }
    }

    public List<Transacao> findAll() {
        return transacaoRepository.findAll();
    }

    private void verifyIfTransacaoExits(Integer id) {
        Optional<Transacao> transacao = transacaoRepository.findById(id);
        if (!transacao.isPresent())
            throw new ResourceNotFoundException("Transação não foi encontrada ID: " + id);
    }
}

