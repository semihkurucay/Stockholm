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
import stockholm.user_interface.cl_ShowMessage;

/**
 *
 * @author semih
 */
public class sl_ShowMessage {

    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private db_SqlConnection sConn = new db_SqlConnection();

    public cl_ShowMessage getMessage() {
        String message = "(BİLGİ SAĞLANAMADI)";

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_ShowMessage");

            while (rs.next()) {
                message = rs.getString("mes_Text");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_ShowMessage.getMessage metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_ShowMessage.getMessage] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return new cl_ShowMessage(message);
    }

    public boolean isUpdate(cl_ShowMessage message) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_ShowMessage set mes_Text=?");
            pstt.setString(1, message.getMessage());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_ShowMessage.isUpdate metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_ShowMessage.isUpdate] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
