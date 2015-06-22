package br.com.contratos.conversores;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="converterCalendar")
public class ConverterCalendar implements Converter{
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	// converte da tela para o objeto
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String string) {
		Calendar c = Calendar.getInstance();
		sdf.setLenient(false);
		try {
			c.setTime(sdf.parse(string));
		} catch (Exception e) {
			return null;			
		}
		return c;
	}

	// converte do objeto para a tela
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		Calendar c = (Calendar) obj;
		String str = sdf.format(c.getTime());
		return str;
	}

}
