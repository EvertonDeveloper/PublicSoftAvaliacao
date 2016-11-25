package avaliacao.dao;

import avaliacao.model.Produto;

public class ProdutoDao extends GenericDao<Produto> {

	@Override
	protected Class<Produto> getEntityClass() {
		return Produto.class;
	}

}
