/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author mazca993
 */
public class Customer {
    private Integer person_Id;
    private String username;
    private String first_Name;
    private String surname;
    private String password;
    private String emailAddress;
    private String shippingAddress;
    private String CreditCardDetails;
    private ArrayList<Sale> saleList;

    public Customer(Integer person_Id, String username, String first_Name, String surname, String password, String emailAddress, String shippingAddress, String CreditCardDetails, ArrayList<Sale> saleList) {
        this.person_Id = person_Id;
        this.username = username;
        this.first_Name = first_Name;
        this.surname = surname;
        this.password = password;
        this.emailAddress = emailAddress;
        this.shippingAddress = shippingAddress;
        this.CreditCardDetails = CreditCardDetails;
        this.saleList = saleList;
    }

    public Customer() {
    }

    public Integer getPerson_Id() {
        return person_Id;
    }

    public void setPerson_Id(Integer person_Id) {
        this.person_Id = person_Id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCreditCardDetails() {
        return CreditCardDetails;
    }

    public void setCreditCardDetails(String CreditCardDetails) {
        this.CreditCardDetails = CreditCardDetails;
    }

    public ArrayList<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(ArrayList<Sale> saleList) {
        this.saleList = saleList;
    }

    @Override
    public String toString() {
        return "Customer{" + "person_Id=" + person_Id + ", username=" + username + ", first_Name=" + first_Name + ", surname=" + surname + ", password=" + password + ", emailAddress=" + emailAddress + ", shippingAddress=" + shippingAddress + ", CreditCardDetails=" + CreditCardDetails + ", saleList=" + saleList + '}';
    }

    
}
