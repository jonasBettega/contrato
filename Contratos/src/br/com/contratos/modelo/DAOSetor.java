package br.com.contratos.modelo;


import java.io.Serializable;




import javax.persistence.EntityManager;

import br.com.contratos.beans.Setor;
import br.com.contratos.conversores.ConverterOrdem;
import br.com.contratos.jpa.EntityManagerUtil;

@SuppressWarnings("serial")
public class DAOSetor<T> extends GenericDAO<T> implements Serializable {
	
	public DAOSetor(){
		super.setClasse(Setor.class);
		super.setEm(EntityManagerUtil.getEntityManager());
		super.getListaOrdem().add(new Ordem("Codigo", "id"));
		super.getListaOrdem().add(new Ordem("Nome", "nome"));
		super.setOrdemAtual((Ordem) super.getListaOrdem().get(1));
		super.setFiltro("");
		super.setMaximoObjetos(2);
		super.setConverterOrdem(new ConverterOrdem(super.getListaOrdem()));
	}

}
