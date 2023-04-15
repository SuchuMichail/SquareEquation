import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TestWrite {
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
    public void testExceptions(){
        Equations listOfEquations = new Equations();

        //пытаюсь записать коэффициенты в файл, при этом не передав имя файла
        try {
            WriteSolutions.writeWithWay(listOfEquations,2);
            Assert.fail("Exception not thrown");
        }catch(IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().equals("Не передали имя файла"));
        }


        //пытаюсь передать способ записи, отличный от 1 и 2
        try{
            WriteSolutions.writeWithWay(listOfEquations,3);
            Assert.fail("Exception not thrown");
        }catch(IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().equals("Некорректный способ"));
        }
    }

    @Test
    public void testWriteToFile() throws FileNotFoundException {
        Equations listOfEquations = new Equations();
        ReadEquations.readWithWay(listOfEquations,2,"input.txt");
        SolveEquations.solve(listOfEquations);

        WriteSolutions.writeWithWay(listOfEquations,2,"output.txt");

        Assert.assertTrue(compareFileWithExpected("output.txt"));

    }

    public boolean compareFileWithExpected(String name){
        String str1 = "";
        String str2 = "";
        boolean boolly=true;
        try (BufferedReader reader1 = new BufferedReader(new InputStreamReader(new FileInputStream(name), StandardCharsets.UTF_8));
             BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream("expected.txt"), StandardCharsets.UTF_8))) {
            while((str1=reader1.readLine()) != null && boolly){
                str2 = reader2.readLine();
                if (!str1.equals(str2)) {
                    boolly = false;
                }
            }
            while((str2=reader2.readLine()) != null && boolly){
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
