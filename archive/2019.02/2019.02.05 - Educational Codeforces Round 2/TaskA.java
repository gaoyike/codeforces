package readman;


import java.util.Scanner;
import java.io.PrintWriter;

public class TaskA {
    public void solve(int testNumber, Scanner in, PrintWriter out) {
        String word = in.nextLine();
        StringBuffer tmp = new StringBuffer();
        StringBuffer a = new StringBuffer();
        a.append('"');
        StringBuffer b = new StringBuffer();
        b.append('"');
        String[] words = word.split(",", -1);
        for (String w : words) {
            for (String s : w.split(";", -1)) {
                if (isDigit(s) && (s.charAt(0) != '0' || s.equals("0"))) {
                    a.append(s);
                    a.append(',');
                }
                else {
                    b.append(s);
                    b.append(',');
                }
            }
        }
        if (a.length() == 1) {
            out.println('-');
        }
        else {
            out.println(a.substring(0, a.length()-1)+'"');
        }
        if (b.length() == 1) {
            out.println('-');
        }
        else {
            out.println(b.substring(0, b.length()-1)+'"');
        }
    }

    private boolean isDigit(String s) {
        if (s.isEmpty()) {
            return false;
        }
        for (Character c : s.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
