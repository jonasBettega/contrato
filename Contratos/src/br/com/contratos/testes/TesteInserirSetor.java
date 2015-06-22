package br.com.contratos.testes;

import javax.persistence.EntityManager;

import br.com.contratos.beans.Setor;
import br.com.contratos.jpa.EntityManagerUtil;

public class TesteInserirSetor {

	public static void main(String[] args) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		Setor s = new Setor();
		s.setNome("Administrativo");
		Setor s2 = new Setor();
		s2.setNome("Juridico");
		em.getTransaction().begin();
		em.persist(s);
		em.persist(s2);
		em.getTransaction().commit();
		System.out.println("Sucesso na inserção");
	}

}
