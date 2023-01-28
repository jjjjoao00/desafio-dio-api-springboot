package desafio.dio.viacep.viacep.api.service.implementacao;

import desafio.dio.viacep.viacep.api.model.Cliente;
import desafio.dio.viacep.viacep.api.model.ClienteRepository;
import desafio.dio.viacep.viacep.api.model.Endereco;
import desafio.dio.viacep.viacep.api.model.EnderecoRepository;
import desafio.dio.viacep.viacep.api.service.ClienteService;
import desafio.dio.viacep.viacep.api.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ClienteServiceImplementacao implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById();
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);

    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if(clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);

    }

    private void salvarClienteComCep (Cliente cliente){
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() ->
        {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save (novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }



}
