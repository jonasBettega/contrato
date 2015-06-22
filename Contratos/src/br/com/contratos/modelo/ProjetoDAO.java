package br.com.contratos.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.contratos.beans.Grupo;
import br.com.contratos.beans.Projeto;
import br.com.contratos.jpa.EntityManagerUtil;
import br.com.contratos.util.UtilErros;
import br.com.contratos.util.UtilMensagens;

public class ProjetoDAO {

	private EntityManager em;
	
	public ProjetoDAO(){
		em = EntityManagerUtil.getEntityManager();
	}
	
	public List<Projeto> ListarTodos(){
		return em.createQuery("from Projeto order by nome").getResultList();
	}
	
	public boolean gravar(Projeto obj){
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
	
	public boolean excluir(Projeto obj){
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
	
	public Projeto localizar(Integer id){
		return em.find(Projeto.class, id);
	}
	
	public void rollback(){
		if (em.getTransaction().isActive() == false){
			em.getTransaction().begin();
		}
		em.getTransaction().rollback();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
}
