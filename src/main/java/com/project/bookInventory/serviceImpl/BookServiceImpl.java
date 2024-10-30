package com.project.bookInventory.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.bookInventory.entity.Book;
import com.project.bookInventory.repository.BookRepository;
import com.project.bookInventory.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookRepository bookRepository;
	

	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);	
	}

	@Override
	public List<Book> getAllBooks() {	
		return bookRepository.findAll();
	}

	@Override
	public void deleteBookById(Long id) {
		bookRepository.deleteById(id);	
	}
	
	@Override
	 public List<Book> filterBooks(String searchTerm) {
	        if (searchTerm == null || searchTerm.trim().isEmpty()) {
	            return bookRepository.findAll();
	        }
	        return bookRepository.filterBooks(searchTerm);
	 }

}
