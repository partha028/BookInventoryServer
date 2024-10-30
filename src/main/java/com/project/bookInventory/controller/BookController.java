package com.project.bookInventory.controller;

import java.io.IOException;
import java.io.StringWriter;

import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookInventory.entity.Book;
import com.project.bookInventory.service.BookService;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return "Book with ID " + id + " has been deleted.";
    }
    
    @PostMapping("/export/csv")
    public ResponseEntity<String> exportBooksToCSV(@RequestBody List<Book> books) {
        StringWriter writer = new StringWriter();

        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Title", "Author", "Genre", "Publication Date", "ISBN"))) {
            for (Book book : books) {
                csvPrinter.printRecord(book.getEntryId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublicationDate(), book.getIsbn());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=books.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(writer.toString());
    }
    
    @GetMapping("/filter")
    public List<Book> filterBooks(@RequestParam String searchTerm) {
        return bookService.filterBooks(searchTerm);
    }

}