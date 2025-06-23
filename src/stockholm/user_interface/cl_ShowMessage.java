/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

/**
 *
 * @author semih
 */
public class cl_ShowMessage {

    private int id;
    private String message;

    public cl_ShowMessage() {

    }

    public cl_ShowMessage(String _message) {
        message = _message;
    }

    //GET
    public int getID() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    //SET
    public void setID(int _id) {
        id = _id;
    }

    public void setMessage(String _message) {
        message = _message;
    }

    //CHECK SET
    public boolean chkSetID(String _id) {
        try {
            int mID = Integer.parseInt(_id);
            id = mID;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean chkSetMessage(String _message) {
        if (_message.length() <= 1000) {
            message = _message;
            return true;
        }

        return false;
    }
}
