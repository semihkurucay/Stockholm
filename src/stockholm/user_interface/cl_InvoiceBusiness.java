/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.user_interface;

/**
 *
 * @author semih
 */
public class cl_InvoiceBusiness extends cl_Business {

    private String invoice;

    //GET
    public String getInvoice() {
        return invoice;
    }

    //SET
    public void setInvoice(String _invoice) {
        invoice = _invoice;
    }

    //CHECK SET
    public boolean chkSetInvoice(String _invoice) {
        if (_invoice.length() > 0 && _invoice.length() <= 50 && !(_invoice.isEmpty())) {
            invoice = _invoice;
            return true;
        }

        return false;
    }
}
