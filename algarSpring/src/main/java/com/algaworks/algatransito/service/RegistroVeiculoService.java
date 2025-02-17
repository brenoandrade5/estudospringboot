package com.algaworks.algatransito.service;

import com.algaworks.algatransito.domain.exception.NegocioException;
import com.algaworks.algatransito.domain.model.Proprietario;
import com.algaworks.algatransito.domain.model.StatusVeiculo;
import com.algaworks.algatransito.domain.model.Veiculo;
import com.algaworks.algatransito.domain.repository.ProprietarioRepository;
import com.algaworks.algatransito.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository;
    private RegistroProprietarioService registroProprietarioService;
    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo){

        if(novoVeiculo.getId()!=null){
            throw new NegocioException("Veiculo a ser cadastrado nao deve possuir um código");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(novoVeiculo))
                                .isPresent();

        if (placaEmUso){
            throw new NegocioException("já existe um veiculo cadastrad com está placa");
        }

        Proprietario proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());
        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());

        return veiculoRepository.save(novoVeiculo);



    }
}
