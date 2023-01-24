package com.example.bookshop.admin.controller;

import com.example.bookshop.dao.CategoryDao;
import com.example.bookshop.dao.AuthorDao;
import com.example.bookshop.dao.BookDao;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Book;
import com.example.bookshop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BookController {

    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private BookDao bookDao;

    @GetMapping("/book-form")
    public String bookForm(Model model){
        model.addAttribute("authors",authorDao.findAll());
        model.addAttribute("categories",categoryDao.findAll());
        model.addAttribute("book",new Book());
        return "admin/book-form";
    }

    @Transactional
    @PostMapping("/book-form")
    public String saveBook(Book book, BindingResult result){
        if (result.hasErrors()){
            return "admin/book-form";
        }
        Category category = categoryDao
                .findById(book.getCategory().getId()).get();
        Author author = authorDao
                .findById(book.getAuthor().getId()).get();
        category.addBook(book);
        author.addBook(book);
        bookDao.save(book);
        return "redirect:/admin/book/all";
    }

    @GetMapping("/book/all")
    public String listAll(Model model){
        model.addAttribute("books",bookDao.findAll());
        return "admin/book-list";
    }

    //https://source.unsplash.com/360x200/?fish
    //https://source.unsplash.com/360x200/?book
    //https://source.unsplash.com/360x200/?ocean
    //https://source.unsplash.com/360x200/?desert
    //https://source.unsplash.com/360x200/?library
}
