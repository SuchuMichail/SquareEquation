import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommunicateWithUser {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Введите способ чтения коэффициентов (1 или 2)");
        Scanner scanner=new Scanner(System.in);

        int way = scanner.nextInt();
        String filename="";

        if(way==1){
            System.out.println("Введите коэффициенты");
        }
        if(way==2){
            System.out.println("Введите имя файла");
            filename=scanner.next();
        }

        Equations list=new Equations();
        ReadEquations.readWithWay(list,way,filename);
        SolveEquations.solve(list);

        System.out.println("Выберите способ вывода результата (1 или 2)");
        way=scanner.nextInt();

        if(way==2){
            System.out.println("Введите имя файла");
            filename= scanner.next();
        }

        WriteSolutions.writeWithWay(list,way,filename);

    }

}
