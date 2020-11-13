package com.wsiiz.repairshop.application.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import com.vaadin.ui.declarative.Design;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import com.wsiiz.repairshop.servicing.ui.service.ServiceView;
import org.vaadin.viritin.layouts.MVerticalLayout;

@SpringUI
@Theme("repairshop")
public class MainUI extends UI implements I18nAware {

    MainViewDisplay mainContent;

    public MainUI(MainViewDisplay mainContent, SpringNavigator navigator) {
        this.mainContent = mainContent;
        navigator.setErrorView(ServiceView.class);
    }

    @Override
    protected void init(VaadinRequest request) {

        Button buttonAdmin = new Button("ADMIN PANEL");
        Button buttonEmployee = new Button("EMPLOYEE PANEL");

        buttonAdmin.addClickListener(event -> getUI().getPage().setLocation("admin-panel"));
        buttonAdmin.setWidth("30%");

        buttonEmployee.addClickListener(event -> getUI().getPage().setLocation("employee-panel"));
        buttonEmployee.setWidth("30%");

        setContent(
                new MVerticalLayout()
                        .add(buttonAdmin, buttonEmployee)
                .alignAll(Alignment.TOP_CENTER)
                );
    }



}
