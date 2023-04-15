import java.util.ArrayList;
import java.util.List;

public class SolveEquations {
    private static String findRoots(double a, double b, double c){
        String message = "";
        if(b*b-4*a*c < -1e-9){
            message = "Without Real roots";
        }
        if(Math.abs(b*b-4*a*c) < 1e-9){
            double res = -b/2./a;
            message = "one root x = " + res;
        }
        if(b*b-4*a*c > 1e-9){
            double res1 = (-b + Math.sqrt(b*b-4*a*c)) / 2./a;
            double res2 = (-b - Math.sqrt(b*b-4*a*c)) / 2./a;
            message = "x1 = " + res1 + ", " + "x2 = " + res2;
        }
        return message;
    }

    public static void solve(Equations list){
        List<String> solutions=new ArrayList<>(list.getAllEquations().size());
        for (int i = 0; i < list.getAllEquations().size(); i++) {
            double a= list.getAllEquations().get(i).get(0);
            double b= list.getAllEquations().get(i).get(1);
            double c= list.getAllEquations().get(i).get(2);
            solutions.add(findRoots(a,b,c));
        }
        list.setSolutions(solutions);
    }
}
