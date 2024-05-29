package br.com.liscandeia;

import br.com.liscandeia.dao.ClienteDAO;
import br.com.liscandeia.dao.IClienteDAO;
import br.com.liscandeia.domain.Cliente;
import br.com.liscandeia.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class ClienteDAOTest {

    private IClienteDAO clienteDao;

    private Cliente cliente;

    public ClienteDAOTest() {
        clienteDao = new ClienteDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        cliente = new Cliente();
        cliente.setCpf(00050000055L);
        cliente.setNome("Lis");
        cliente.setCidade("Tagua");
        cliente.setEndereco("QNM");
        cliente.setEstado("DF");
        cliente.setNumero(31);
        cliente.setTelefone(6199998888L);
        clienteDao.cadastrar(cliente);
    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setCpf(00000000000L);
        Boolean retorno = clienteDao.cadastrar(cliente);
        assertTrue(retorno);
    }


    @Test
    public void excluirCliente() {
        clienteDao.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Lis de Lima");
        clienteDao.alterar(cliente);
        Assert.assertEquals("Lis de Lima", cliente.getNome());
    }

    @Test
    public void buscarTodos() {
        Collection<Cliente> list = clienteDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);
    }
}