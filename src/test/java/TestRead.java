import WorkWithEquations.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestRead {
    @BeforeClass
    public static void setup() {
        //создадим файлы input.txt, expected.txt, incorrect1.txt, incorrect2.txt,
        //которыми будем пользоваться в тестах

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("incorrect1.txt"))) {
            String str = "1 2 3\n4 5\n6 7 8";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("incorrect2.txt"))) {
            String str = "1 2 3 4\n5 6 7\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("input.txt"))) {
            String str = "1 0 1\n2 5 -3.5\n1 1 1\n1 4 1";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("expected.txt"))) {
            String str = "Without Real roots\n" +
                    "x1 = 0.5700274723201295, x2 = -3.0700274723201293\n" +
                    "Without Real roots\n" +
                    "x1 = -0.2679491924311228, x2 = -3.732050807568877\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Test
    public void testExceptions() {
        AllEquations listOfEquations = new AllEquations();

        //пытаюсь читать из файла, в котором в одной строке меньше 3х чисел
        try {
            IEquationsReader reader = new ReaderFromFile("incorrect1.txt");
            reader.readEquations(listOfEquations);

            Assert.fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Incorrect number of coefficients");
        }

        //пытаюсь читать из файла, в котором в одной строке больше 3х чисел
        try {
            IEquationsReader reader = new ReaderFromFile("incorrect2.txt");
            reader.readEquations(listOfEquations);

            Assert.fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Incorrect number of coefficients");
        }

    }

    @Test
    public void testReadFromFile() {
        //беру содержимое файла input.txt, создаю по коэффициентам уравнения,
        //эти уравнения добавляю в объект класса AllEquation
        // и сравниваю этот объект (ожидаемый) с объектом,
        // полученным после использования ReaderFromFile
        AllEquations listOfEquations = new AllEquations();
        IEquationsReader reader = new ReaderFromFile("input.txt");

        reader.readEquations(listOfEquations);

        AllEquations expectedAllEquations = new AllEquations();
        List<OneEquation> expected = new ArrayList<>();

        //дальше идут коэффициенты из input.txt
        OneEquation first = new OneEquation(1, 0, 1);
        OneEquation second = new OneEquation(2, 5, -3.5);
        OneEquation third = new OneEquation(1, 1, 1);
        OneEquation fourth = new OneEquation(1, 4, 1);

        expected.add(first);
        expected.add(second);
        expected.add(third);
        expected.add(fourth);

        expectedAllEquations.setAllEquations(expected);

        Assert.assertEquals(expectedAllEquations, listOfEquations);
    }
}
