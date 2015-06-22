package br.com.contratos.modelo;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.contratos.beans.Grupo;
import br.com.contratos.jpa.EntityManagerUtil;
import br.com.contratos.util.UtilErros;
import br.com.contratos.util.UtilMensagens;

public class GrupoDAO {
	
	private EntityManager em;
	private String ordem = "id";
	private String filtro = "";
	private Integer maximosObjetos = 10;
	private Integer posicaoAtual = 0;
	private Integer totalObjetos = 0;
	
	public GrupoDAO(){
		em = EntityManagerUtil.getEntityManager();
	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> listar(){
		String where = "";
		if (filtro.length() > 0 ){
			if (ordem.equals("id")){
				try {
					Integer.parseInt(filtro);
					where = " where "+ ordem + " = '"+filtro+"' ";
				}catch(Exception e) {
					
				}
			} else {
				where = " where upper("+ordem+") like '"+filtro.toUpperCase()+ "%' ";
			}			
		}
		String jpql = "from Grupo"+ where + " order by "+ordem;
		totalObjetos = em.createQuery("select id from Grupo "+ where +
				" order by "+ordem).getResultList().size();
		return em.createQuery(jpql).
				setFirstResult(posicaoAtual).
				setMaxResults(maximosObjetos).getResultList();
	}
	
	public void primeiro(){
		posicaoAtual = 0;
	}
	
	public void anterior(){
		posicaoAtual -= maximosObjetos;
		if (posicaoAtual < 0 ){
			posicaoAtual = 0;
		}
	}
	
	public void proximo(){
		if (posicaoAtual + maximosObjetos < totalObjetos){
			posicaoAtual += maximosObjetos;
		}
	}
	
	public void ultimo(){
		int resto = totalObjetos % maximosObjetos;
		if (resto > 0 ){
			posicaoAtual = totalObjetos - resto;
		} else {
			posicaoAtual = totalObjetos - maximosObjetos;
		}
	}
	
	public String getMessagemNavegacao(){
		int ate = posicaoAtual + maximosObjetos;
		if (ate > totalObjetos) {
			ate = totalObjetos;
		}
		return "Listando de "+ (posicaoAtual + 1)+
				" até "+ ate + " de " +totalObjetos+ " registros";
	}
	
	@SuppressWarnings("unchecked")
	public List<Grupo> ListarTodos(){
		return em.createQuery("from Grupo order by nome").getResultList();
	}
	
	public boolean gravar(Grupo obj){
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
	
	public boolean excluir(Grupo obj){
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
	
	public Grupo localizar(Integer id){
		return em.find(Grupo.class, id);
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}	

	public Integer getPosicaoAtual() {
		return posicaoAtual;
	}

	public void setPosicaoAtual(Integer posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}

	public Integer getTotalObjetos() {
		return totalObjetos;
	}

	public void setTotalObjetos(Integer totalObjetos) {
		this.totalObjetos = totalObjetos;
	}

	public Integer getMaximosObjetos() {
		return maximosObjetos;
	}

	public void setMaximosObjetos(Integer maximosObjetos) {
		this.maximosObjetos = maximosObjetos;
	}
	
}
