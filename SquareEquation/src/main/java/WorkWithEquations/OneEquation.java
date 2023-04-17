package WorkWithEquations;

import java.util.Objects;

public class OneEquation {
    private double a;
    private double b;
    private double c;

    private Solution solution;

    public OneEquation(double a, double b, double c) {
        setA(a);
        setB(b);
        setC(c);
        //сразу же вычисляем решение уравнения
        calculateSolution(a, b, c);
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public Solution getSolution() {
        return solution;
    }

    private void setA(double a) {
        this.a = a;
    }

    private void setB(double b) {
        this.b = b;
    }

    private void setC(double c) {
        this.c = c;
    }

    private void calculateSolution(double a, double b, double c) {
        this.solution = SolverEquations.findRoots(a, b, c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneEquation equation = (OneEquation) o;
        return Double.compare(equation.a, a) == 0 && Double.compare(equation.b, b) == 0 && Double.compare(equation.c, c) == 0 && Objects.equals(solution, equation.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, solution);
    }
}
