package com.wsiiz.repairshop.foundation.ui.i18n;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Component;
import org.vaadin.viritin.form.AbstractForm;

import java.util.Arrays;

public abstract class BaseEditor<E> extends AbstractForm<E> implements I18nAware {


    public BaseEditor(Class<E> entityType) {
        super(entityType);
    }

    protected void setCaptions(){
        Arrays.stream(this.getClass().getDeclaredFields())
                .filter(f -> HasValue.class.isAssignableFrom(f.getType()))
                .forEach(f -> {
                    try{
                        f.setAccessible(true);
                        ((Component) f.get(this)).setCaption(i18n(f.getName()));
                    } catch (IllegalAccessException e){}
                });
    }
}
