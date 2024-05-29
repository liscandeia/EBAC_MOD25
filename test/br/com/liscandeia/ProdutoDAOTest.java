package br.com.liscandeia;

import br.com.liscandeia.dao.IProdutoDAO;
import br.com.liscandeia.dao.ProdutoDAO;
import br.com.liscandeia.domain.Produto;
import br.com.liscandeia.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import java.math.BigDecimal;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class ProdutoDAOTest {

    private IProdutoDAO produtoDao;

    private Produto produto;

    public ProdutoDAOTest() {
        produtoDao = new ProdutoDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        produto = new Produto();
        produto.setCodigo("A");
        produto.setDescricao("TV");
        produto.setNome("TV SAMSUNG");
        produto.setValor(BigDecimal.TEN);
        produtoDao.cadastrar(produto);
    }

    @Test
    public void pesquisar() {
        Produto produto = this.produtoDao.consultar(this.produto.getCodigo());
        Assert.assertNotNull(produto);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        produto.setCodigo("B");
        Boolean retorno = produtoDao.cadastrar(produto);
        assertTrue(retorno);
    }

    @Test
    public void excluir() {
        produtoDao.excluir(produto.getCodigo());
    }

    @Test
    public void alterarProduto() throws TipoChaveNaoEncontradaException {
        produto.setNome("DVD");
        produtoDao.alterar(produto);

        Assert.assertEquals("DVD", produto.getNome());
    }

    @Test
    public void buscarTodos() {
        Collection<Produto> list = produtoDao.buscarTodos();
        assertTrue(list != null);
        assertTrue(list.size() == 2);
    }
}
