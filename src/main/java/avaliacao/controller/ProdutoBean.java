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

import avaliacao.dao.ProdutoDao;
import avaliacao.model.Produto;

@ManagedBean
public class ProdutoBean {

	private ProdutoDao dao;

	private List<Produto> lista;

	public ProdutoBean() {
		dao = new ProdutoDao();
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void updateLista() {
		this.lista = dao.listar();
	}

	public List<Produto> getLista() {
		if (this.lista == null) {
			updateLista();
		}
		return lista;
	}

	public void setLista(List<Produto> lista) {
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
		RequestContext.getCurrentInstance().openDialog("produtoCadastro", options, null);
	}

	public void btnEditar(Produto p) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		
		List<String> idParam = new ArrayList<String>();
		idParam.add(p.getIdProduto()+ "");
		params.put("id", idParam);
		
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("resizable", false);
		options.put("draggable", false);
		options.put("modal", true);
		RequestContext.getCurrentInstance().openDialog("produtoCadastro", options, params);
		
	}

	public void btnDeletar(Produto p) {
		dao.remover(p);
		updateLista();
	}

}
