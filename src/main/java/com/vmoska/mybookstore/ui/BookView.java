package com.vmoska.mybookstore.ui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vmoska.mybookstore.model.entity.Book;
import com.vmoska.mybookstore.repository.BookRepository;
import org.apache.commons.lang3.StringUtils;

@Route("book-view")
public class BookView extends VerticalLayout {

    private final BookRepository bookRepository;

    private final BookEditor bookEditor;

    final Grid<Book> grid;

    final TextField filter;

    private final Button addNewBtn;

    public BookView(BookRepository repo, BookEditor bookEditor) {
        this.bookRepository = repo;
        this.bookEditor = bookEditor;
        this.grid = new Grid<>(Book.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New book", VaadinIcon.PLUS.create());

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, bookEditor);

        grid.setHeight("200px");
        grid.setColumns("id", "title", "isbn13", "language", "numPages", "publicationDate", "publisher");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by title");

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> listBooks(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            bookEditor.editBook(e.getValue());
        });

        addNewBtn.addClickListener(e -> bookEditor.editBook(new Book()));

        bookEditor.setChangeHandler(() -> {
            bookEditor.setVisible(false);
            listBooks(filter.getValue());
        });

        listBooks(null);
    }

    void listBooks(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(bookRepository.findAll());
        } else {
            grid.setItems(bookRepository.findByTitleStartsWithIgnoreCase(filterText));
        }
    }

}
