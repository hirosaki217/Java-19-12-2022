package com.web.sbs.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(columnDefinition = "VARCHAR(20)")
    private String productId;
    @Column(columnDefinition = "VARCHAR(255)", nullable = false)

    private String productName;
    @Column(columnDefinition = "VARCHAR(255)")

    private String productImage;
    @Column(columnDefinition = "DECIMAL default 0", nullable = false)
    private int productPrice;
    @Column(columnDefinition = "TINYINT default 1",  nullable = false)
    private boolean isSales;
    @Column(columnDefinition = "TEXT")

    private String description;

    private Date createdAt;

    private Date updatedAt;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isSales() {
        return isSales;
    }

    public void setSales(boolean sales) {
        isSales = sales;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Product() {
    }

    public Product(String productId, String productName, String productImage, int productPrice, boolean isSales, String description) {
        this.productId = productId;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
        this.isSales = isSales;
        this.description = description;
    }

    public Product(String productName, boolean isSales) {
        this.productName = productName;
        this.isSales = isSales;
    }

    public Product(String productId, String productName, int productPrice, boolean isSales, String description) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.isSales = isSales;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productPrice=" + productPrice +
                ", isSales=" + isSales +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
