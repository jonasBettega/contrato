package br.com.contratos.testes;


import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.contratos.beans.Funcionario;
import br.com.contratos.beans.Projeto;
import br.com.contratos.beans.ProjetoFuncionario;
import br.com.contratos.beans.Setor;
import br.com.contratos.jpa.EntityManagerUtil;

public class TesteInserirProjeto {

	public static void main(String args[]){
		EntityManager em = EntityManagerUtil.getEntityManager();
		Setor setor = em.find(Setor.class, 1);
		Funcionario func = em.find(Funcionario.class, 1);
		Projeto projeto = new Projeto();
		projeto.setAtivo(true);
		projeto.setDescricao("Meu novo projeto com JSF");
		projeto.setFim(Calendar.getInstance());
		projeto.setInicio(Calendar.getInstance());
		projeto.setNome("Sistema de funcionario");
		projeto.setSetor(setor);
		ProjetoFuncionario pf = new ProjetoFuncionario();
		pf.setCargaHoraria(100);
		pf.setFimParticipacao(Calendar.getInstance());
		pf.setFuncionario(func);
		pf.setGestor(true);
		pf.setInicioParticipacao(Calendar.getInstance());
		projeto.adicionarFuncionario(pf);
		em.getTransaction().begin();
		em.persist(projeto);
		em.getTransaction().commit();
		System.out.println("Sucesso na inserção");
	}
}

