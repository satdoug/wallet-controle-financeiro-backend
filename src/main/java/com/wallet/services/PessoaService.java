package com.wallet.services;

import com.wallet.domain.Pessoa;
import com.wallet.repositories.PessoaRepository;
import com.wallet.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa find(Integer id){
        Optional<Pessoa> obj = pessoaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
    }

    public Pessoa insert(Pessoa pessoa){
        pessoa.setId(null);
        pessoa = pessoaRepository.save(pessoa);

        return pessoa;
    }

    public Pessoa update(Pessoa pessoa){
        Pessoa newPessoa = find(pessoa.getId());
        upDataPessoa(newPessoa, pessoa);
        return pessoaRepository.save(newPessoa);
    }

    public void delete(Integer id){
        verifyIfPessoaExits(id);
        find(id);
        try {
            pessoaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é possível excluir uma pessoa que possui associados! ");
        }
    }

    public List<Pessoa> findAll(){
        return (List<Pessoa>) pessoaRepository.findAll();
    }

    private void upDataPessoa(Pessoa newPessoa, Pessoa pessoa){
        newPessoa.setNome(pessoa.getNome());
        newPessoa.setSalario(pessoa.getSalario());
        newPessoa.setDataAniversario(pessoa.getDataAniversario());
        newPessoa.setDataInsercao(pessoa.getDataInsercao());
        newPessoa.setDataAtualizacao(pessoa.getDataAtualizacao());
    }

    private void verifyIfPessoaExits(Integer id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if (!pessoa.isPresent())
            throw new ResourceNotFoundException("Pessoa não foi encontrada ID: " + id);
    }
}

