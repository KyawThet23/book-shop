package com.example.bookshop.controller;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.ds.BookDto;
import com.example.bookshop.entity.Book;
import com.example.bookshop.service.CartService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller("user_book_controller")
@RequestMapping("/user")
public class BookController {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private CartService cartService;

    private List<Book> books;

    @PostConstruct
    public void init(){
        books = bookDao.findAll();
    }

    @GetMapping("/book/all")
    public String listAllBooks(Model model,@ModelAttribute("booksAll")List<Book> books){
        model.addAttribute("books",books);
        return "show-all-books";
    }

    @GetMapping("/book")
    public String bookDetails(@RequestParam("id") int id,Model model,
                              @ModelAttribute("booksAll")List<Book> books){

        Book book = books.stream().filter(b -> b.getId() == id).findFirst().get();
        model.addAttribute("book",book);
        return "detail-book";
    }



    public Set<BookDto> listOnCart(){
        return cartService.listCart();
    }

    @ModelAttribute("carts")
    public Set<BookDto> lisTAllCart(){
        return cartService.listCart();
    }
    @ModelAttribute("booksAll")
    public List<Book> listAllBooks(){
        return books;
    }
}
