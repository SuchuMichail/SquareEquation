package WorkWithEquations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AllEquations {
    //список уравнений
    private List<OneEquation> allEquations;

    public AllEquations() {
        allEquations = new ArrayList<>();
    }

    public List<OneEquation> getAllEquations() {
        return allEquations;
    }

    public void addOneEquation(OneEquation equation) {
        this.allEquations.add(equation);
    }

    public void clearAllEquations() {
        this.allEquations.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllEquations that = (AllEquations) o;
        return Objects.equals(allEquations, that.allEquations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allEquations);
    }
}
