package com.project.bookInventory.service;

import java.util.List;

import com.project.bookInventory.entity.Book;

public interface BookService {

	public Book addBook(Book book);
	
	 public List<Book> getAllBooks();
	 
	 public void deleteBookById(Long id);
	 
	 public List<Book> filterBooks(String searchTerm); 
}
