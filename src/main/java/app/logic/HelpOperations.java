package app.logic;

public class HelpOperations {
    public static boolean isNumber(String string) {

        if (string == null || string.length() == 0 || string.startsWith("0")) return false;

        int i = 0;
        if (string.charAt(0) == '-') {
            if (string.length() == 1) {
                return false;
            }
            i = 1;
        }

        char c;
        for (; i < string.length(); i++) {
            c = string.charAt(i);
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }
}
