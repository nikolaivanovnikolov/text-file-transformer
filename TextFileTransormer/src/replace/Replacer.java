/*
 * File    : Replacer.java
 * Project : TextFileTransormer
 * Package : replace
 * Created : Sep 21, 2019
 * Author  : Nikola Nikolov
 */
package replace;

/**
 * Interface for replacing strings in text type files like .txt, .xml, .json, etc.
 * 
 * @author <a href="mailto:n.nikolov@prolet.org">Nikola Nikolov</a>
 */
public interface Replacer
{
    /**
     * Method <code>replace(String inputFilePath, String oldString, String newString)</code>.</br>
     * 
     * This method should search and replace all occurrences of oldString with newString.
     * 
     * @param inputFilePath
     * @param oldString
     * @param newString
     */
    public void replace(String inputFilePath, String oldString, String newString);
}
