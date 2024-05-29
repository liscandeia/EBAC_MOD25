package br.com.liscandeia;

import br.com.liscandeia.dao.ClienteDAO;
import br.com.liscandeia.dao.IClienteDAO;
import br.com.liscandeia.domain.Cliente;
import br.com.liscandeia.exceptions.TipoChaveNaoEncontradaException;
import br.com.liscandeia.service.ClienteService;
import br.com.liscandeia.service.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteServiceTest {

    private IClienteService clienteService;

    private Cliente cliente;

    public ClienteServiceTest() {
        IClienteDAO dao = new ClienteDAO();
        clienteService = new ClienteService(dao);
    }

    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(05505505505L);
        cliente.setNome("Lis");
        cliente.setCidade("Tagua");
        cliente.setEndereco("QNM");
        cliente.setEstado("DF");
        cliente.setNumero(31);
        cliente.setTelefone(6199998888L);

    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        Boolean retorno = clienteService.cadastrar(cliente);

        Assert.assertTrue(retorno);
    }

    @Test
    public void excluirCliente() {
        clienteService.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Lis");
        clienteService.alterar(cliente);
        Assert.assertEquals("Lis", cliente.getNome());
    }
}