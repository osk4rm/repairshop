package com.wsiiz.repairshop.application.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.wsiiz.repairshop.enterprise.ui.EmployeeView;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

@SpringUI(path = "/admin-panel")
@Theme("repairshop")
public class AdminUI extends UI implements I18nAware {

  MainViewDisplay mainContent;

  public AdminUI(MainViewDisplay mainContent, SpringNavigator navigator) {
    this.mainContent = mainContent;
    navigator.setErrorView(EmployeeView.class);
  }

  @Override
  protected void init(VaadinRequest request) {

    setContent(
        new MHorizontalLayout()
            .add(createNavigationBar())

            .expand(mainContent)
            .withFullHeight()
    );
  }

  private Component createNavigationBar() {
    MVerticalLayout m = new MVerticalLayout().withWidth("150px").withStyleName("main-menu");
    Button buttonLogout = new Button("LOGOUT");
    buttonLogout.addClickListener(event -> getUI().getPage().setLocation("logout"));

    m.addComponent(createNavButton(i18n("employee"), "employee"));
    m.addComponent(buttonLogout);
    m.setComponentAlignment(buttonLogout, Alignment.BOTTOM_LEFT);
    return m;
  }

  private Component createNavButton(String caption, String path) {
    Button button = new Button(caption);
    button.addClickListener(e -> getNavigator().navigateTo(path));
    button.addStyleName("main-menu-option");
    button.addStyleName("main-menu-option-" + path);
    return button;
  }


}
