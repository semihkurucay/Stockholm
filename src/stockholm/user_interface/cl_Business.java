/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

/**
 *
 * @author semih
 */
public class cl_Business {

    private String id;
    private String taxOffice;
    private String compRegNo;
    private String businessName;
    private String nameSurname;
    private String phone;
    private String mail;
    private String country;
    private String city;
    private String district;
    private String nightbrood;
    private String address;

    //GET
    public String getID() {
        return id;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public String getCompRegNo() {
        return compRegNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getNightbrood() {
        return nightbrood;
    }

    public String getAddress() {
        return address;
    }

    //SET
    public void setID(String _id) {
        id = _id;
    }

    public void setTaxOffice(String _taxOffice) {
        taxOffice = _taxOffice;
    }

    public void setCompRegNo(String _compRegNo) {
        compRegNo = _compRegNo;
    }

    public void setBusinessName(String _businessName) {
        businessName = _businessName;
    }

    public void setNameSurname(String _nameSurname) {
        nameSurname = _nameSurname;
    }

    public void setPhone(String _phone) {
        phone = _phone;
    }

    public void setMail(String _mail) {
        mail = _mail;
    }

    public void setCountry(String _country) {
        country = _country;
    }

    public void setCity(String _city) {
        city = _city;
    }

    public void setDistrict(String _district) {
        district = _district;
    }

    public void setNightbrood(String _nightbrood) {
        nightbrood = _nightbrood;
    }

    public void setAddress(String _address) {
        address = _address;
    }

    //CHECK SET
    public boolean chkSetID(String _id) {
        if (_id.length() > 0 && _id.length() <= 15 && !(_id.isEmpty()) && _id.matches("^\\d{1,15}$")) {
            id = _id;
            return true;
        }

        return false;
    }

    public boolean chkSetTaxOffice(String _taxOffice) {
        if (_taxOffice.length() <= 50) {
            taxOffice = _taxOffice;
            return true;
        }

        return false;
    }

    public boolean chkSetCompRegNo(String _compRegNo) {
        if (_compRegNo.length() <= 50) {
            compRegNo = _compRegNo;
            return true;
        }

        return false;
    }

    public boolean chkSetBusinessName(String _businessName) {
        if (_businessName.length() > 0 && _businessName.length() <= 100) {
            businessName = _businessName;
            return true;
        }

        return false;
    }

    public boolean chkSetNameSurname(String _nameSurname) {
        if (_nameSurname.length() <= 50) {
            nameSurname = _nameSurname;
            return true;
        }

        return false;
    }

    public boolean chkSetPhone(String _phone) {
        if (_phone.length() == 0 || _phone.matches("^\\d{1,15}$")) {
            phone = _phone;
            return true;
        }

        return false;
    }

    public boolean chkSetMail(String _mail) {
        if (_mail.length() == 0 || (_mail.length() <= 50 && _mail.contains("@") && _mail.contains("."))) {
            mail = _mail;
            return true;
        }

        return false;
    }

    public boolean chkSetCountry(String _country) {
        if (_country.length() > 0 && _country.length() <= 100 && !(_country.isEmpty())) {
            country = _country;
            return true;
        }

        return false;
    }

    public boolean chkSetCity(String _city) {
        if (_city.length() > 0 && _city.length() <= 100 && !(_city.isEmpty())) {
            city = _city;
            return true;
        }

        return false;
    }

    public boolean chkSetDistrict(String _district) {
        if (_district.length() > 0 && _district.length() <= 100 && !(_district.isEmpty())) {
            district = _district;
            return true;
        }

        return false;
    }

    public boolean chkSetNightbrood(String _nightbrood) {
        if (_nightbrood.length() > 0 && _nightbrood.length() <= 100 && !(_nightbrood.isEmpty())) {
            nightbrood = _nightbrood;
            return true;
        }

        return false;
    }

    public boolean chkSetAddress(String _address) {
        if (_address.length() > 0 && _address.length() <= 100 && !(_address.isEmpty())) {
            address = _address;
            return true;
        }

        return false;
    }

}
