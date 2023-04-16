package WorkWithEquations;

public class WriterInConsole implements ISolutionsWriter {
    public WriterInConsole() {
    }

    @Override
    public void writeSolutions(AllEquations listEquations) {
        for (int i = 0; i < listEquations.getAllEquations().size(); i++) {
            System.out.println(listEquations.getAllEquations().get(i).getSolution().toString());
        }
    }
}
