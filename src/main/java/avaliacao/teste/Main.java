package avaliacao.teste;

import avaliacao.dao.ProdutoDao;
import avaliacao.model.Produto;

public class Main {
	
	public static void main(String[] args){
		Produto p = new Produto();
		p.setNomeProduto("novo produto 3");
		
		ProdutoDao dao = new ProdutoDao();
		dao.salvar(p);
		
		
		for (Produto pdt : dao.listar()) {
			System.out.println(pdt.getNomeProduto());
		}
		
		
	}

}
