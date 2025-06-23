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
import stockholm.user_interface.cl_Category;

/**
 *
 * @author semih
 */
public class sl_Category {

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
            rs = stt.executeQuery("Select * from tbl_Category");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("ctg_ID"), rs.getString("ctg_Name")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Category.getTableList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.getTableList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, cl_Category category) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Category where ctg_ID LIKE '%" + category.getID() + "%' AND ctg_Name LIKE '%" + category.getName() + "%'");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("ctg_ID"), rs.getString("ctg_Name")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Category.getTableSearchList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.getTableSearchList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public cl_Category getInformation(String id) {
        cl_Category category = new cl_Category();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Category where ctg_ID='" + id + "'");

            while (rs.next()) {
                category.setID(rs.getString("ctg_ID"));
                category.setName(rs.getString("ctg_Name"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Category.getInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.getInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return category;
    }

    public String getCategoryName(String id) {
        String name = "!";

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Category where ctg_ID='" + id + "'");

            while (rs.next()) {
                name = rs.getString("ctg_Name");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Category.getCategoryName metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.getCategoryName] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return name;
    }

    public boolean isThereID(String id) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Category where ctg_ID='" + id + "'");

            while (rs.next()) {
                isThere = true;
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Category.isThereID metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.isThereID] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public boolean isAdd(cl_Category category) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Category (ctg_ID,ctg_Name) values (?,?)");
            pstt.setString(1, category.getID());
            pstt.setString(2, category.getName());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Category.isAdd metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.isAdd] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isUpdate(cl_Category category) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Category set ctg_Name=? where ctg_ID=?");
            pstt.setString(1, category.getName());
            pstt.setString(2, category.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Category.isUpdate metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.isUpdate] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isDelete(cl_Category category) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Category where ctg_ID=?");
            pstt.setString(1, category.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            if (e.getErrorCode() == 547) {
                JOptionPane.showMessageDialog(null, "Silmek istediğiniz kategori kullanılmaktadır. Kullanılan stoğu güncelleyin.", "Hatalı Silme İşlemi", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "sl_Category.isDelete metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Category.isDelete] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
            }
        }

        return isComplate;
    }
}
