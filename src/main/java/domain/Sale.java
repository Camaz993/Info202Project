/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mazca993
 */
public class Sale {
    private Integer sale_Id;
    private Date date;
    private ArrayList<SaleItem> saleItemList;
    private String status;
    private Customer boughtBy;

    public Sale(Integer sale_Id, Date date, ArrayList<SaleItem> saleItemList, String status, Customer boughtBy) {
        this.sale_Id = sale_Id;
        this.date = date;
        this.saleItemList = saleItemList;
        this.status = status;
        this.boughtBy = boughtBy;
    }

    public Integer getSale_Id() {
        return sale_Id;
    }

    public void setSale_Id(Integer sale_Id) {
        this.sale_Id = sale_Id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<SaleItem> getSaleItemList() {
        return saleItemList;
    }

    public void setSaleItemList(ArrayList<SaleItem> saleItemList) {
        this.saleItemList = saleItemList;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getBoughtBy() {
        return boughtBy;
    }

    public void setBoughtBy(Customer boughtBy) {
        this.boughtBy = boughtBy;
    }

    public BigDecimal getTotal(){
        BigDecimal total = new BigDecimal(0);
        for(SaleItem item:saleItemList){
          total =  total.add(item.getItemTotal());
        }
        return total;
    }

    @Override
    public String toString() {
        return "Sale{" + "sale_Id=" + sale_Id + ", date=" + date + ", saleItemList=" + saleItemList + ", status=" + status + ", boughtBy=" + boughtBy + '}';
    }
    
    public void addItem(SaleItem item){
        saleItemList.add(item);
    }
}
