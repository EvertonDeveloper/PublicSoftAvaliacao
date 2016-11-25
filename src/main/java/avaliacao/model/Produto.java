package avaliacao.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Classe representando a tabela de produtos no sistema
 * @author everton
 *
 */
@Entity
public class Produto extends AbstractModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idProduto;
	
	private String nomeProduto;
	
	
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	
	private String quantidadePorUnidade;
	
	private double precoUnitario;
	
	private boolean unidadesEmEstoque;
	
	private boolean unidadesEmOrdem;
	
	private boolean nivelDeReposicao;
	
	private String descontinuado;
	
	

	public int getIdProduto() {
		return idProduto;
	}



	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}



	public String getNomeProduto() {
		return nomeProduto;
	}



	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}



	public String getQuantidadePorUnidade() {
		return quantidadePorUnidade;
	}



	public void setQuantidadePorUnidade(String quantidadePorUnidade) {
		this.quantidadePorUnidade = quantidadePorUnidade;
	}



	public double getPrecoUnitario() {
		return precoUnitario;
	}



	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}



	public boolean isUnidadesEmEstoque() {
		return unidadesEmEstoque;
	}



	public void setUnidadesEmEstoque(boolean unidadesEmEstoque) {
		this.unidadesEmEstoque = unidadesEmEstoque;
	}



	public boolean isUnidadesEmOrdem() {
		return unidadesEmOrdem;
	}



	public void setUnidadesEmOrdem(boolean unidadesEmOrdem) {
		this.unidadesEmOrdem = unidadesEmOrdem;
	}



	public boolean isNivelDeReposicao() {
		return nivelDeReposicao;
	}



	public void setNivelDeReposicao(boolean nivelDeReposicao) {
		this.nivelDeReposicao = nivelDeReposicao;
	}



	public String getDescontinuado() {
		return descontinuado;
	}



	public void setDescontinuado(String descontinuado) {
		this.descontinuado = descontinuado;
	}



	public Categoria getCategoria() {
		return categoria;
	}



	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}



	@Override
	public Serializable GetId() {
		return this.idProduto;
	}
}
