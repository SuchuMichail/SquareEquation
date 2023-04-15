import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestSolve {
    @BeforeClass
    public static void setup() throws FileNotFoundException {
        //создадим файлы input.txt и output.txt, которыми будем пользоваться в тестах
        FileOutputStream insim=new FileOutputStream("input.txt");
        FileOutputStream outsim=new FileOutputStream("expected.txt");

        try (Writer writer = new OutputStreamWriter(insim)) {
            String str = "1 0 1\n2 5 -3.5\n1 1 1\n1 4 1";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(outsim)) {
            String str="Without Real roots\n"+
                    "x1 = 0.5700274723201295, x2 = -3.0700274723201293\n"+
                    "Without Real roots\n"+
                    "x1 = -0.2679491924311228, x2 = -3.732050807568877\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    @Test
    public void testSolve() throws FileNotFoundException {
        //читаем из файла коэффициенты, решаем уравнения, сравниваем результат с реальным
        Equations listOfEquations=new Equations();

        ReadEquations.readWithWay(listOfEquations,2,"input.txt");
        SolveEquations.solve(listOfEquations);

        List<String> realSolutions=new ArrayList<>();
        //дальше идут записи из real.txt
        realSolutions.add("Without Real roots");
        realSolutions.add("x1 = 0.5700274723201295, x2 = -3.0700274723201293");
        realSolutions.add("Without Real roots");
        realSolutions.add("x1 = -0.2679491924311228, x2 = -3.732050807568877");

        for(int i=0;i< realSolutions.size();i++){
            Assert.assertEquals(realSolutions.get(i),listOfEquations.getSolutions().get(i));
        }
    }
}
