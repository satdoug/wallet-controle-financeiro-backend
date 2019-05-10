package com.wallet.services;

import com.wallet.domain.Carteira;
import com.wallet.repositories.CarteiraRepository;
import com.wallet.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;

    public Carteira find(Integer id){
        Optional<Carteira> obj = carteiraRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Carteira.class.getName()));
    }

    public Carteira insert(Carteira carteira){
        carteira.setId(null);
        carteira = carteiraRepository.save(carteira);

        return carteira;
    }

    public Carteira update(Carteira carteira){
        verifyIfCarteiraExits(carteira.getId());
        Carteira newCarteira = find(carteira.getId());
        upDataCarteira(newCarteira, carteira);
        return carteiraRepository.save(newCarteira);
    }

    public void delete(Integer id){
        verifyIfCarteiraExits(id);
        find(id);
        try {
            carteiraRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é possível excluir uma carteira que possui associados! ");
        }
    }

    public List<Carteira> findAll(){
        return (List<Carteira>) carteiraRepository.findAll();
    }

    private void upDataCarteira(Carteira newCarteira, Carteira carteira){
        newCarteira.setDescricao(carteira.getDescricao());
        newCarteira.setDataInsercao(carteira.getDataInsercao());
        newCarteira.setDataAtualizacao(carteira.getDataAtualizacao());
    }

    private void verifyIfCarteiraExits(Integer id) {
        Optional<Carteira> carteira = carteiraRepository.findById(id);
        if (!carteira.isPresent())
            throw new ResourceNotFoundException("Carteira não foi encontrada ID: " + id);
    }
}
