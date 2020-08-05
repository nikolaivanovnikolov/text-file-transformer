/*
 * File    : TestReplacer.java
 * Project : TextFileTransormer
 * Package : test
 * Created : Sep 22, 2019
 * Author  : Nikola Nikolov
 */
package test;

import java.io.File;

import iputoutput.ReplacerFactory;
import replace.Replacer;

/**
 * Main class for replacing strings in different type of text based files.
 * 
 * @author <a href="mailto:n.nikolov@prolet.org">Nikola Nikolov</a>
 */
public class TestReplacer
{

    /**
     * TODO: comment method main.
     * 
     * @param args
     */
    public static void main(String[ ] args)
    {
        validateArgs(args);

        //D:\dev\workspace\TextFileTransormer\beans.xml
        //D:\dev\workspace\TextFileTransormer\beans.txt
        String inputFilePath = args[0];
        String oldString = args[1];
        String newString = args[2];

        final Replacer replacer = ReplacerFactory.createReplacer(inputFilePath);
        if(replacer == null)
        {
            System.out.println("Could not create replacer!");
            System.exit(1);
        }
        
        replacer.replace(inputFilePath, oldString, newString);
    }

    private static void validateArgs(String args[])
    {
        if(args== null || args.length < 3)
        {
            System.out.println("Please specify arguments: input_file_path, old_string, new_string");
            System.exit(2);;
        }

        for (int i = 0; i < args.length; i++)
        {
            if(args[i] == null)
            {
                System.out.println("Null arguemnt[" + i + "]");
                System.exit(3);
            }
        }

        String filePath = args[0];
        if(filePath == null || filePath.isEmpty())
        {
            System.out.println("Empty or null file path!");
            System.exit(4);
        }

        File file = new File(filePath);
        if (!file.exists())
        {
            System.out.println("File does not exist!");
            System.exit(5);
        }
        
        if (file.isDirectory())
        {
            System.out.println("File path is a dyrectory!");
            System.exit(6);
        }
    }
}
