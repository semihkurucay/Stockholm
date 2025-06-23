/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

/**
 *
 * @author semih
 */
public class cl_NoteList {

    private int id;
    private String title;
    private String text;

    //GET
    public int getID() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    //SET
    public void searchSetID(String _id) {
        try {
            int nId = Integer.parseInt(_id);
            id = nId;
        } catch (Exception e) {
            id = -1;
        }
    }

    public void setID(int _id) {
        id = _id;
    }

    public void setTitle(String _title) {
        title = _title;
    }

    public void setText(String _text) {
        text = _text;
    }

    //CHECK SET
    public boolean chkSetID(String _id) {
        try {
            int nId = Integer.parseInt(_id);
            id = nId;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean chkSetTitle(String _title) {
        if (_title.length() > 0 && _title.length() <= 50 && !(_title.isEmpty())) {
            title = _title;
            return true;
        }

        return false;
    }

    public boolean chkSetText(String _text) {
        if (_text.length() > 0 && _text.length() <= 1000 && !(_text.isEmpty())) {
            text = _text;
            return true;
        }

        return false;
    }
}
