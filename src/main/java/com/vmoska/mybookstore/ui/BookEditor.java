package com.vmoska.mybookstore.ui;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vmoska.mybookstore.model.entity.Book;
import com.vmoska.mybookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class BookEditor extends VerticalLayout implements KeyNotifier {

    private final BookRepository bookRepository;

    private Book book;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Book> binder = new Binder<>(Book.class);
    private ChangeHandler changeHandler;

    @Autowired
    public BookEditor(BookRepository repository) {
        this.bookRepository = repository;

        add(firstName, lastName, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editBook(book));
        setVisible(false);
    }

    void delete() {
        bookRepository.delete(book);
        changeHandler.onChange();
    }

    void save() {
        bookRepository.save(book);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editBook(Book book) {
        if (book == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = book.getId() != null;
        if (persisted) {
            book = bookRepository.findById(book.getId()).get();
        } else {
            book = book;
        }

        cancel.setVisible(persisted);
        binder.setBean(book);
        setVisible(true);
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }
}
