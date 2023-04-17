package WorkWithEquations;

import java.util.Objects;

public class Solution {
    private Double x1;
    private Double x2;

    public Solution(Double x1, Double x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public Double getX1() {
        return x1;
    }

    public Double getX2() {
        return x2;
    }

    @Override
    public String toString() {
        String str = "";
        if (x1 == null && x2 == null) {
            str = "Without Real roots";
        } else if (x1 != null && x2 == null) {
            str = "one root x = " + x1;
        } else if (x1 != null) {
            str = "x1 = " + x1 + ", x2 = " + x2;
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return Objects.equals(x1, solution.x1) && Objects.equals(x2, solution.x2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x1, x2);
    }
}
