package desafio.dio.viacep.viacep.api.service;

import desafio.dio.viacep.viacep.api.model.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;



public interface ClienteService {
    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir (Cliente cliente);

    void atualizar (Long id, Cliente cliente);

    void deletar (Long id);

}
