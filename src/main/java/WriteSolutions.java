import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteSolutions {
    private static void writeInConsole(Equations list){
        SolveEquations.solve(list);
        System.out.println(list.getSolutions().get(0));
    }
    private static void writeInFile(Equations list, String filename){
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)) {
            SolveEquations.solve(list);
            for(int i=0;i<list.getSolutions().size();i++) {
                writer.write(list.getSolutions().get(i) + "\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public static void writeWithWay(Equations list, int way){
        if(way==1){
            writeInConsole(list);
        }else if(way==2){
            throw new IllegalArgumentException("Не передали имя файла");
        }else{
            throw new IllegalArgumentException("Некорректный способ");
        }
    }
    public static void writeWithWay(Equations list, int way, String filename){
        if(way==1){
            writeInConsole(list);
        }else if(way==2){
            writeInFile(list, filename);
        }else{
            throw new IllegalArgumentException("Некорректный способ");
        }
    }
}
