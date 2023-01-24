package com.example.bookshop.admin.controller;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    private BookDao bookDao;

    @GetMapping("/admin/")
    public String layoutTest(){
        return "forward:/admin/book/all";
    }

    @GetMapping(value = {"/","/home"})
    public String index(){
        return "index";
    }

    @ModelAttribute("books")
    public List<Book> listBooks(){
        return bookDao.findAll();
    }
}
