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
import stockholm.user_interface.cl_Business;
import stockholm.user_interface.cl_InvoiceBusiness;

/**
 *
 * @author semih
 */
public class sl_Business {

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
            rs = stt.executeQuery("Select bss_ID,bss_BusinessName from tbl_Business");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("bss_ID"), rs.getString("bss_BusinessName")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.getTableList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.getTableList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, cl_Business business) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select bss_ID,bss_BusinessName from tbl_Business where bss_ID LIKE '%" + business.getID() + "%' AND bss_TaxOffice LIKE '%" + business.getTaxOffice() + "%' AND bss_CompRegNo LIKE '%" + business.getCompRegNo() + "%' AND bss_BusinessName LIKE '%" + business.getBusinessName() + "%' AND bss_UserNameSurname LIKE '%" + business.getNameSurname() + "%' AND bss_Phone LIKE '%" + business.getPhone() + "%' AND bss_Mail LIKE '%" + business.getMail() + "%' AND bss_Country LIKE '%" + business.getCountry() + "%' AND bss_City LIKE '%" + business.getCity() + "%' AND bss_District LIKE '%" + business.getDistrict() + "%' AND bss_Nightbrood LIKE '%" + business.getNightbrood() + "%'");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getString("bss_ID"), rs.getString("bss_BusinessName")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.getTableSearchList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.getTableSearchList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public cl_Business getInformation(String id) {
        cl_Business business = new cl_Business();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Business where bss_ID ='" + id + "'");

            while (rs.next()) {
                business.setID(rs.getString("bss_ID"));
                business.setTaxOffice(rs.getString("bss_TaxOffice"));
                business.setCompRegNo(rs.getString("bss_CompRegNo"));
                business.setBusinessName(rs.getString("bss_BusinessName"));
                business.setNameSurname(rs.getString("bss_UserNameSurname"));
                business.setPhone(rs.getString("bss_Phone"));
                business.setMail(rs.getString("bss_Mail"));
                business.setCountry(rs.getString("bss_Country"));
                business.setCity(rs.getString("bss_City"));
                business.setDistrict(rs.getString("bss_District"));
                business.setNightbrood(rs.getString("bss_Nightbrood"));
                business.setAddress(rs.getString("bss_Address"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.getInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.getInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return business;
    }

    public cl_InvoiceBusiness getInformationBusinessInvoice(String id) {
        cl_InvoiceBusiness business = new cl_InvoiceBusiness();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Business where bss_ID ='" + id + "'");

            while (rs.next()) {
                business.setID(rs.getString("bss_ID"));
                business.setTaxOffice(rs.getString("bss_TaxOffice"));
                business.setCompRegNo(rs.getString("bss_CompRegNo"));
                business.setBusinessName(rs.getString("bss_BusinessName"));
                business.setNameSurname(rs.getString("bss_UserNameSurname"));
                business.setPhone(rs.getString("bss_Phone"));
                business.setMail(rs.getString("bss_Mail"));
                business.setCountry(rs.getString("bss_Country"));
                business.setCity(rs.getString("bss_City"));
                business.setDistrict(rs.getString("bss_District"));
                business.setNightbrood(rs.getString("bss_Nightbrood"));
                business.setAddress(rs.getString("bss_Address"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.getInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.getInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return business;
    }

    public cl_Business getSearchInformation(cl_Business business) {
        cl_Business getBusiness = new cl_Business();

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Business where bss_ID LIKE '%" + business.getID() + "%' AND bss_TaxOffice LIKE '%" + business.getTaxOffice() + "%' AND bss_CompRegNo LIKE '%" + business.getCompRegNo() + "%' AND bss_BusinessName LIKE '%" + business.getBusinessName() + "%' AND bss_UserNameSurname LIKE '%" + business.getNameSurname() + "%' AND bss_Phone LIKE '%" + business.getPhone() + "%' AND bss_Mail LIKE '%" + business.getMail() + "%' AND bss_Country LIKE '%" + business.getCountry() + "%' AND bss_City LIKE '%" + business.getCity() + "%' AND bss_District LIKE '%" + business.getDistrict() + "%' AND bss_Nightbrood LIKE '%" + business.getNightbrood() + "%'");

            while (rs.next()) {
                getBusiness.setID(rs.getString("bss_ID"));
                getBusiness.setTaxOffice(rs.getString("bss_TaxOffice"));
                getBusiness.setCompRegNo(rs.getString("bss_CompRegNo"));
                getBusiness.setBusinessName(rs.getString("bss_BusinessName"));
                getBusiness.setNameSurname(rs.getString("bss_UserNameSurname"));
                getBusiness.setPhone(rs.getString("bss_Phone"));
                getBusiness.setMail(rs.getString("bss_Mail"));
                getBusiness.setCountry(rs.getString("bss_Country"));
                getBusiness.setCity(rs.getString("bss_City"));
                getBusiness.setDistrict(rs.getString("bss_District"));
                getBusiness.setNightbrood(rs.getString("bss_Nightbrood"));
                getBusiness.setAddress(rs.getString("bss_Address"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.getSearchInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.getSearchInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return getBusiness;
    }

    public String getBusinessName(String id) {
        String bName = "(BİLGİ SAĞLANAMADI)";

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Business where bss_ID ='" + id + "'");

            while (rs.next()) {
                bName = rs.getString("bss_BusinessName");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.getBusinessName metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.getBusinessName] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return bName;
    }

    public boolean isThereID(String id) {
        boolean isThere = false;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Business where bss_ID ='" + id + "'");

            while (rs.next()) {
                isThere = true;
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.isThereID metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.isThereID] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isThere;
    }

    public boolean isAdd(cl_Business business) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Business (bss_ID,bss_TaxOffice,bss_CompRegNo,bss_BusinessName,bss_UserNameSurname,bss_Phone,bss_Mail,bss_Country,bss_City,bss_District,bss_Nightbrood,bss_Address) values (?,?,?,?,?,?,?,?,?,?,?,?)");
            pstt.setString(1, business.getID());
            pstt.setString(2, business.getTaxOffice());
            pstt.setString(3, business.getCompRegNo());
            pstt.setString(4, business.getBusinessName());
            pstt.setString(5, business.getNameSurname());
            pstt.setString(6, business.getPhone());
            pstt.setString(7, business.getMail());
            pstt.setString(8, business.getCountry());
            pstt.setString(9, business.getCity());
            pstt.setString(10, business.getDistrict());
            pstt.setString(11, business.getNightbrood());
            pstt.setString(12, business.getAddress());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.isAdd metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.isAdd] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isUpdate(cl_Business business) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Business set bss_TaxOffice=?,bss_CompRegNo=?,bss_BusinessName=?,bss_UserNameSurname=?,bss_Phone=?,bss_Mail=?,bss_Country=?,bss_City=?,bss_District=?,bss_Nightbrood=?,bss_Address=? where bss_ID=?");
            pstt.setString(1, business.getTaxOffice());
            pstt.setString(2, business.getCompRegNo());
            pstt.setString(3, business.getBusinessName());
            pstt.setString(4, business.getNameSurname());
            pstt.setString(5, business.getPhone());
            pstt.setString(6, business.getMail());
            pstt.setString(7, business.getCountry());
            pstt.setString(8, business.getCity());
            pstt.setString(9, business.getDistrict());
            pstt.setString(10, business.getNightbrood());
            pstt.setString(11, business.getAddress());
            pstt.setString(12, business.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.isUpdate metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.isUpdate] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isDelete(cl_Business business) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Business where bss_ID=?");
            pstt.setString(1, business.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_Business.isDelete metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_Business.isDelete] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
