package br.com.liscandeia.service;


import br.com.liscandeia.dao.IClienteDAO;
import br.com.liscandeia.domain.Cliente;
import br.com.liscandeia.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {


    public ClienteService(IClienteDAO clienteDAO) {
        super(clienteDAO);
    }

    @Override
    public Cliente buscarPorCPF(Long cpf) {
        return this.dao.consultar(cpf);
    }
}