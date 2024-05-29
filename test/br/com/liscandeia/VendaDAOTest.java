package br.com.liscandeia;

import br.com.liscandeia.dao.*;
import br.com.liscandeia.domain.Cliente;
import br.com.liscandeia.domain.Produto;
import br.com.liscandeia.domain.Venda;
import br.com.liscandeia.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.*;

public class VendaDAOTest {

    private IVendaDAO vendaDao;

    private IClienteDAO clienteDao;

    private IProdutoDAO produtoDao;

    //private Venda venda;

    private Cliente cliente;

    private Produto produto;

    public VendaDAOTest() {
        vendaDao = new VendaDAO();
        clienteDao = new ClienteDAO();
        produtoDao = new ProdutoDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        this.cliente = cadastrarCliente();
        this.produto = cadastrarProduto("A", BigDecimal.TEN);
    }


    @Test
    public void pesquisar() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("A");
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        Venda vendaConsultada = vendaDao.consultar(venda.getCodigo());
        assertNotNull(vendaConsultada);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("B");
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }


    @Test
    public void cancelarVenda() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "C";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        venda.setStatus(Venda.Status.CANCELADA);
        vendaDao.alterar(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        assertEquals(codigoVenda, vendaConsultada.getCodigo());
        assertEquals(Venda.Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "D";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(produto, 1);

        assertTrue(venda.getQuantidadeTotalProdutos() == 3);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(30)));
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "E";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);

        assertTrue(venda.getQuantidadeTotalProdutos() == 3);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void salvarProdutoExistente() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("F");
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);

        Boolean retorno1 = vendaDao.cadastrar(venda);
        assertFalse(retorno1);
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void removerProduto() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "G";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(venda.getQuantidadeTotalProdutos() == 3);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));


        vendaConsultada.removerProduto(prod, 1);
        assertTrue(venda.getQuantidadeTotalProdutos() == 2);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "H";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(venda.getQuantidadeTotalProdutos() == 3);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));


        vendaConsultada.removerProduto(prod, 1);
        assertTrue(venda.getQuantidadeTotalProdutos() == 2);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(20)));
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void removerTodosProdutos() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "I";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        assertTrue(venda.getQuantidadeTotalProdutos() == 3);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(70)));


        vendaConsultada.removerTodosProdutos();
        assertTrue(venda.getQuantidadeTotalProdutos() == 0);
        assertTrue(venda.getValorTotal().equals(BigDecimal.valueOf(0)));
        assertTrue(venda.getStatus().equals(Venda.Status.INICIADA));
    }

    @Test
    public void finalizarVenda() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "J";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(venda.getStatus(), vendaConsultada.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "K";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        assertTrue(retorno);
        assertNotNull(venda);
        assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);
        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        assertEquals(venda.getStatus(), vendaConsultada.getStatus());

        vendaConsultada.adicionarProduto(this.produto, 1);

    }

    private Produto cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("TV");
        produto.setNome("TV LG");
        produto.setValor(valor);
        produtoDao.cadastrar(produto);
        return produto;
    }

    private Cliente cadastrarCliente() throws TipoChaveNaoEncontradaException {
        Cliente cliente = new Cliente();
        cliente.setCpf(12345678910L);
        cliente.setNome("Lis");
        cliente.setCidade("TAGUA");
        cliente.setEndereco("QNM");
        cliente.setEstado("DF");
        cliente.setNumero(10);
        cliente.setTelefone(6199998888L);
        clienteDao.cadastrar(cliente);
        return cliente;
    }

    private Venda criarVenda(String codigo) {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.setStatus(Venda.Status.INICIADA);
        venda.adicionarProduto(this.produto, 2);
        return venda;
    }

}
