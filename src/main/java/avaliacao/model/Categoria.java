package avaliacao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria extends AbstractModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCategoria;
	
	private String nomeCategoria;
		
	private String descricao;

	private byte[] figura;
	
	
	public int getIdCategoria() {
		return idCategoria;
	}



	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}



	public String getNomeCategoria() {
		return nomeCategoria;
	}



	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



	public byte[] getFigura() {
		return figura;
	}



	public void setFigura(byte[] figura) {
		this.figura = figura;
	}



	@Override
	public Serializable GetId() {
		return idCategoria;
	}
	
}
