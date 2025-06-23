/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockholm.data_access;

import java.util.Random;

/**
 *
 * @author semih
 */
public class getInvoiceRandomCode {

    private static String[] chars = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
        "U", "V", "W", "X", "Y", "Z",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
        "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
        "u", "v", "w", "x", "y", "z",
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static String getInvoice() {
        Random rnd = new Random();
        String invoice = "ÜRETİLDİ_";

        for (int i = 0; i < 38; i++) {
            invoice += chars[rnd.nextInt(chars.length)];
        }

        return invoice;
    }
}
