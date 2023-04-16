package WorkWithEquations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderFromConsole implements IEquationsReader {

    public ReaderFromConsole() {
    }

    @Override
    public void readEquations(AllEquations listEquations) {
        Scanner scanner = new Scanner(System.in);

        //будет читаться одно уравнение
        OneEquation equation = new OneEquation(scanner.nextDouble(),
                scanner.nextDouble(), scanner.nextDouble());

        List<OneEquation> support = new ArrayList<>(1);
        support.add(equation);

        listEquations.setAllEquations(support);
    }
}
