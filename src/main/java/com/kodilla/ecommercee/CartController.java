package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Dto.CartDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @RequestMapping(method = RequestMethod.GET, value = "getCarts")
    public List<CartDto> getCarts(){
        return new ArrayList<>();
    }
    @RequestMapping(method = RequestMethod.GET, value = "getCart")
    public CartDto getCart(@RequestParam Long cartId) throws CartNotFoundException{
        return new CartDto();
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCart")
    public void deleteCart(@RequestParam  Long cartId){

    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateCart")
    public CartDto updateCart(@RequestBody CartDto cartDto){
        return new CartDto();
    }
    @RequestMapping(method = RequestMethod.POST, value = "createCart" )
    public void createCart(@RequestBody CartDto cartDto){

    }

}
