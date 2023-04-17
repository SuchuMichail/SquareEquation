package WorkWithEquations;

public class WriterInConsole implements ISolutionsWriter {
    public WriterInConsole() {
    }

    @Override
    public void writeSolutions(AllEquations listEquations) {
        for(OneEquation iter: listEquations.getAllEquations()){
            System.out.println(iter.getSolution().toString()+"\r");
        }
    }
}
