package click.awsjava.productprice.server.controller;

import click.awsjava.productprice.server.entity.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private List<Product> products = new ArrayList<Product>();

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable String id) {
        var result = products
                .stream()
                .filter(x -> x.getId().equals(id))
                .toList();

        if (result.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(result.get((0)));
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setId(UUID.randomUUID().toString());
        products.add(product);

        return ResponseEntity.ok(product);
    }

    @PutMapping
    public ResponseEntity<Product> addOrEditProduct(@RequestBody Product product) {
        var result = products
                .stream()
                .filter(x -> x.getId().equals(product.getId()))
                .toList();

        if (result.size() == 0) {
            if (product.getId() == null) {
                return addProduct(product);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        var p = products.get(0);
        p.setDescription(product.getDescription());
        p.setPrice(product.getPrice());

        return ResponseEntity.ok(product);
    }

}
