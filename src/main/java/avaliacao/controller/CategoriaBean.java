package avaliacao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import avaliacao.dao.CategoriaDao;
import avaliacao.model.Categoria;

@ManagedBean
public class CategoriaBean {
	private CategoriaDao dao;

	private List<Categoria> lista;

	public CategoriaBean() {
		dao = new CategoriaDao();
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void updateLista() {
		this.lista = dao.listar();
	}

	public List<Categoria> getLista() {
		if (this.lista == null) {
			updateLista();
		}
		return lista;
	}

	public void setLista(List<Categoria> lista) {
		this.lista = lista;
	}
	
	public void onModelReturn(SelectEvent event) {
		String msg = event.getObject().toString();
        addMessage(msg);
        updateLista();
    }

	public void btnCadastrar() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog("categoriaCadastro", options, null);
	}

	public void btnEditar(Categoria p) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		
		List<String> idParam = new ArrayList<String>();
		idParam.add(p.getIdCategoria()+ "");
		params.put("id", idParam);
		
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog("categoriaCadastro", options, params);
		
	}

	public void btnDeletar(Categoria p) {
		dao.remover(p);
		updateLista();
	}
}
