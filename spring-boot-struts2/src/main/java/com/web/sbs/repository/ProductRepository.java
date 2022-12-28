package com.web.sbs.repository;

import com.web.sbs.model.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductRepository {
    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productImage", column = "product_image"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "isSales", column = "is_sales"),
            @Result(property = "description", column = "description"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @Select("SELECT product_id, product_name, product_image, product_price," +
            "is_sales, description,created_at,updated_at   FROM products ORDER BY created_at DESC")
    public List<Product> findAll();
    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productImage", column = "product_image"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "isSales", column = "is_sales"),
            @Result(property = "description", column = "description"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @Select("SELECT product_id, product_name, product_image, product_price," +
            "is_sales, description,created_at,updated_at   " +
            "FROM products ORDER BY created_at DESC LIMIT #{size} OFFSET #{next}")
    public List<Product> findAllByPagin(@Param("size") int size,@Param("next") int next);



    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productImage", column = "product_image"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "isSales", column = "is_sales"),
            @Result(property = "description", column = "description"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @Select("SELECT product_id, product_name, product_image, product_price," +
            "is_sales, description,created_at,updated_at " +
            "FROM products WHERE products.product_name like  CONCAT('%', #{product.productName}, '%') " +
            "AND " +
            "products.is_sales LIKE CONCAT('%', #{product.isSales}, '%')  AND "+
            "products.product_price  BETWEEN IFNULL(NULLIF( #{priceFrom} , -1), products.product_price) AND IFNULL(NULLIF( #{priceTo}, -1), products.product_price)"+
            " ORDER BY created_at DESC LIMIT #{size} OFFSET #{next}")
    public List<Product> findProductsByPagin(@Param("product") Product product ,@Param("priceFrom") double priceFrom ,@Param("priceTo") double priceTo , @Param("next") int next, @Param("size") int size);

    @Results({
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "productImage", column = "product_image"),
            @Result(property = "productPrice", column = "product_price"),
            @Result(property = "isSales", column = "is_sales"),
            @Result(property = "description", column = "description"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
    })
    @Select("SELECT product_id, product_name, product_image, product_price," +
            "is_sales, description,created_at,updated_at " +
            "FROM products WHERE products.product_name like  CONCAT('%', #{product.productName}, '%') " +
            "AND " +
            "products.is_sales LIKE CONCAT('%', #{product.isSales}, '%')  AND "+
            "products.product_price  BETWEEN IFNULL(NULLIF( #{priceFrom} , -1), products.product_price) AND IFNULL(NULLIF( #{priceTo}, -1), products.product_price)"+
            " ORDER BY created_at DESC")
    public List<Product> findProducts(@Param("product") Product product ,@Param("priceFrom") double priceFrom ,@Param("priceTo") double priceTo );

    @Insert("INSERT INTO products(product_id, product_name, product_image, product_price, is_sales,  description, created_at, updated_at) " +
            "VALUES( #{product.productId}, #{product.productName}, #{product.productImage}, #{product.productPrice}, #{product.isSales}, #{product.description}, #{product.createdAt}, #{product.updatedAt})")
    public void insertProduct(@Param("product") Product product);

    @Select("SELECT  product_id FROM products WHERE product_id LIKE CONCAT(#{name}, '%') ORDER BY product_id DESC LIMIT 1")
    public String findIdByFirstCharId(@Param("name") String name);
}
