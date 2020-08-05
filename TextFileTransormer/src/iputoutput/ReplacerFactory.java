/*
 * File    : ReplacerFactory.java
 * Project : TextFileTransormer
 * Package : iputoutput
 * Created : Sep 23, 2019
 * Author  : Nikola Nikolov
 */
package iputoutput;

import replace.*;

/**
 * Enum class that instantiates <code>Replacer</code> classes to deal with different file types.
 * 
 * @author <a href="mailto:n.nikolov@prolet.org">Nikola Nikolov</a>
 */
public enum ReplacerFactory
{
    TXT(new TextReplacer()),
    XML(new XmlReplacer());
//    JSON(new JsonReplacer());

    private Replacer replacer;
    
    private ReplacerFactory(Replacer replacer)
    {
        this.replacer = replacer;
    }
    
    public static Replacer createReplacer(String filePath)
    {
        String fileExtension = filePath.substring(filePath.lastIndexOf('.'), filePath.length());
        System.out.println("File Extension: " + fileExtension);

        if(fileExtension == null || fileExtension.isEmpty())
        {
            System.out.println("Could not find file extension!");
            return null;
        }

        switch (fileExtension)
        {
            case ".txt":
                return TXT.replacer;

            case ".xml":
                return XML.replacer;

            default: {
                System.out.println("File extension not supported: " + fileExtension);
                return null;                
            }
        }
    }
}
