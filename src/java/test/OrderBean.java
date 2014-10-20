/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author ugxs318
 */
public class OrderBean implements Serializable {
private Integer id;
private String item;
private Double qty;
private Double rate;
private Double price;
private Double vat;
private Double priceExVat;
private String cat;
private java.sql.Date odate;
private String oid;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public java.sql.Date getOdate() {
        return odate;
    }

    public void setOdate(java.sql.Date odate) {
        this.odate = odate;
    }


    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getPriceExVat() {
        return priceExVat;
    }

    public void setPriceExVat(Double priceExVat) {
        this.priceExVat = priceExVat;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

   
}
