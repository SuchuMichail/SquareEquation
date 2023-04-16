package WorkWithEquations;

public class SolverEquations {
    public static Solution findRoots(double a, double b, double c) {
        Solution solution;

        double d = b * b - 4 * a * c;
        if (d < -1e-9) {
            //     Without Real roots
            solution = new Solution(null, null);
        } else if (Math.abs(d) < 1e-9) {
            double res = -b / 2. / a;
            // one root
            solution = new Solution(res, null);
        } else {
            double res1 = (-b + Math.sqrt(d)) / 2. / a;
            double res2 = (-b - Math.sqrt(d)) / 2. / a;
            solution = new Solution(res1, res2);
        }
        return solution;
    }

}
