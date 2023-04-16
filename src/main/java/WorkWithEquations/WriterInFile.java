package WorkWithEquations;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class WriterInFile implements ISolutionsWriter {
    private String filename;

    public WriterInFile(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeSolutions(AllEquations listEquations) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8)) {
            for (int i = 0; i < listEquations.getAllEquations().size(); i++) {
                writer.write(listEquations.getAllEquations().get(i).getSolution().toString() + "\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
