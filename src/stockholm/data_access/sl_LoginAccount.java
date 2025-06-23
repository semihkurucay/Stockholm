/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.data_access;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import stockholm.user_interface.cl_LoginAccount;

/**
 *
 * @author semih
 */
public class sl_LoginAccount {

    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private db_SqlConnection sConn = new db_SqlConnection();

    public boolean isLogin(cl_LoginAccount account) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_LoginAccount where acc_Username collate Latin1_General_CS_AS = '" + account.getUsername() + "' and acc_Password collate Latin1_General_CS_AS = '" + account.getPassword() + "'");

            while (rs.next()) {
                isComplate = true;
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_LoginAccount.isLogin metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_LoginAccount.isLogin] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public cl_LoginAccount getInformation() {
        String username = "(BİLGİ SAĞLANAMADI)";
        String password = "(BİLGİ SAĞLANAMADI)";

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_LoginAccount");

            while (rs.next()) {
                username = rs.getString("acc_Username");
                password = rs.getString("acc_Password");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_LoginAccount.getInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_LoginAccount.getInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return new cl_LoginAccount(username, password);
    }

    public boolean isUpdate(cl_LoginAccount account) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_LoginAccount set acc_Username=?,acc_Password=?");
            pstt.setString(1, account.getUsername());
            pstt.setString(2, account.getPassword());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_LoginAccount.isUpdate metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_LoginAccount.isUpdate] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
