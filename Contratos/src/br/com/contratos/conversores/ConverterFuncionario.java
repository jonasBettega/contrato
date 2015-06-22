package br.com.contratos.conversores;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;


import br.com.contratos.beans.Funcionario;
import br.com.contratos.jpa.EntityManagerUtil;

public class ConverterFuncionario implements Converter, Serializable{
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		if (string == null || string.equals("Selecione um funcionario")){
			return null;
		}
		return EntityManagerUtil.getEntityManager().find(Funcionario.class, 
				Integer.parseInt(string));
	}
	
	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object o) {
		if (o == null){
			return null;
		}
		Funcionario obj = (Funcionario) o;
		return obj.getId().toString();
	}

}
