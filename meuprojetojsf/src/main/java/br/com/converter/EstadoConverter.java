package br.com.converter;

import java.io.Serializable;

import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.entidades.Estados;

@FacesConverter(forClass = Estados.class, value = "estadoConverter")
public class EstadoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@Override // retorna o objeto inteiro
	public Object getAsObject(FacesContext context, UIComponent component, String idEstado) {
		try {
			if (idEstado != null && !idEstado.isEmpty()) {
				EntityManager entityManager=CDI.current().select(EntityManager.class).get();

				Estados estados = (Estados) entityManager.find(Estados.class, Long.parseLong(idEstado));

				return estados;
			} else {
				return "";
			}
		} catch (java.lang.NumberFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override // retorna apenas o id/código em String
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		if (estado == null) {
			return null;
		}
		if (estado instanceof Estados) {
			return ((Estados) estado).getId().toString();
		} else {
			return estado.toString();
		}

	}

}
