import WorkWithEquations.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestRead {
    static String folderName = "tests_files/";

    @BeforeClass
    public static void setup() {
        //создадим файлы input.txt, expected.txt, incorrect1.txt, incorrect2.txt,
        //которыми будем пользоваться в тестах
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(folderName + "incorrect1.txt"))) {
            String str = "1 2 3\r\n4 5\r\n6 7 8";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(folderName + "incorrect2.txt"))) {
            String str = "1 2 3 4\r\n5 6 7\r\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(folderName + "input.txt"))) {
            String str = "1 0 1\r\n2 5 -3.5\r\n1 1 1\r\n1 4 1";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(folderName + "expected.txt"))) {
            String str = "Without Real roots\r\n" +
                    "x1 = 0.5700274723201295, x2 = -3.0700274723201293\r\n" +
                    "Without Real roots\r\n" +
                    "x1 = -0.2679491924311228, x2 = -3.732050807568877\r\n";
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
            IEquationsReader reader = new ReaderFromFile(folderName + "incorrect1.txt");
            reader.readEquations(listOfEquations);

            Assert.fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Incorrect number of coefficients");
        }

        //пытаюсь читать из файла, в котором в одной строке больше 3х чисел
        try {
            IEquationsReader reader = new ReaderFromFile(folderName + "incorrect2.txt");
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
        IEquationsReader reader = new ReaderFromFile(folderName + "input.txt");

        reader.readEquations(listOfEquations);

        AllEquations expectedAllEquations = new AllEquations();

        //дальше идут коэффициенты из input.txt
        OneEquation first = new OneEquation(1, 0, 1);
        OneEquation second = new OneEquation(2, 5, -3.5);
        OneEquation third = new OneEquation(1, 1, 1);
        OneEquation fourth = new OneEquation(1, 4, 1);

        expectedAllEquations.addOneEquation(first);
        expectedAllEquations.addOneEquation(second);
        expectedAllEquations.addOneEquation(third);
        expectedAllEquations.addOneEquation(fourth);

        Assert.assertEquals(expectedAllEquations, listOfEquations);
    }
}
