package br.com.contratos.testes;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.contratos.beans.Funcionario;
import br.com.contratos.beans.Grupo;
import br.com.contratos.beans.Setor;
import br.com.contratos.jpa.EntityManagerUtil;

public class TesteInserirFuncionario {
	
	public static void main(String args[]){
		EntityManager em = EntityManagerUtil.getEntityManager();
		Grupo grupo = em.find(Grupo.class, 1);
		Setor setor = em.find(Setor.class, 1);
		Funcionario f = new Funcionario();
		f.setAtivo(true);
		f.setCpf("004.908.039-38");
		f.setEmail("jb@gmail.com");
		f.setGrupo(grupo);
		f.setNascimento(Calendar.getInstance());
		f.setNome("Joaaa");
		f.setNomeUsuario("joaaa");
		f.setSalario(5000.00);
		f.setSenha("1234555");
		f.setSetor(setor);
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		System.out.println("Sucesso na inserção");
	}
}
