package org.elib.services;

import org.elib.services.model.BookDto;
import org.elib.services.model.UserDto;

import java.util.List;

public interface ELibService {

	UserDto registerUser(UserDto userDto);
	UserDto loginUser(UserDto userDto);
	List<UserDto> findAllUsers();

	List<BookDto> findAllBooks();
	BookDto getBook(Integer id);
	BookDto addBook(BookDto bookDto);
	BookDto editBook(BookDto bookDto);
	Boolean deleteBook(Integer id);

	BookDto takeBook(UserDto userDto, Integer bookId);
	BookDto openBook(UserDto userDto, Integer bookId);
	BookDto returnBook(Integer id);
}