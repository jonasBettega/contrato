package br.com.contratos.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.contratos.beans.Funcionario;
import br.com.contratos.beans.Grupo;
import br.com.contratos.beans.Setor;
import br.com.contratos.jpa.EntityManagerUtil;
import br.com.contratos.util.UtilErros;
import br.com.contratos.util.UtilMensagens;

public class FuncionarioDAO {
	

	private EntityManager em;
	
	public FuncionarioDAO(){
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<Funcionario> listTodos(){
		return em.createNamedQuery("from Funcionario order by nome").getResultList();
	}

	public boolean gravar(Funcionario obj){
		try {
			em.getTransaction().begin();
			if (obj.getId() == null){
				em.persist(obj);
			} else {
				em.merge(obj);
			}
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso!");
			return true;
		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao persistir objeto: "+
			                                  UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean excluir(Funcionario obj){
		try {
			em.getTransaction().begin();
			em.remove(obj);
			em.getTransaction().commit();
			UtilMensagens.mensagemInformacao("Objeto removido com sucesso!");
			return true;
		} catch (Exception e){
			if (em.getTransaction().isActive() == false){
				em.getTransaction().begin();
			}
			em.getTransaction().rollback();
			UtilMensagens.mensagemErro("Erro ao remover objeto: "+
			                                  UtilErros.getMensagemErro(e));
			return false;
		}
	}	
	
	public Funcionario localizar(Integer id){
		return em.find(Funcionario.class, id);
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

}
