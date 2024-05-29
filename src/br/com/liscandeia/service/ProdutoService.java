package br.com.liscandeia.service;

import br.com.liscandeia.dao.IProdutoDAO;
import br.com.liscandeia.domain.Produto;
import br.com.liscandeia.service.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

    public ProdutoService(IProdutoDAO dao) {
        super(dao);
    }

    @Override
    public void excluir(String valor) {

    }

    @Override
    public Produto consultar(Long valor) {
        return null;
    }
}