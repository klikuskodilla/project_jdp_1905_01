package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.Dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getProducts")
    public List<ProductDto> getProducts(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProduct")
    public ProductDto getProduct(@RequestParam Long productId) throws ProductNotFoundException{
        return new ProductDto(1L, "test_product_name", "test_details", 24.5);
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct", consumes = APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto){
        return new ProductDto(1L, "updated_test_product_name", "updated_test_details" , 53.5);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam Long productId){
    }
}
