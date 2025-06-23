/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.data_access;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import stockholm.user_interface.cl_Invoice;
import stockholm.user_interface.cl_InvoiceBusiness;
import stockholm.user_interface.cl_InvoiceStock;

/**
 *
 * @author semih
 */
public class sl_Invoice {

    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private db_SqlConnection sConn = new db_SqlConnection();

    public void getTableList(JTable table) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT i.inv_Invoice, b.inv_bss_ID, i.inv_Products, i.inv_Type, i.inv_Date FROM tbl_Invoice i INNER JOIN tbl_InvoiceBusiness b ON i.inv_Invoice = b.inv_bss_Invoice INNER JOIN tbl_InvoiceStock s ON i.inv_Invoice = s.inv_stk_Invoice GROUP BY i.inv_Invoice, b.inv_bss_ID, i.inv_Type, i.inv_Date, i.inv_Products ORDER BY i.inv_Date DESC");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("inv_Invoice"), rs.getString("inv_bss_ID"), rs.getInt("inv_Products"), rs.getString("inv_Type"), rs.getString("inv_Date")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.getTableList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.getTableList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, cl_Invoice invoice, cl_InvoiceStock stock, cl_InvoiceBusiness business) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT i.inv_Invoice, b.inv_bss_ID, i.inv_Products, i.inv_Type, i.inv_Date FROM tbl_Invoice i INNER JOIN tbl_InvoiceBusiness b ON i.inv_Invoice = b.inv_bss_Invoice INNER JOIN tbl_InvoiceStock s ON i.inv_Invoice = s.inv_stk_Invoice where i.inv_Invoice LIKE '%" + invoice.getInvoice() + "%' AND b.inv_bss_ID LIKE '%" + business.getID() + "%' AND i.inv_Type='" + invoice.getType() + "' AND i.inv_Date LIKE '%" + invoice.getDate() + "%' AND s.inv_stk_Code LIKE '%" + stock.getID() + "%' GROUP BY i.inv_Invoice, b.inv_bss_ID, i.inv_Type, i.inv_Date, i.inv_Products ORDER BY i.inv_Date DESC ");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("inv_Invoice"), rs.getString("inv_bss_ID"), rs.getInt("inv_Products"), rs.getString("inv_Type"), rs.getString("inv_Date")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.getTableList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.getTableList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultListModel<String> getProductList(String invoice) {
        DefaultListModel<String> products = new DefaultListModel<>();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select inv_stk_Code,inv_stk_Amount, inv_stk_Price*(1+inv_stk_KDV/100) as 'price',inv_stk_SellPrice from tbl_InvoiceStock where inv_stk_Invoice='" + invoice + "'");

            while (rs.next()) {
                products.addElement(rs.getString("inv_stk_Code") + " + " + rs.getInt("inv_stk_Amount") + " + " + rs.getBigDecimal("price").setScale(2, RoundingMode.HALF_UP) + " + " + rs.getBigDecimal("inv_stk_SellPrice").setScale(2, RoundingMode.HALF_UP));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.getProductList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.getProductList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return products;
    }

    public cl_InvoiceStock getStockInformation(cl_InvoiceStock stock) {
        cl_InvoiceStock getStock = new cl_InvoiceStock();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_InvoiceStock where inv_stk_Invoice='" + stock.getInvoice() + "' AND inv_stk_Code='" + stock.getID() + "'");

            while (rs.next()) {
                getStock.setID(rs.getString("inv_stk_Code"));
                getStock.setName(rs.getString("inv_stk_Name"));
                getStock.setCategoryID(rs.getString("inv_stk_ctg_ID"));
                getStock.chkSetCategoryName(rs.getString("inv_stk_ctg_Name"));
                getStock.setAmount(rs.getInt("inv_stk_Amount"));
                getStock.setUnitType(rs.getString("inv_stk_UnitType"));
                getStock.setPrice(rs.getBigDecimal("inv_stk_Price"));
                getStock.setKdv(rs.getBigDecimal("inv_stk_KDV"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.getStockInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.getStockInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return getStock;
    }

    public cl_InvoiceBusiness getBusinessInformation(cl_InvoiceBusiness business) {
        cl_InvoiceBusiness getBusiness = new cl_InvoiceBusiness();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select * from tbl_InvoiceBusiness where inv_bss_Invoice='" + business.getInvoice() + "' AND inv_bss_ID='" + business.getID() + "'");

            while (rs.next()) {
                getBusiness.setID(rs.getString("inv_bss_ID"));
                getBusiness.setTaxOffice(rs.getString("inv_bss_TaxOffice"));
                getBusiness.setCompRegNo(rs.getString("inv_bss_CompRegNo"));
                getBusiness.setBusinessName(rs.getString("inv_bss_BusinessName"));
                getBusiness.setNameSurname(rs.getString("inv_bss_UserNameSurname"));
                getBusiness.setPhone(rs.getString("inv_bss_Phone"));
                getBusiness.setMail(rs.getString("inv_bss_Mail"));
                getBusiness.setCountry(rs.getString("inv_bss_Country"));
                getBusiness.setCity(rs.getString("inv_bss_City"));
                getBusiness.setDistrict(rs.getString("inv_bss_District"));
                getBusiness.setNightbrood(rs.getString("inv_bss_Nightbrood"));
                getBusiness.setAddress(rs.getString("inv_bss_Address"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.getBusinessInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.getBusinessInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return getBusiness;
    }
    
    public BigDecimal getProcessPrice(String date, String type) {
        BigDecimal price = new BigDecimal(BigInteger.ZERO);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("SELECT ISNULL(SUM(inv_stk_SellPrice), 0) AS sumPrice FROM tbl_Invoice i INNER JOIN tbl_InvoiceStock s ON i.inv_Invoice = s.inv_stk_Invoice where i.inv_Date LIKE '%" + date + "%' AND i.inv_Type='" + type + "'");

            while (rs.next()) {
                price = rs.getBigDecimal("sumPrice");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.getProcessPrice metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.getProcessPrice] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
        
        return price;
    }
    
    public int getProcessCount(String date) {
        int count = 0;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("select COUNT(inv_Invoice) as 'countProcess' from tbl_Invoice where inv_Date LIKE '%" + date + "%'");

            while (rs.next()) {
                count =rs.getInt("countProcess");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.getProcessCount metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.getProcessCount] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return count;
    }

    public boolean isThereInvoice(String invoice) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Invoice where inv_Invoice ='" + invoice + "'");

            while (rs.next()) {
                isThere = true;
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.isThereInvoice metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.isThereInvoice] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public boolean isAdd(cl_Invoice invoice, cl_InvoiceBusiness business, cl_InvoiceStock[] stocks) {
        boolean isInvoice = isAddInvoice(invoice), isBusiness = isAddBusiness(business), isStocks = true;
        boolean isError = false;
        String errorID = "";

        for (cl_InvoiceStock stock : stocks) {
            if (!(isAddStock(stock))) {
                isStocks = false;
            }

            if (invoice.getType().equals("ALIŞ")) {
                if (!(isError)) {
                    if (!(new sl_Stock().inStock(stock))) {
                        errorID = stock.getID();
                        isError = true;
                    }
                }
            } else if (invoice.getType().equals("SATIŞ")) {
                if (!(isError)) {
                    if (!(new sl_Stock().outStock(stock))) {
                        errorID = stock.getID();
                        isError = true;
                    }
                }
            }
        }

        if (isInvoice && isBusiness && isStocks && !(isError)) {
            return true;
        } else {

            if (isBusiness) {
                isDeleteBusiness(business.getInvoice());
            }
            if (isStocks) {
                isDeleteStock(stocks[0].getInvoice());
            }

            if (errorID.length() > 0) {
                isError = false;
                for (cl_InvoiceStock stock : stocks) {

                    if (invoice.getType().equals("ALIŞ")) {
                        if (!(stock.getID().equals(errorID))) {
                            if (!(isError)) {
                                new sl_Stock().outStock(stock);
                            }
                        } else {
                            isError = true;
                        }
                    } else if (invoice.getType().equals("SATIŞ")) {
                        if (!(stock.getID().equals(errorID))) {
                            if (!(isError)) {
                                new sl_Stock().inStock(stock);
                            }
                        } else {
                            isError = true;
                        }
                    }
                }
            }

            if (isInvoice) {
                isDeleteInvoice(invoice.getInvoice());
            }

            return false;
        }
    }

    public void isDelete(String invoice) {
        isDeleteBusiness(invoice);
        isDeleteStock(invoice);
        isDeleteInvoice(invoice);
    }

    private boolean isAddInvoice(cl_Invoice invoice) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Invoice (inv_Invoice,inv_Date,inv_Type,inv_Products) values (?,?,?,?)");
            pstt.setString(1, invoice.getInvoice());
            pstt.setString(2, invoice.getDate());
            pstt.setString(3, invoice.getType());
            pstt.setInt(4, invoice.getProducts());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.isAddInvoice metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.isAddInvoice] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    private boolean isAddBusiness(cl_InvoiceBusiness business) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_InvoiceBusiness (inv_bss_Invoice,inv_bss_ID,inv_bss_TaxOffice,inv_bss_CompRegNo,inv_bss_BusinessName,inv_bss_UserNameSurname,inv_bss_Phone,inv_bss_Mail,inv_bss_Country,inv_bss_City,inv_bss_District,inv_bss_Nightbrood,inv_bss_Address) values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstt.setString(1, business.getInvoice());
            pstt.setString(2, business.getID());
            pstt.setString(3, business.getTaxOffice());
            pstt.setString(4, business.getCompRegNo());
            pstt.setString(5, business.getBusinessName());
            pstt.setString(6, business.getNameSurname());
            pstt.setString(7, business.getPhone());
            pstt.setString(8, business.getMail());
            pstt.setString(9, business.getCountry());
            pstt.setString(10, business.getCity());
            pstt.setString(11, business.getDistrict());
            pstt.setString(12, business.getNightbrood());
            pstt.setString(13, business.getAddress());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.isAddBusiness metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.isAddBusiness] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    private boolean isAddStock(cl_InvoiceStock stock) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_InvoiceStock (inv_stk_Invoice,inv_stk_Code,inv_stk_Name,inv_stk_ctg_ID,inv_stk_ctg_Name,inv_stk_UnitType,inv_stk_Amount,inv_stk_KDV,inv_stk_Price,inv_stk_SellPrice) values (?,?,?,?,?,?,?,?,?,?)");
            pstt.setString(1, stock.getInvoice());
            pstt.setString(2, stock.getID());
            pstt.setString(3, stock.getName());
            pstt.setString(4, stock.getCategoryID());
            pstt.setString(5, stock.getCategoryName());
            pstt.setString(6, stock.getUnitType());
            pstt.setInt(7, stock.getAmount());
            pstt.setBigDecimal(8, stock.getKdv());
            pstt.setBigDecimal(9, stock.getPrice());
            pstt.setBigDecimal(10, stock.getSellPrice());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.isAddStock metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.isAddStock] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    private boolean isDeleteInvoice(String invoice) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Invoice where inv_Invoice=?");
            pstt.setString(1, invoice);
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.isDeleteInvoice metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.isDeleteInvoice] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    private boolean isDeleteBusiness(String business) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_InvoiceBusiness where inv_bss_Invoice=?");
            pstt.setString(1, business);
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.isAddBusiness metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.isAddBusiness] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    private boolean isDeleteStock(String stock) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_InvoiceStock where inv_stk_Invoice=?");
            pstt.setString(1, stock);
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Invoice.isDeleteStock metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Invoice.isDeleteStock] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
