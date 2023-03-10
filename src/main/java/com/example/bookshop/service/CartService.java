package com.example.bookshop.service;

import com.example.bookshop.dao.BookDao;
import com.example.bookshop.ds.BookDto;
import com.example.bookshop.ds.CartBean;
import com.example.bookshop.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CartService {

    @Autowired
    private CartBean cartBean;
    @Autowired
    private BookDao bookDao;

    public void addToCart(int id) {
        cartBean.addToCart(toDto(bookDao.findById(id).get()));
    }

    public void removeFromCard(BookDto bookDto){

        cartBean.removeBook(bookDto);
    }

    public Set<BookDto> listCart() {
        return cartBean.listAllCarts();
    }

    public BookDto toDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getPublishedYear(),
                book.getPublisher(),
                book.getPrice(),
                book.getQuantity(),
                book.getGenre(),
                book.getImageUrl(),
                book.getDescription(),
                book.getCategory(),
                book.getAuthor()
        );
    }
}
