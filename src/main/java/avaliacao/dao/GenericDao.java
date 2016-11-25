package avaliacao.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import avaliacao.model.AbstractModel;
import avaliacao.util.StringUtil;



public abstract class GenericDao<T extends AbstractModel> {

	protected static EntityManagerFactory entityManagerFactory;
	protected EntityManager manager;

	public GenericDao() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("avaliacao");
		}
	}

	protected abstract Class<T> getEntityClass();

	public void salvar(T obj) {

		T retorno = null;
		try {
			retorno = consultarPorID(obj.GetId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (retorno != null) {
			alterar(obj);
		} else {
			incluir(obj);
		}
	}

	protected void incluir(T obj) {
		manager = entityManagerFactory.createEntityManager();
		manager.getTransaction().begin();
		manager.persist(obj);
		manager.getTransaction().commit();
	}

	public void remover(T obj) {
		manager = entityManagerFactory.createEntityManager();
		@SuppressWarnings("unchecked")
		T pObj = (T) manager.find(obj.getClass(), obj.GetId());
		manager.getTransaction().begin();
		manager.remove(pObj);
		manager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public T consultarPorID(Serializable id) {

		T result = null;

		manager = entityManagerFactory.createEntityManager();

		try {
			if (id != null) {
				result = (T) manager.find(getEntityClass(), id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// manager.close();
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> listar() {
		manager = entityManagerFactory.createEntityManager();
		Query query = manager.createQuery(String.format("select c from %s c",
				this.getEntityClass().getSimpleName()), this.getEntityClass());
		return query.getResultList();
	}

	protected void alterar(T obj) {
		System.out.println("Alterar " + obj);
		manager = entityManagerFactory.createEntityManager();
		manager.getTransaction().begin();
		manager.merge(obj);
		manager.flush();
		manager.getTransaction().commit();
	}

	public List<T> listarPorLike(Map<String, Object> atributos) {
		manager = entityManagerFactory.createEntityManager();

		List<String> atts = new ArrayList<String>();

		for (Entry<String, Object> entry : atributos.entrySet()) {
			
			if (entry.getValue() instanceof String) {
				atts.add(String.format("UPPER(c.%s) like UPPER(:%s)", entry.getKey(),
						entry.getKey().toString()));
			} else {
				atts.add(String.format("c.%s = :%s", entry.getKey(),
						entry.getKey().toString()));		
			}
		}

		String sql = String.format("select c from %s c where %s ",
				getEntityClass().getSimpleName(),
				StringUtil.join(atts, " and "));

		Query query = manager.createQuery(sql, getEntityClass());

		for (Entry<String, Object> entry : atributos.entrySet()) {

			if (entry.getValue() instanceof String) {
				query.setParameter(entry.getKey(), "%" + entry.getValue() + "%");
			} else {
				query.setParameter(entry.getKey(), entry.getValue());				
			}

		}

		return query.getResultList();
	}

}
