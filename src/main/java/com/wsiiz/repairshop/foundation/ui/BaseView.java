package com.wsiiz.repairshop.foundation.ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.wsiiz.repairshop.foundation.domain.AbstractFactory;
import com.wsiiz.repairshop.foundation.domain.AbstractService;
import com.wsiiz.repairshop.foundation.domain.BaseEntity;
import com.wsiiz.repairshop.foundation.ui.dialog.ConfirmDialog;
import com.wsiiz.repairshop.foundation.ui.i18n.I18nAware;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.grid.MGrid;
import org.vaadin.viritin.label.RichText;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

public abstract class BaseView<E extends BaseEntity> extends VerticalLayout implements View,
    ClickListener, Window.CloseListener, I18nAware {

  private final RichText infoText;
  private final AbstractForm<E> editor;
  private final Button addNew;

  protected final AbstractFactory<E> factory;
  protected final AbstractService<E> service;
  protected final JpaRepository<E, Long> repository;

  private MGrid<E> table;

  public BaseView(AbstractFactory<E> factory, AbstractService<E> service, JpaRepository<E, Long> repository,
      AbstractForm<E> editor) {
    this.factory = factory;
    this.service = service;
    this.repository = repository;
    this.infoText = new RichText().withMarkDown(i18n("infoText"));
    this.addNew = new MButton(i18n("addNew"), this).withIcon(VaadinIcons.PLUS);
    this.editor = editor;
  }

  @PostConstruct
  public void postConstruct() {

    this.table = createTable();

    loadEntities();

    final MVerticalLayout mainLayout = new MVerticalLayout(
        infoText,
        new MHorizontalLayout().add(addNew))
        .alignAll(Alignment.MIDDLE_LEFT);

    mainLayout.expand(table);
    mainLayout.setMargin(false);

    addComponent(mainLayout);

    editor.setSavedHandler(entity -> {
      service.save(entity);
      editor.closePopup();
      loadEntities();
    });

  }

  protected abstract void addColumns(MGrid<E> table);

  protected MGrid<E> createTable() {

    MGrid<E> table = new MGrid<>();

    addColumns(table);

    table.addComponentColumn(entity -> new MHorizontalLayout(
        new MButton(VaadinIcons.EDIT, e -> {
          editInPopup(entity);
        }).withStyleName(ValoTheme.BUTTON_BORDERLESS).withStyleName("no-padding"),
        new MButton(VaadinIcons.TRASH, e -> {
          new ConfirmDialog(i18n("deleteConfirmation"), () -> {
            repository.delete(entity);
            loadEntities();
          }, getUI());
        }).withStyleName(ValoTheme.BUTTON_BORDERLESS).withStyleName("no-padding")))
        .setCaption(i18n(BaseView.class, "actions"))
        .setWidth(120);

    table.setHeightFull();
    table.setWidthFull();

    return table;
  }

  private void editInPopup(E entity) {
    editor.setEntity(entity);
    editor.openInModalPopup();
  }

  @Override
  public void enter(ViewChangeListener.ViewChangeEvent event) {
    loadEntities();
  }

  protected void loadEntities() {
    List<E> entities = repository.findAll();
    table.setRows(entities);
  }

  @Override
  public void buttonClick(Button.ClickEvent event) {
    if (event.getButton() == addNew) {
      E entity = newEntity();
      if (entity != null) {
        editor.setEntity(entity);
        editor.focusFirst();
        editor.openInModalPopup();
      }
    }
  }

  private E newEntity() {
    return factory.create();
  }

  @Override
  public void windowClose(Window.CloseEvent e) {
    // odświeżenie tabeli po edycji elementu
    loadEntities();
  }

}
