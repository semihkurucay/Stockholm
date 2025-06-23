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
import stockholm.user_interface.cl_NoteList;

/**
 *
 * @author semih
 */
public class sl_NoteList {

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
            rs = stt.executeQuery("Select not_ID,not_Title from tbl_Notepad");

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("not_ID"), rs.getString("not_Title")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_NoteList.getTableList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_NoteList.getTableList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public void getTableSearchList(JTable table, cl_NoteList note) {
        DefaultTableModel mTable = (DefaultTableModel) table.getModel();
        mTable.setRowCount(0);

        StringBuilder sqlQuary = new StringBuilder("Select not_ID,not_Title from tbl_Notepad where not_Title LIKE '%" + note.getTitle() + "%' AND not_Text LIKE '%" + note.getText() + "%'");
        if (note.getID() > -1) {
            sqlQuary.append(" AND not_ID = " + note.getID());
        }

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery(sqlQuary.toString());

            while (rs.next()) {
                mTable.addRow(new Object[]{rs.getInt("not_ID"), rs.getString("not_Title")});
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_NoteList.getTableSearchList metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_NoteList.getTableSearchList] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }
    }

    public cl_NoteList getInformation(String _id) {
        cl_NoteList note = new cl_NoteList();

        try {
            int id = Integer.parseInt(_id);

            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery("Select * from tbl_Notepad where not_ID =" + id);

            while (rs.next()) {
                note.setID(rs.getInt("not_ID"));
                note.setTitle(rs.getString("not_Title"));
                note.setText(rs.getString("not_Text"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_NoteList.getInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_NoteList.getInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Hatalı format girdiniz, ID değeri sadece sayı olmalıdır.", "Hatalı Format", JOptionPane.ERROR_MESSAGE);
        }

        return note;
    }

    public cl_NoteList getSearchInformation(cl_NoteList note) {
        cl_NoteList getNote = new cl_NoteList();

        StringBuilder sqlQuary = new StringBuilder("Select * from tbl_Notepad where not_Title LIKE '%" + note.getTitle() + "%' AND not_Text LIKE '%" + note.getText() + "%'");
        if (note.getID() > -1) {
            sqlQuary.append(" AND not_ID = " + note.getID());
        }

        try {
            conn = sConn.getConnection();
            stt = conn.createStatement();
            rs = stt.executeQuery(sqlQuary.toString());

            while (rs.next()) {
                getNote.setID(rs.getInt("not_ID"));
                getNote.setTitle(rs.getString("not_Title"));
                getNote.setText(rs.getString("not_Text"));
            }

            conn.close();
            stt.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_NoteList.getSearchInformation metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_NoteList.getSearchInformation] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return getNote;
    }

    public boolean isAdd(cl_NoteList note) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("insert into tbl_Notepad (not_Title,not_Text) values (?,?)");
            pstt.setString(1, note.getTitle());
            pstt.setString(2, note.getText());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_NoteList.isAdd metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_NoteList.isAdd] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isUpdate(cl_NoteList note) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("update tbl_Notepad set not_Title=?,not_Text=? where not_ID=?");
            pstt.setString(1, note.getTitle());
            pstt.setString(2, note.getText());
            pstt.setInt(3, note.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_NoteList.isUpdate metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_NoteList.isUpdate] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }

    public boolean isDelete(cl_NoteList note) {
        boolean isComplate = false;

        try {
            conn = sConn.getConnection();
            pstt = conn.prepareStatement("delete from tbl_Notepad where not_ID=?");
            pstt.setInt(1, note.getID());
            pstt.executeUpdate();

            conn.close();
            pstt.close();

            isComplate = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "sl_NoteList.isDelete metodunda sql hatası ile karşılaşıldı!\nErrorMessage:" + e.getMessage(), "[sl_NoteList.isDelete] Sql Hatası - " + e.getErrorCode(), JOptionPane.ERROR_MESSAGE);
        }

        return isComplate;
    }
}
