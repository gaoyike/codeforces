package readman;

import net.egork.io.InputReader;
import net.egork.io.OutputWriter;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TaskD {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        double x1 = in.readDouble();
        double y1 = in.readDouble();
        double r1 = in.readDouble();
        double x2 = in.readDouble();
        double y2 = in.readDouble();
        double r2 = in.readDouble();
        MathContext mc = new MathContext(40, RoundingMode.HALF_UP);
        //两圆,圆心距
        BigDecimal c = BigDecimal.valueOf(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));
        if (r1 + r2 <= c.doubleValue()) {
            out.printFormat("%.20f\n", (double) 0.0);
            return;
        } //如果两圆不相交
        else if (Math.abs(r2 - r1) >= c.doubleValue()) {
            out.printFormat("%.20f\n", Math.PI * Math.pow(Math.min(r1, r2), 2)); // 面积是内圆的面积
            return;
        } else {
            //1的圆心角
            double r1_ang = Math.acos(BigDecimal.valueOf(r1).pow(2,mc).add(c.pow(2,mc)).subtract(BigDecimal.valueOf(r2).pow(2),mc).divide(BigDecimal.valueOf(2), 20, RoundingMode.CEILING).divide(BigDecimal.valueOf(r1),20, RoundingMode.CEILING).divide(c,20, RoundingMode.CEILING).doubleValue());
            //2的圆心角
            double r2_ang = Math.acos(BigDecimal.valueOf(r2).pow(2,mc).add(c.pow(2,mc)).subtract(BigDecimal.valueOf(r1).pow(2),mc).divide(BigDecimal.valueOf(2), 20, RoundingMode.CEILING).divide(BigDecimal.valueOf(r2),20, RoundingMode.CEILING).divide(c,20, RoundingMode.CEILING).doubleValue());
            //1的面积
            double a1 = r1_ang * Math.pow(r1, 2) - 0.5 * Math.pow(r1, 2) * Math.sin(r1_ang * 2);
            //2的面积
            double a2 = r2_ang * Math.pow(r2, 2) - 0.5 * Math.pow(r2, 2) * Math.sin(r2_ang * 2);
            out.printFormat("%.20f\n", (a1 + a2));
        }
    }
}
