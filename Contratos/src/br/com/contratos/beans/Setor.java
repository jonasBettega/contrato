package br.com.contratos.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="SETOR")
public class Setor implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ID")
	@SequenceGenerator(name="SEQ_SETOR", sequenceName="SEQ_SETOR_ID", 
			allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_SETOR")
	private Integer id;
	@NotEmpty(message="O nome deve ser informado")
	@Length(max=50, message="O nome não pode ultrapassar {max} caracteres")
	@Column(name="NOME", length=50, nullable=false)
	@Index(name="IDX_SETOR_NOME")
	private String nome;
	
	public Setor(){
		
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
		Setor other = (Setor) obj;
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
}
