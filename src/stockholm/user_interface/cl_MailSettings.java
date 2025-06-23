/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

/**
 *
 * @author semih
 */
public class cl_MailSettings {

    private int id;
    private String mail;
    private String password;
    private int port;
    private String host;
    private boolean auth;
    private boolean starttls;

    public cl_MailSettings() {

    }

    public cl_MailSettings(String _mail, String _password, int _port, String _host, boolean _auth, boolean _starttls) {
        mail = _mail;
        password = _password;
        port = _port;
        host = _host;
        auth = _auth;
        starttls = _starttls;
    }

    //GET
    public int getID() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public boolean getAuth() {
        return auth;
    }

    public boolean getStarttls() {
        return starttls;
    }

    //SET
    public void setID(int _id) {
        id = _id;
    }

    public void setMail(String _mail) {
        mail = _mail;
    }

    public void setPassword(String _password) {
        password = _password;
    }

    public void setPort(int _port) {
        port = _port;
    }

    public void setHost(String _host) {
        host = _host;
    }

    public void setAuth(boolean _auth) {
        auth = _auth;
    }

    public void setStarttls(boolean _starttls) {
        starttls = _starttls;
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

    public boolean chkSetMail(String _mail) {
        if (_mail.length() <= 50) {
            mail = _mail;
            return true;
        }

        return false;
    }

    public boolean chkSetPassword(String _password) {
        if (_password.length() <= 50) {
            password = _password;
            return true;
        }

        return false;
    }

    public boolean chkSetPort(String _port) {
        try {
            int mPort = Integer.parseInt(_port);
            port = mPort;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean chkSetHost(String _host) {
        if (_host.length() <= 50) {
            host = _host;
            return true;
        }

        return false;
    }
}
