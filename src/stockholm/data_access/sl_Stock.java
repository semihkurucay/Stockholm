/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.data_access;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import stockholm.user_interface.cl_InvoiceStock;
import stockholm.user_interface.cl_Stock;

/**
 *
 * @author semih
 */
public class sl_Stock {

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
            rs = stt.executeQuery("Select stk_ID,stk_Name,ctg_Name,stk_UnitType,stk_Amount from tbl_Stock inner join tbl_Category on tbl_Stock.stk_ctg_ID=tbl_Category.ctg_ID");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("stk_ID"), rs.getString("stk_Name"), rs.getString("ctg_Name"), rs.getString("stk_UnitType"), rs.getInt("stk_Amount")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.getTableList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.getTableList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, cl_Stock stock) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select stk_ID,stk_Name,ctg_Name,stk_UnitType,stk_Amount from tbl_Stock inner join tbl_Category on tbl_Stock.stk_ctg_ID=tbl_Category.ctg_ID where stk_ID LIKE '%" + stock.getID() + "%' AND stk_Name LIKE '%" + stock.getName() + "%' AND stk_ctg_ID LIKE '%" + stock.getCategoryID() + "%'");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("stk_ID"), rs.getString("stk_Name"), rs.getString("ctg_Name"), rs.getString("stk_UnitType"), rs.getInt("stk_Amount")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.getTableSearchList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.getTableSearchList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public cl_Stock getInformation(String id) {
        cl_Stock stock = new cl_Stock();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Stock where stk_ID='" + id + "'");

            while (rs.next()) {
                stock.setID(rs.getString("stk_ID"));
                stock.setName(rs.getString("stk_Name"));
                stock.setCategoryID(rs.getString("stk_ctg_ID"));
                stock.setUnitType(rs.getString("stk_UnitType"));
                stock.setAmount(rs.getInt("stk_Amount"));
                stock.setKdv(rs.getBigDecimal("stk_KDV"));
                stock.setPrice(rs.getBigDecimal("stk_Price"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.getInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.getInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return stock;
    }

    public cl_InvoiceStock getInformationStockInvoice(String id) {
        cl_InvoiceStock stock = new cl_InvoiceStock();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Stock where stk_ID='" + id + "'");

            while (rs.next()) {
                stock.setID(rs.getString("stk_ID"));
                stock.setName(rs.getString("stk_Name"));
                stock.setCategoryID(rs.getString("stk_ctg_ID"));
                stock.setUnitType(rs.getString("stk_UnitType"));
                stock.setAmount(rs.getInt("stk_Amount"));
                stock.setKdv(rs.getBigDecimal("stk_KDV"));
                stock.setPrice(rs.getBigDecimal("stk_Price"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.getInformationStockInvoice metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.getInformationStockInvoice] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return stock;
    }

    public cl_Stock getSearchInformation(cl_Stock getStock) {
        cl_Stock stock = new cl_Stock();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Stock where stk_ID LIKE '%" + getStock.getID() + "%' AND stk_Name LIKE '%" + getStock.getName() + "%' AND stk_ctg_ID LIKE '%" + getStock.getCategoryID() + "%'");

            while (rs.next()) {
                stock.setID(rs.getString("stk_ID"));
                stock.setName(rs.getString("stk_Name"));
                stock.setCategoryID(rs.getString("stk_ctg_ID"));
                stock.setUnitType(rs.getString("stk_UnitType"));
                stock.setAmount(rs.getInt("stk_Amount"));
                stock.setKdv(rs.getBigDecimal("stk_KDV"));
                stock.setPrice(rs.getBigDecimal("stk_Price"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.getSearchInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.getSearchInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return stock;
    }

    public int getAmount(String id) {
        int amount = 0;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select stk_Amount from tbl_Stock where stk_ID='" + id + "'");

            while (rs.next()) {
                amount = rs.getInt("stk_Amount");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.getAmount metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.getAmount] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return amount;
    }

    public boolean isThereID(String id) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Stock where stk_ID='" + id + "'");

            while (rs.next()) {
                isThere = true;
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.isThereID metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.isThereID] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public boolean inStock(cl_InvoiceStock stock) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Stock set stk_Amount=stk_Amount+? where stk_ID=?");
            pstt.setInt(1, stock.getAmount());
            pstt.setString(2, stock.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.inStock metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.inStock] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean outStock(cl_InvoiceStock stock) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Stock set stk_Amount=stk_Amount-? where stk_ID=?");
            pstt.setInt(1, stock.getAmount());
            pstt.setString(2, stock.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.outStock metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.outStock] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isAdd(cl_Stock stock) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Stock (stk_ID,stk_Name,stk_ctg_ID,stk_UnitType,stk_Amount,stk_KDV,stk_Price) values (?,?,?,?,?,?,?)");
            pstt.setString(1, stock.getID());
            pstt.setString(2, stock.getName());
            pstt.setString(3, stock.getCategoryID());
            pstt.setString(4, stock.getUnitType());
            pstt.setInt(5, 0);
            pstt.setBigDecimal(6, stock.getKdv());
            pstt.setBigDecimal(7, stock.getPrice());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.isAdd metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.isAdd] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isUpdate(cl_Stock stock) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Stock set stk_Name=?,stk_ctg_ID=?,stk_UnitType=?,stk_KDV=?,stk_Price=? where stk_ID=?");
            pstt.setString(1, stock.getName());
            pstt.setString(2, stock.getCategoryID());
            pstt.setString(3, stock.getUnitType());
            pstt.setBigDecimal(4, stock.getKdv());
            pstt.setBigDecimal(5, stock.getPrice());
            pstt.setString(6, stock.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.isUpdate metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.isUpdate] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isDelete(cl_Stock stock) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Stock where stk_ID=?");
            pstt.setString(1, stock.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Stock.isDelete metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Stock.isDelete] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
