package br.com.contratos.beans;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PROJETO_FUNCIONARIO")
public class ProjetoFuncionario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_PROJ_FUNC", sequenceName="GEN_PROJ_FUNC_ID", allocationSize=1)
	@GeneratedValue(generator="SEQ_PROJ_FUNC", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@NotNull(message="A carga horaria deve ser informada")
	@Column(name="CARGA_HORARIA", nullable=false)
	private Integer cargaHoraria;
	@NotNull(message="Se o funcionario é gestor deve ser informado")
	@Column(name="GESTOR", nullable=false)
	private Boolean gestor;
	@NotNull(message="Inicio da participação deve ser informada")
	@Column(name="INICIO_PARTICIPACAO", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar inicioParticipacao;
	@NotNull(message="Fim da participação deve ser informada")
	@Column(name="FIM_PARTICIPACAO", nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar fimParticipacao;
	@NotNull(message="O funcionario deve ser informado")
	@ManyToOne
	@JoinColumn(name="FUNCIONARIO", referencedColumnName="ID", nullable=false)
	private Funcionario funcionario;
	@NotNull(message="O projeto deve ser informado")
	@ManyToOne
	@JoinColumn(name="PROJETO", referencedColumnName="ID", nullable=false)
	private Projeto projeto;
	
	public ProjetoFuncionario(){
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}
	public Boolean getGestor() {
		return gestor;
	}
	public void setGestor(Boolean gestor) {
		this.gestor = gestor;
	}
	public Calendar getInicioParticipacao() {
		return inicioParticipacao;
	}
	public void setInicioParticipacao(Calendar inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}
	public Calendar getFimParticipacao() {
		return fimParticipacao;
	}
	public void setFimParticipacao(Calendar fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Projeto getProjeto() {
		return projeto;
	}
	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
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
		ProjetoFuncionario other = (ProjetoFuncionario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
