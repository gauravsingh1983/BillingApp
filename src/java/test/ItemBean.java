/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.text.DecimalFormat;

/**
 *
 * @author ugxs318
 */
public class ItemBean {
private Integer id;
private String item;
private Double rate;
private String cat;
private Double vat;

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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }
    
    
}
