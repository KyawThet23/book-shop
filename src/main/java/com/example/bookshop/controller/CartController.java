package com.example.bookshop.controller;

import com.example.bookshop.ds.BookDto;
import com.example.bookshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/addToCart")
    public String addToCart(@RequestParam("id") int id){
        cartService.addToCart(id);
        return "redirect:/user/book?id="+id;
    }

    @GetMapping("/delete")
    public String removeFromCart(@RequestParam("id")int id){

        cartService.removeFromCard(findBookDtoById(id));
        return "redirect:/cart/view";

    }

    private BookDto findBookDtoById(int id){
        return cartService.listCart()
                        .stream()
                            .filter(b->b.getId() == id)
                                .findFirst()
                                    .get();
    }

    @GetMapping("/view")
    public String viewCart(Model model){
        model.addAttribute("carts",cartService.listCart());
        return "cart-view";
    }
}
