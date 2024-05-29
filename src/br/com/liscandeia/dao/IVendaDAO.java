package br.com.liscandeia.dao;

import br.com.liscandeia.dao.generic.IGenericDAO;
import br.com.liscandeia.domain.Venda;
import br.com.liscandeia.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

    void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException;
}