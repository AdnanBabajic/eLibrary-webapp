package org.elib.services.impl;

import org.dom4j.rule.Mode;
import org.elib.repository.BookRepository;
import org.elib.repository.UserRepository;
import org.elib.repository.entity.Book;
import org.elib.repository.entity.User;
import org.elib.services.ELibService;
import org.elib.services.model.BookDto;
import org.elib.services.model.UserDto;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class ELibServiceImpl implements ELibService {

    @Inject
    BookRepository bookRepository;

    @Inject
    UserRepository userRepository;

    public UserDto registerUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);

        if(!userRepository.checkExisting(user)) {
            user = userRepository.add(user);
            userDto = modelMapper.map(user, UserDto.class);
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto loginUser(UserDto userDto) {
        List<User> userList = userRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        User logUser = modelMapper.map(userDto, User.class);

        for (User user:
             userList) {
            if(user.getName() == logUser.getName()) return modelMapper.map(user, UserDto.class);
        }

        return null;
    }

    @Override
    public List<UserDto> findAllUsers() {
        ModelMapper modelMapper = new ModelMapper();
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user:
             userList) {
            userDtoList.add(modelMapper.map(user, UserDto.class));
        }
        return userDtoList;
    }

    @Override
    public List<BookDto> findAllBooks() {
        ModelMapper modelMapper = new ModelMapper();
        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<>();

        for (Book book:
             bookList) {
            bookDtoList.add(modelMapper.map(book, BookDto.class));
        }

        return bookDtoList;
    }

    @Override
    public BookDto getBook(Integer id) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = bookRepository.findById(id);
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    @Override
    public BookDto addBook(BookDto bookDto) {
        ModelMapper modelMapper = new ModelMapper();
        Book book = modelMapper.map(bookDto, Book.class);
        book = bookRepository.add(book);
        bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    @Override
    public BookDto editBook(BookDto bookDto) {
    Book book = bookRepository.findById(bookDto.getId());
    ModelMapper modelMapper = new ModelMapper();
    book = modelMapper.map(bookDto, Book.class);
    book = bookRepository.save(book);
    bookDto = modelMapper.map(book, BookDto.class);
    return bookDto;
    }

    @Override
    public Boolean deleteBook(Integer id) {
        Book book = bookRepository.findById(id);

        if(book != null) {
        bookRepository.delete(book);
        return true;
        }
        return false;
    }

    @Override
    public BookDto takeBook(UserDto userDto, Integer bookId) {
        Book book = bookRepository.findById(bookId);
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(userDto, User.class);

        book = bookRepository.takeBook(book, user);

        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }

    @Override
    public BookDto openBook(UserDto userDto, Integer bookId) {
        Book book = bookRepository.findById(bookId);
        if(book != null) {
            ModelMapper modelMapper = new ModelMapper();
            User user = modelMapper.map(userDto, User.class);
            BookDto bookDto = modelMapper.map(book, BookDto.class);

            if (user == book.getUser()) {
                return bookDto;
            }

            return null;
        }
        return null;
    }

    @Override
    public BookDto returnBook(Integer id) {
        Book book = bookRepository.findById(id);
        book = bookRepository.returnBook(book);

        ModelMapper modelMapper = new ModelMapper();
        BookDto bookDto = modelMapper.map(book, BookDto.class);
        return bookDto;
    }
}
