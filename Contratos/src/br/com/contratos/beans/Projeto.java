package br.com.contratos.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="PROJETO")
public class Projeto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_PROJ", sequenceName="GEN_PROJ_ID", allocationSize=1)
	@GeneratedValue(generator="SEQ_PROJ", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@NotEmpty(message="O nome deve ser informado")
	@Length(max=50, message="O nome não deve ultrapassar {max} caracteres")
	@Column(name="NOME", nullable=false, length=50)
	@Index(name="IDX_PROJETO_NOME")
	private String nome;
	@Type(type="org.hibernate.type.StringClobType")
	@NotEmpty(message="A descrição deve ser informada")
	@Column(name="DESCRICAO", nullable=false)
	@Lob
	private String descricao;
	@javax.validation.constraints.NotNull(message="Data de inicio deve ser informada")
	@Column(name="INICIO", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar inicio;
	@javax.validation.constraints.NotNull(message="Data de fim deve ser informada")
	@Column(name="FIM", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar fim;
	@javax.validation.constraints.NotNull(message="Status do projeto deve ser informado")
	@Column(name="ATIVO", nullable=false)
	private Boolean ativo;
	@javax.validation.constraints.NotNull(message="O setor deve ser informado")
	@ManyToOne
	@JoinColumn(name="SETOR", referencedColumnName="ID", nullable=false)
	private Setor setor;
	@OneToMany(mappedBy="projeto", cascade= {CascadeType.ALL},
			orphanRemoval=true)
	@LazyCollection(LazyCollectionOption.EXTRA)
	@OrderBy(value="id asc")
	private List<ProjetoFuncionario> funcionarios = new ArrayList<ProjetoFuncionario>();
	
	public Projeto(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getInicio() {
		return inicio;
	}
	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}
	public Calendar getFim() {
		return fim;
	}
	public void setFim(Calendar fim) {
		this.fim = fim;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

	public List<ProjetoFuncionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<ProjetoFuncionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public void adicionarFuncionario(ProjetoFuncionario obj){
		obj.setProjeto(this);
		this.funcionarios.add(obj);
	}
	
	public void removerFuncionario(ProjetoFuncionario obj){
		if (this.funcionarios.contains(obj)){
			this.funcionarios.remove(obj);
		}
	}
	
	public void removerTodosFuncionario(){
		this.funcionarios.clear();
	}

}
