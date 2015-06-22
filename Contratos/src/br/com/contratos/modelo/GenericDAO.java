package br.com.contratos.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.contratos.conversores.ConverterOrdem;
import br.com.contratos.util.UtilErros;
import br.com.contratos.util.UtilMensagens;


@SuppressWarnings("serial")
public class GenericDAO<T> implements Serializable {
	
	private Class classe;
	private EntityManager em;
	private String filtro = "";
	private List<Ordem> listaOrdem = new ArrayList<Ordem>();
	private Ordem ordemAtual;
	private int maximoObjetos = 10;
	private int posicao = 0;
	private int totalObjetos = 0;
	private ConverterOrdem converterOrdem;
	
	public void iniciarTransacao(){
		if (em.getTransaction().isActive() == false){
			em.getTransaction().begin();
		}
	}
	public void rollbackTransacao(){
		iniciarTransacao();
		em.getTransaction().rollback();
	}
	

	public void commitTransacao(){
		em.getTransaction().commit();
	}
	
	public boolean persist(T objeto){
		try {
			iniciarTransacao();
			em.persist(objeto);
			commitTransacao();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso");
			return true;
		} catch (Exception e) {
			rollbackTransacao();
			UtilMensagens.mensagemErro("Erro ao persistir objeto: "+
					UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean merge(T objeto){
		try {
			iniciarTransacao();
			em.merge(objeto);
			commitTransacao();
			UtilMensagens.mensagemInformacao("Objeto persistido com sucesso");
			return true;
		} catch (Exception e) {
			rollbackTransacao();
			UtilMensagens.mensagemErro("Erro ao persistir objeto: "+
					UtilErros.getMensagemErro(e));
			return false;
		}
	}
	
	public boolean remove(T objeto){
		try {
			iniciarTransacao();
			em.remove(objeto);
			commitTransacao();
			UtilMensagens.mensagemInformacao("Objeto removido com sucesso");
			return true;
		} catch (Exception e) {
			rollbackTransacao();
			UtilMensagens.mensagemErro("Erro ao remover objeto: "+
					UtilErros.getMensagemErro(e));
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> listarTodos(){
		String jpql = "from "+classe.getSimpleName();
		if (ordemAtual != null){
			jpql += "order by"+ordemAtual.getAtributo();
		}
		return em.createQuery(jpql).getResultList();
	}
	
	public String protegeFiltro(String filtro){
		filtro = filtro.replaceAll("[';-]", "");
		return filtro;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listar(){
		String jpql = "from "+classe.getSimpleName();
		String where = "";
		if (filtro != null && filtro.length() > 0){
			filtro = protegeFiltro(filtro);
			if (ordemAtual.getAtributo().endsWith("id")){
				try {
					Integer.parseInt(filtro);
					where = " where "+ordemAtual.getAtributo() + " = '"+filtro+"' ";
				} catch (Exception e) {
					
				}
			} else {
				where = " where upper("+ordemAtual.getAtributo()+ ") like '"
						+filtro.toUpperCase()+ "%' ";
			}
		}
		jpql += where;
		if (ordemAtual != null){
			jpql += " order by "+ordemAtual.getAtributo();
		}
		totalObjetos = em.createQuery("select id from "+classe.getSimpleName()).
				getResultList().size();
		if (maximoObjetos == 0){
			maximoObjetos = totalObjetos;
		}
		return em.createQuery(jpql).
				setMaxResults(maximoObjetos).
				setFirstResult(posicao).getResultList();
	}
	
	public void primeiro(){
		posicao = 0;
	}
	
	public void anterior(){
		posicao -= maximoObjetos;
		if (posicao < 0){
			posicao = 0;
		}
	}
	
	public void proximo(){
		if (posicao + maximoObjetos < totalObjetos){
			posicao += maximoObjetos;
		}
	}
	
	public void ultimo(){
		int resto = totalObjetos % maximoObjetos;
		if (resto > 0){
			posicao = totalObjetos - resto;
		} else {
			posicao = totalObjetos - maximoObjetos;
		}
	}
	
	public String getMensagemNavegacao(){
		int ate = posicao + maximoObjetos;
		if (ate > totalObjetos){
			ate = totalObjetos;
		}
		return "Lsstando de " + (posicao + 1)+
				" até"+ ate + " de " +totalObjetos+ " registros";
	}
	
	public Class getClasse() {
		return classe;
	}
	public void setClasse(Class classe) {
		this.classe = classe;
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public List<Ordem> getListaOrdem() {
		return listaOrdem;
	}
	public void setListaOrdem(List<Ordem> listaOrdem) {
		this.listaOrdem = listaOrdem;
	}
	public Ordem getOrdemAtual() {
		return ordemAtual;
	}
	public void setOrdemAtual(Ordem ordemAtual) {
		this.ordemAtual = ordemAtual;
	}
	public int getMaximoObjetos() {
		return maximoObjetos;
	}
	public void setMaximoObjetos(int maximoObjetos) {
		this.maximoObjetos = maximoObjetos;
	}
	public int getPosicao() {
		return posicao;
	}
	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}
	public int getTotalObjetos() {
		return totalObjetos;
	}
	public void setTotalObjetos(int totalObjetos) {
		this.totalObjetos = totalObjetos;
	}
	public ConverterOrdem getConverterOrdem() {
		return converterOrdem;
	}
	public void setConverterOrdem(ConverterOrdem converterOrdem) {
		this.converterOrdem = converterOrdem;
	}
	
}