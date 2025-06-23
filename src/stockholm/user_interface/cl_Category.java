/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

/**
 *
 * @author semih
 */
public class cl_Category {

    private String id;
    private String name;

    public cl_Category() {

    }

    public cl_Category(String _id, String _name) {
        id = _id;
        name = _name;
    }

    //GET
    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    //SET
    public void setID(String _id) {
        id = _id;
    }

    public void setName(String _name) {
        name = _name;
    }

    //CHECK SET
    public boolean chkSetID(String _id) {
        if (_id.length() > 0 && _id.length() <= 20 && !(_id.isEmpty()) && !(_id.contains(" "))) {
            id = _id;
            return true;
        }
        return false;
    }

    public boolean chkSetName(String _name) {
        if (_name.trim().length() > 0 && _name.length() <= 20 && !(_name.isEmpty())) {
            name = _name;
            return true;
        }
        return false;
    }
}
