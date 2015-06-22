package br.com.contratos.util;

public class UtilErros {	
	public static String getMensagemErro(Exception e){
		while (e.getCause() != null){
			e = (Exception) e.getCause();
		}
		String retorno = e.getMessage();
		return retorno;
	}

}
