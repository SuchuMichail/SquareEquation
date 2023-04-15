import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equations {

    //список уравнений. в нём листы из 3х элементов - коэффициентов уравнения
    public List<List<Double>> allEquations;
    public List<String> solutions;//лист из решений уравнений

    public Equations(){
        allEquations=new ArrayList<>();
        solutions=new ArrayList<>();
    }

    public List<List<Double>> getAllEquations() {
        return allEquations;
    }

    public List<String> getSolutions(){
        return solutions;
    }

    public void setSolutions(List<String> solutions){
        this.solutions=new ArrayList<>(solutions.size());
        this.solutions.addAll(solutions);
    }
/*
    public void addEquation(double a, double b, double c){
        List<Double> equation = new ArrayList<>(3);
        equation.add(a);
        equation.add(b);
        equation.add(c);
        allEquations.add(equation);
    }
*/
    public void setAllEquations(List<List<Double>> allEquations){
        this.allEquations=new ArrayList<>();
        this.allEquations.addAll(allEquations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equations equations = (Equations) o;
        return Objects.equals(allEquations, equations.allEquations) && Objects.equals(solutions, equations.solutions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(allEquations, solutions);
    }
}
