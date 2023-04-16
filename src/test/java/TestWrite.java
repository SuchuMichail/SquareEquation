import WorkWithEquations.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestWrite {
    @BeforeClass
    public static void setup() {
        //создадим файлы, которыми будем пользоваться в тестах

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

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("ex2.txt"))) {
            String str = "Without Real roots\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream("ex3.txt"))) {
            String str = "Without Real roots\n" +
                    "Without Real roots\n" +
                    "x1 = 0.5700274723201295, x2 = -3.0700274723201293\n" +
                    "Without Real roots\n" +
                    "x1 = -0.2679491924311228, x2 = -3.732050807568877\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Test
    public void testWriteToFile() {
        AllEquations listOfEquations = new AllEquations();

        IEquationsReader reader = new ReaderFromFile("input.txt");
        reader.readEquations(listOfEquations);


        ISolutionsWriter writer = new WriterInFile("output.txt");
        writer.writeSolutions(listOfEquations);

        Assert.assertTrue(compareFileWithExpected("output.txt", "expected.txt"));
        Assert.assertFalse(compareFileWithExpected("output.txt", "ex2.txt"));
        Assert.assertFalse(compareFileWithExpected("output.txt", "ex3.txt"));
    }

    public boolean compareFileWithExpected(String name1, String name2) {
        //метод сравнивает содержимое двух файлов
        String str1 = "";
        String str2 = "";
        boolean boolly = true;
        try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(name1), StandardCharsets.UTF_8));
             BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream(name2), StandardCharsets.UTF_8))) {
            while ((str1 = reader1.readLine()) != null && boolly) {
                str2 = reader2.readLine();
                if (!str1.equals(str2)) {
                    boolly = false;
                }
            }
            while ((str2 = reader2.readLine()) != null && boolly) {
                str1 = reader1.readLine();
                if (!str1.equals(str2)) {
                    boolly = false;
                }
            }

            return boolly;
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
