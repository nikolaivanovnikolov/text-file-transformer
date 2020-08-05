Task:
-----
Write a program, that can replace patterns in text-based files.
It has 3 input arguments: 
filename, pattern(regex), replacement.
(For replacing you can just use String.replaceAll() for example).
This first version should support plain text files and XML files, maybe more in the future.
The important thing is that in XML files only the content should be replaced not the XML element or attribute names. 


Execution of Tranformer/Replacer program:
----------------------------------------
Executing the program successfully on file "beans.xml" creates new file: "beans_new.xml".
The content of the new is the same as in the original file, except the replaced strings.

Examples of running the program from console:

!Here - text to find and replace with: ?There?

> java -jar TextFileTransformer.jar beans.xml !Here ?There?
File Extension: .xml
node: [property: null]
attr: name="stringProperty"
attrValue: stringProperty
attr: value="!Here:String:Bar"
attrValue: !Here:String:Bar


> java -jar TextFileTransformer.jar beans.txt !Here ?Some_Some?
File Extension: .txt


>java -jar TextFileTransformer.jar beans.json !Here ?Where?
File Extension: .json
File extension not supported: .json
Could not create replacer!
