package com.sztus.meshop.products.object.domain;

import lombok.Data;

import java.io.Serializable;
@Data
public class ProductsUrl implements Serializable {
    private Long id;
    private String url;
}
