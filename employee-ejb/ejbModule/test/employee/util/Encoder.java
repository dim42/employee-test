package test.employee.util;

import java.io.UnsupportedEncodingException;

public class Encoder {
    /**
     * Перекодирует строку в utf8.
     * 
     * @param string
     *            исходная строка
     * @return строка-результат
     */
    public static String encode(String string) {
        try {
            string = new String(string.getBytes("ISO-8859-1"), "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }
}
