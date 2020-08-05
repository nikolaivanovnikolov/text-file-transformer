/*
 * File    : TextReplacer.java
 * Project : TextFileTransormer
 * Package : replace
 * Created : Sep 21, 2019
 * Author  : Nikola Nikolov
 */
package replace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Implementation of <code>Replacer</code> for replacing strings in text files.
 * 
 * @author <a href="mailto:n.nikolov@prolet.org">Nikola Nikolov</a>
 */
public class TextReplacer implements Replacer
{

    @Override
    public void replace(String inputFilePath, String oldString, String newString)
    {
        try(Stream<String> str = Files.lines(Paths.get(inputFilePath)))
        {
            String outputPath = inputFilePath.replace(".txt", "_new.txt");
            File outputFile = new File(outputPath);
            try(FileWriter fw = new FileWriter(outputFile))
            {
                str.map(line -> line.replaceAll(oldString, newString)).forEach(line -> {
                    try
                    {
                        fw.write(line + "\n");
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                });
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
