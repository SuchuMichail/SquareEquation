import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadEquations {

    private static void readFromConsole(Equations list){
        //System.out.println("Введите коэффициенты квадратного уравнения\n");
        Scanner scanner=new Scanner(System.in);

        List<Double> equation=new ArrayList<>(3);
        equation.add(scanner.nextDouble());
        equation.add(scanner.nextDouble());
        equation.add(scanner.nextDouble());
        //в листе из уравнений будет только одно уравнение

        List<List<Double>> newEquations=new ArrayList<>(1);
        newEquations.add(equation);

        list.setAllEquations(newEquations);
    }

    private static void readFromFile(Equations list, InputStream inputStream){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            List<List<Double>> allEquations=new ArrayList<>();
            String str;
            while((str=reader.readLine()) != null){
                String[] s=str.split(" ");
                if(s.length<3){
                    throw new IllegalArgumentException("В строке недостаточно коэффициентов");
                }
                List<Double> equation=new ArrayList<>(3);
                equation.add(Double.valueOf(s[0]));
                equation.add(Double.valueOf(s[1]));
                equation.add(Double.valueOf(s[2]));

                allEquations.add(equation);
            }
            list.setAllEquations(allEquations);

        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void readWithWay(Equations list, int way) {
        if(way==1){
            readFromConsole(list);
        }else if(way==2){
            throw new IllegalArgumentException("Вы не передали имя файла, из которого читаем");
        }else{
            throw new IllegalArgumentException("Некорректный способ");
        }
    }

    public static void readWithWay(Equations list, int way, String filename) throws FileNotFoundException {
        if(way==1){
            readFromConsole(list);
        }else if(way==2){
            readFromFile(list, new FileInputStream(filename));
        }else{
            throw new IllegalArgumentException("Некорректный способ");
        }
    }
}
