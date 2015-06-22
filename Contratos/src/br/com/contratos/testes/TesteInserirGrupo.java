package br.com.contratos.testes;


import javax.persistence.EntityManager;

import br.com.contratos.beans.Grupo;
import br.com.contratos.jpa.EntityManagerUtil;

public class TesteInserirGrupo {

	public static void main(String[] args) {
		
		EntityManager em = EntityManagerUtil.getEntityManager();
		Grupo g = new Grupo();
		g.setNome("Coordenadores");
		em.getTransaction().begin();
		em.persist(g);
		em.getTransaction().commit();
		System.out.println("Sucesso na inserção");
	}

}
