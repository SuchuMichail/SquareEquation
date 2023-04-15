import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TestRead {
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

        //пытаюсь считать коэффициенты из файла, при этом не передав имя файла
        try {
            ReadEquations.readWithWay(listOfEquations,2);
            Assert.fail("Exception not thrown");
        }catch(IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().equals("Вы не передали имя файла, из которого читаем"));
        }


        //пытаюсь передать способ чтения, отличный от 1 и 2
        try{
            ReadEquations.readWithWay(listOfEquations,3);
            Assert.fail("Exception not thrown");
        }catch(IllegalArgumentException e){
            Assert.assertTrue(e.getMessage().equals("Некорректный способ"));
        }


        //пытаюсь читать из файла, в котором в одной строке меньше 3х чисел
        try{
            ReadEquations.readWithWay(listOfEquations,2,"incorrect.txt");
            Assert.fail("Exception not thrown");
        } catch (IllegalArgumentException e) {
            Assert.assertTrue(e.getMessage().equals("В строке недостаточно коэффициентов"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testReadFromFile() throws FileNotFoundException {
        //беру содержимое файла input.txt и сравниваю с коэффициентами уравнений,
        // полученных после чтения этого файла
        Equations listOfEquations = new Equations();
        ReadEquations.readWithWay(listOfEquations,2,"input.txt");

        List<List<Double>> real=new ArrayList<>();

        List<Double> firstEquation=new ArrayList<>();
        List<Double> secondEquation=new ArrayList<>();
        List<Double> thirdEquation=new ArrayList<>();
        List<Double> fourthEquation=new ArrayList<>();

        //дальше идут коэффициенты из input.txt
        firstEquation.add(1.);firstEquation.add(0.);firstEquation.add(1.);
        secondEquation.add(2.);secondEquation.add(5.);secondEquation.add(-3.5);
        thirdEquation.add(1.);thirdEquation.add(1.);thirdEquation.add(1.);
        fourthEquation.add(1.);fourthEquation.add(4.);fourthEquation.add(1.);

        real.add(firstEquation);real.add(secondEquation);
        real.add(thirdEquation);real.add(fourthEquation);

        Assert.assertEquals(real,listOfEquations.getAllEquations());
    }
}
