/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

import java.math.BigDecimal;

/**
 *
 * @author semih
 */
public class cl_InvoiceStock extends cl_Stock {

    private String invoice;
    private String categoryName;
    private BigDecimal sellPrice;

    //GET
    public String getInvoice() {
        return invoice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    //SET
    public void setInvoice(String _invoice) {
        invoice = _invoice;
    }

    public void setCategoryName(String _categoryName) {
        categoryName = _categoryName;
    }

    public void setSellPrice(BigDecimal _sellPrice) {
        sellPrice = _sellPrice;
    }

    //CHECK SET
    public boolean chkSetInvoice(String _invoice) {
        if (_invoice.length() > 0 && _invoice.length() <= 50 && !(_invoice.isEmpty())) {
            invoice = _invoice;
            return true;
        }

        return false;
    }

    public boolean chkSetCategoryName(String _categoryName) {
        if (_categoryName.length() > 0 && _categoryName.length() <= 20 && !(_categoryName.isEmpty())) {
            categoryName = _categoryName;
            return true;
        }

        return false;
    }

    public boolean chkSetSellPrice(String _sellPrice) {
        try {
            BigDecimal mSellPrice = new BigDecimal(_sellPrice);

            if (mSellPrice.compareTo(BigDecimal.ZERO) > 0) {
                sellPrice = mSellPrice;
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
