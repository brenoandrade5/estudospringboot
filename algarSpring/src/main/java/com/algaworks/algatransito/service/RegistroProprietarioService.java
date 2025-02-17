package com.algaworks.algatransito.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository;

    public Proprietario buscar(Long proprietrioId){

        return proprietarioRepository.findById(proprietrioId)
                .orElseThrow(() -> new NegocioException("Proprietario nao encontrado"));

    }


    @Transactional
    public Proprietario salvar(Proprietario proprietario){
    boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
            .filter(p -> !p.equals(proprietario))
            .isPresent();
    if(emailEmUso){
        throw new NegocioException("Já existe um proprietário cadastrado com este e-mail");
    }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId){
        proprietarioRepository.deleteById(proprietarioId);
    }
}
