import WorkWithEquations.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestWrite {
    static String folderName = "tests_files/";

    @BeforeClass
    public static void setup() {
        //создадим файлы, которыми будем пользоваться в тестах
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

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(folderName + "ex2.txt"))) {
            String str = "Without Real roots\r\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }

        try (Writer writer = new OutputStreamWriter(new FileOutputStream(folderName + "ex3.txt"))) {
            String str = "Without Real roots\r\n" +
                    "Without Real roots\r\n" +
                    "x1 = 0.5700274723201295, x2 = -3.0700274723201293\r\n" +
                    "Without Real roots\r\n" +
                    "x1 = -0.2679491924311228, x2 = -3.732050807568877\r\n";
            writer.write(str);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    @Test
    public void testWriteToFile() {
        AllEquations listOfEquations = new AllEquations();

        IEquationsReader reader = new ReaderFromFile(folderName + "input.txt");
        reader.readEquations(listOfEquations);


        ISolutionsWriter writer = new WriterInFile(folderName + "output.txt");
        writer.writeSolutions(listOfEquations);

        Assert.assertTrue(compareFileWithExpected(folderName + "output.txt", folderName + "expected.txt"));
        Assert.assertFalse(compareFileWithExpected(folderName + "output.txt", folderName + "ex2.txt"));
        Assert.assertFalse(compareFileWithExpected(folderName + "output.txt", folderName + "ex3.txt"));
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
