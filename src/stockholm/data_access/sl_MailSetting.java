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
import stockholm.user_interface.cl_MailSettings;

/**
 *
 * @author semih
 */
public class sl_MailSetting {

    private Connection conn = null;
    private ResultSet rs = null;
    private Statement stt = null;
    private PreparedStatement pstt = null;
    private db_SqlConnection sConn = new db_SqlConnection();

    public cl_MailSettings getInformation() {
        String mail = "(BİLGİ SAĞLANAMADI)";
        String password = "(BİLGİ SAĞLANAMADI)";
        int port = -1;
        String host = "(BİLGİ SAĞLANAMADI)";
        boolean auth = false;
        boolean starttls = false;

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_MailSetting");

            while (rs.next()) {
                mail = rs.getString("mal_Mail");
                password = rs.getString("mal_Password");
                port = rs.getInt("mal_Port");
                host = rs.getString("mal_Host");
                auth = rs.getBoolean("mal_Auth");
                starttls = rs.getBoolean("mal_Starttls");
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_MailSetting.getInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_LoginAccount.getInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return new cl_MailSettings(mail, password, port, host, auth, starttls);
    }

    public boolean isUpdate(cl_MailSettings mail) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_MailSetting set mal_Mail=?,mal_Password=?,mal_Port=?,mal_Host=?,mal_Auth=?,mal_Starttls=?");
            pstt.setString(1, mail.getMail());
            pstt.setString(2, mail.getPassword());
            pstt.setInt(3, mail.getPort());
            pstt.setString(4, mail.getHost());
            pstt.setBoolean(5, mail.getAuth());
            pstt.setBoolean(6, mail.getStarttls());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_MailSetting.isUpdate metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_LoginAccount.isUpdate] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
