/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author semih
 */
public class cl_Invoice {

    private String invoice;
    private String date;
    private String type;
    private int products;

    //GET
    public String getInvoice() {
        return invoice;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getProducts() {
        return products;
    }

    //SET
    public void setInvoice(String _invoice) {
        invoice = _invoice;
    }

    public void setDate(String _date) {
        date = _date;
    }

    public void setType(String _type) {
        type = _type;
    }

    public void setProducts(int _products) {
        products = _products;
    }

    //CHECK SET
    public boolean chkSetInvoice(String _invoice) {
        if (_invoice.length() > 0 && _invoice.length() <= 50 && !(_invoice.isEmpty())) {
            invoice = _invoice;
            return true;
        }

        return false;
    }

    public boolean chkSetDate(String _date) {
        try {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate check = LocalDate.parse(_date, dateFormat);

            date = _date;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean chkSetType(String _type) {
        if (_type.length() > 0 && _type.length() <= 50 && !(_type.isEmpty())) {
            type = _type;
            return true;
        }

        return false;
    }
}
