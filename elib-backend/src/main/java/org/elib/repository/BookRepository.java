package org.elib.repository;

import org.elib.repository.entity.Book;
import org.elib.repository.entity.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class BookRepository extends Repository<Book, Integer>{

    public BookRepository() {
        super(Book.class);
    }

    @Inject
    UserRepository userRepository;

    public Book takeBook(Book book, User user) {
        book.setAvailable(false);
        book.setUser(user);

        book = save(book);
        return book;
    }

    public Book returnBook(Book book) {
        book.setAvailable(true);
        book.setUser(null);

        save(book);
        return book;
    }
}
