package com.project.bookInventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.bookInventory.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("SELECT b FROM Book b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
            "LOWER(b.genre) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
     List<Book> filterBooks(@Param("searchTerm") String searchTerm);
	
}