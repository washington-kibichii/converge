/*
 * Copyright (C) 2012 Interactive Media Management
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dk.i2m.converge.admin.jsf.converters;

import dk.i2m.converge.ejb.entities.Language;
import dk.i2m.converge.ejb.facades.LanguageFacadeLocal;
import dk.i2m.converge.ejb.services.DataNotFoundException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Allan Lykke Christensen
 */
@ManagedBean
@RequestScoped
public class LanguageConverter implements Converter {

    @EJB
    private LanguageFacadeLocal languageFacade;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        try {
            Language l = languageFacade.find(Long.valueOf(string));
            return l;
        } catch (DataNotFoundException ex) {
            throw new ConverterException();
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null) {
            return "";
        }
        try {
            Language l = (Language) o;
            return "" + l.getId();
        } catch (Exception ex) {
            throw new ConverterException();
        }
    }
}
