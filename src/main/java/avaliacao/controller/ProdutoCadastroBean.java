package avaliacao.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import avaliacao.dao.CategoriaDao;
import avaliacao.dao.ProdutoDao;
import avaliacao.model.Categoria;
import avaliacao.model.Produto;

@ManagedBean
@ViewScoped
public class ProdutoCadastroBean implements Serializable {

	private Produto model;

	private ProdutoDao dao;

	private List<Categoria> categorias;

	public List<Categoria> getCategorias() {
		if (categorias == null) {
			categorias = new CategoriaDao().listar();
		}
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ProdutoCadastroBean() {
		dao = new ProdutoDao();
		model = new Produto();
	}

	@PostConstruct
	public void init() {
		try {
			Map<String, String[]> mapParams = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterValuesMap();
			
			if(mapParams.size()==0)
				return;

			String[] idParam = mapParams.get("id");
			
			if(idParam == null)
				return;

			model = dao.consultarPorID(Integer.parseInt(idParam[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Produto getModel() {
		return model;
	}

	public void setModel(Produto model) {
		this.model = model;
	}

	public void btnSalvar() {
		dao.salvar(model);
		RequestContext.getCurrentInstance().closeDialog("Cadastrado com sucesso!");
	}
}
