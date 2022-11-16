package awsjava.click.productprice.server.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    public String id;

    public String description;

    public BigDecimal price;

}
