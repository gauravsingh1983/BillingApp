/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

/**
 *
 * @author Gaurav
 */
public class ReportBean {

    private String item;
    private String category;
    private Double quantity;
    private Double price;
    private Double priceExVat;
    String orderdate;

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPriceExVat() {
        return priceExVat;
    }

    public void setPriceExVat(Double priceExVat) {
        this.priceExVat = priceExVat;
    }

    
}
