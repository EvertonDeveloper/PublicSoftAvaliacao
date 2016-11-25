package avaliacao.dao;

import avaliacao.model.Categoria;

public class CategoriaDao extends GenericDao<Categoria> {

	@Override
	protected Class<Categoria> getEntityClass() {
		return Categoria.class;
	}

}
