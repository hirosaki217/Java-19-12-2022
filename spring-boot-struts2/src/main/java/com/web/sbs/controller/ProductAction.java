package com.web.sbs.controller;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.web.sbs.model.Product;
import com.web.sbs.repository.ProductRepository;
import com.web.sbs.utitls.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class ProductAction extends ActionSupport {
    private File file;
    private List<Product> products;
    private int totalPages = 0;
    private int totalRecord = 0;
    private int size = 10;
    private int page = 0;
    private String productId;
    private String productName;
    private String productImage;
    private double productPrice;
    private boolean isSales;
    private String strIsSales;
    private String description;
    private static final String ROOT_DIR = "D:\\test\\";
    private String fileFileName;
    private double priceFrom;
    private double priceTo;
    @Autowired
    private Gson gson;
    @Autowired
    private ProductRepository productRepository;
    private String jsonData;
    public String search(){
        if(strIsSales.length() > 0 ){
            if(strIsSales.equals("true"))
                isSales = true;
            else if(strIsSales.equals("false"))
                isSales = false;
            else if(strIsSales.equals(""))
                isSales = false;
        }

        try {
            Product product = new Product(productName, isSales);

            totalRecord = productRepository.findProducts(product, (int) priceFrom, (int) priceTo).size();
            System.out.println("TOTAL: "+totalRecord);
            PageUtils pageUtils = new PageUtils(page, size, totalRecord);
            totalPages = pageUtils.getTotalPages();
            products = productRepository.findProductsByPagin(product, (int) priceFrom, (int) priceTo, pageUtils.getNext(), size);
        }catch (Exception e){
            e.printStackTrace();
        }

        return SUCCESS;
    }
    public String list(){
        totalRecord = productRepository.findAll().size();
        PageUtils pageUtils = new PageUtils(page, size, totalRecord);
        totalPages = pageUtils.getTotalPages();
        products = productRepository.findAllByPagin(size, pageUtils.getNext());
        return SUCCESS;
    }
    public String insert(){

        try {
            String firstChar = productName.substring(0,1).toUpperCase();
            String nameLatest = productRepository.findIdByFirstCharId(firstChar);

            FileInputStream fileInputStream = new FileInputStream(file);
            String[] temp2 = fileFileName.split("\\.");

            String fileName = file.getName() +"."+ temp2[temp2.length -1];
            isSales = Boolean.valueOf(strIsSales);
            Product product = new Product(generateId(firstChar, nameLatest), productName, productPrice, isSales, description);
            product.setCreatedAt(new Date());
            product.setProductImage(ROOT_DIR + fileName);

            productRepository.insertProduct(product);
            fileInputStream.transferTo(new FileOutputStream(ROOT_DIR + fileName));

        }catch (Exception e){
            e.printStackTrace();
        }

        return SUCCESS;

    }

    public String generateId(String id, String nameLatest){

        String pId = id+"000000001";
        try{


            String numberIdLatest = nameLatest.substring(1,nameLatest.length());
            System.out.println("NUMBER LATEST: "+numberIdLatest);
            Integer num = Integer.parseInt(numberIdLatest);
            num++;
            String zero = generateZero(9 - num.toString().length());

            return id + zero + num;
        }catch (Exception e){
            e.printStackTrace();
        }
        return pId;
    }

    public String generateZero(int size){
        String s = "";
        for(int i = 0; i< size; i++){
            s+="0";
        }
        return s;
    }
    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;

    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
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

    public String getStrIsSales() {
        return strIsSales;
    }

    public void setStrIsSales(String strIsSales) {
        this.strIsSales = strIsSales;
    }

    public double getPriceFrom() {

        return priceFrom;
    }

    public void setPriceFrom(double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(double priceTo) {
        this.priceTo = priceTo;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public double getPriceFromString(String s){
        double num = -1;
        if(s.isEmpty())
            return -1;
        try {
            num = Double.parseDouble(s);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        System.out.println("NUMBER: " + num);
        return  num;
    }
}
