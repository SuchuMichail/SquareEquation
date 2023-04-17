package WorkWithEquations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ReaderFromFile implements IEquationsReader {
    private String filename;

    public ReaderFromFile(String filename) {
        this.filename = filename;
    }

    @Override
    public void readEquations(AllEquations listEquations) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String str;
            while ((str = reader.readLine()) != null) {
                String[] s = str.split(" ");
                if (s.length != 3) {
                    throw new IllegalArgumentException("Incorrect number of coefficients");
                }
                OneEquation equation = new OneEquation(
                        Double.parseDouble(s[0]),
                        Double.parseDouble(s[1]),
                        Double.parseDouble(s[2])
                );

                listEquations.addOneEquation(equation);
            }

        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
