package avaliacao.controller;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import avaliacao.dao.CategoriaDao;
import avaliacao.model.Categoria;

@ManagedBean
@ViewScoped
public class CategoriaCadastroBean implements Serializable{
	private Categoria model;

	private CategoriaDao dao;


	public CategoriaCadastroBean() {
		dao = new CategoriaDao();
		model = new Categoria();
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

	public Categoria getModel() {
		return model;
	}

	public void setModel(Categoria model) {
		this.model = model;
	}


	public void btnSalvar() {
		dao.salvar(model);
		RequestContext.getCurrentInstance().closeDialog("Cadastrado com sucesso!");
	}
}
