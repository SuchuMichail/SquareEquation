package ConsoleCommunication;

import WorkWithEquations.*;

import java.util.Scanner;

public class CommunicationWithUser {
    public static void main(String[] args) {
        String way = "";
        Scanner scanner = new Scanner(System.in);
        while (!way.equals("1") && !way.equals("2")) {
            System.out.println("Введите способ чтения коэффициентов\n1 - если из консоли\n2 - если из файла");
            way = scanner.next();
        }

        String filename = "";
        if (way.equals("1")) {
            System.out.println("Введите коэффициенты");
        }
        if (way.equals("2")) {
            System.out.println("Введите имя файла");
            filename = scanner.next();
        }

        AllEquations list = new AllEquations();

        IEquationsReader reader = null;
        if (way.equals("1")) {
            reader = new ReaderFromConsole();
        }
        if (way.equals("2")) {
            reader = new ReaderFromFile(filename);
        }
        reader.readEquations(list);

        way = "";
        while (!way.equals("1") && !way.equals("2")) {
            System.out.println("Введите способ вывода решений\n1 - если в консоль\n2 - если в файл");
            way = scanner.next();
        }

        ISolutionsWriter writer;
        if (way.equals("1")) {
            writer = new WriterInConsole();
            writer.writeSolutions(list);
        }
        if (way.equals("2")) {
            System.out.println("Введите имя файла");
            filename = scanner.next();

            writer = new WriterInFile(filename);
            writer.writeSolutions(list);
        }

    }

}
