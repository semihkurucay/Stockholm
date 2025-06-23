/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author semih
 */
public class cl_Stock {

    private String id;
    private String name;
    private String categoryID;
    private String unitType;
    private int amount;
    private BigDecimal kdv;
    private BigDecimal price;

    //GET
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getUnitType() {
        return unitType;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getKdv() {
        return kdv;
    }

    public BigDecimal getPrice() {
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    //SET
    public void setID(String _id) {
        id = _id;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setCategoryID(String _categoryID) {
        categoryID = _categoryID;
    }

    public void setUnitType(String _unitType) {
        unitType = _unitType;
    }

    public void setAmount(int _amount) {
        amount = _amount;
    }

    public void setKdv(BigDecimal _kdv) {
        kdv = _kdv;
    }

    public void setPrice(BigDecimal _price) {
        price = _price;
    }

    //CHECK SET
    public boolean chkSetID(String _id) {
        if (_id.length() > 0 && _id.length() <= 50 && !(_id.isEmpty()) && !(_id.contains(" ")) && !(_id.contains("+"))) {
            id = _id;
            return true;
        }

        return false;
    }

    public boolean chkSetName(String _name) {
        if (_name.length() > 0 && _name.length() <= 50 && !(_name.isEmpty()) && !(_name.contains("+"))) {
            name = _name;
            return true;
        }

        return false;
    }

    public boolean chkSetCategoryID(String _categoryID) {
        if (_categoryID.length() > 0 && _categoryID.length() <= 20 && !(_categoryID.isEmpty())) {
            categoryID = _categoryID;
            return true;
        }

        return false;
    }

    public boolean chkSetUnitType(String _unitType) {
        if (_unitType.length() > 0 && _unitType.length() <= 10 && !(_unitType.isEmpty())) {
            unitType = _unitType;
            return true;
        }

        return false;
    }

    public boolean chkSetAmount(String _amount) {
        try {
            int mAmount = Integer.parseInt(_amount);
            amount = mAmount;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean chkSetKdv(String _kdv) {
        try {
            BigDecimal mKDV = new BigDecimal(_kdv);

            if (mKDV.compareTo(new BigDecimal("999.99")) > 0 || mKDV.scale() > 2) {
                return false;
            }

            kdv = mKDV;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean chkSetPrice(String _price) {
        try {
            BigDecimal mPrice = new BigDecimal(_price);

            if (mPrice.compareTo(BigDecimal.ZERO) > 0) {
                price = mPrice;
                return true;
            }

            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
