/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

/**
 *
 * @author semih
 */
public class cl_LoginAccount {

    private int id;
    private String username;
    private String password;

    public cl_LoginAccount() {

    }

    public cl_LoginAccount(String _username, String _password) {
        username = _username;
        password = _password;
    }

    //GET
    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //SET
    public void setID(int _id) {
        id = _id;
    }

    public void setUsername(String _username) {
        username = _username;
    }

    public void setPassword(String _password) {
        password = _password;
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

    public boolean chkSetUsername(String _username) {
        if (_username.length() > 0 && _username.length() <= 20 && !(_username.isEmpty())) {
            username = _username;
            return true;
        }

        return false;
    }

    public boolean chkSetPassword(String _password) {
        if (_password.length() > 0 && _password.length() <= 20 && !(_password.isEmpty())) {
            password = _password;
            return true;
        }

        return false;
    }
}
