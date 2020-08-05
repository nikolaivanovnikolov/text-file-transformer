/*
 * File    : XmlReplacer.java
 * Project : TextFileTransormer
 * Package : replace
 * Created : Sep 21, 2019
 * Author  : Nikola Nikolov
 */
package replace;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


/**
 * Implementation of <code>Replacer</code> for replacing strings in XML files.
 * 
 * @author <a href="mailto:n.nikolov@prolet.org">Nikola Nikolov</a>
 */
public class XmlReplacer implements Replacer
{

//    static String inputFile = "./beans.xml";
//    static String outputFile = "./beans_new.xml";

    @Override
    public void replace(String inputFilePath, String oldString, String newString)
    {
        // 1- Build the doc from the XML file
        Document doc = null;
        try
        {
            doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(inputFilePath));
        } catch (SAXException e)
        {
            System.out.println(e.getMessage());
            return;
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
            return;
        } catch (ParserConfigurationException e)
        {
            System.out.println(e.getMessage());
            return;
        }
    
        if (doc == null)
        {
            return;
        }
    
        // 2- Get the node(s)
        NodeList nodes = null;
        nodes = doc.getChildNodes();
        
        // 3- Make the change on the document nodes
        if (nodes != null && nodes.getLength() > 0)
        {
            for (int idx = 0; idx < nodes.getLength(); idx++)
            {
                nodeReplace(nodes.item(idx),oldString, newString);
                System.out.println("\n");
            }
        }

        // 4- Save the result to a new XML doc
        Transformer xformer = null;
        try
        {
            xformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e)
        {
            // TODO: Process exception here.
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e)
        {
            // TODO: Process exception here.
            e.printStackTrace();
        }
    
        if (xformer != null && doc != null)
        {
            try
            {
                String outputFile = inputFilePath.replace(".xml", "_new.xml");
                xformer.transform(new DOMSource(doc),
                    new StreamResult(new File(outputFile)));
            } catch (TransformerException e)
            {
                // TODO: Process exception here.
                e.printStackTrace();
            }
        }
    }

    /**
     * Method nodeReplace(Node node, String oldString, String newString).</br>
     * 
     * Recursively traverses all node's children and replaces all occurrences of</br>
     * oldString with newString in the attribute's values and node's text content.
     * 
     * @param node
     * @param oldString
     * @param newString
     */
    private void nodeReplace(Node node, String oldString, String newString)
    {
        System.out.println("node: " + node);
    
        if(node.hasChildNodes())
        {
            NodeList childeNodes = node.getChildNodes();
            if(childeNodes != null)
            {
                for (int i = 0; i < childeNodes.getLength(); i++)
                {
                    nodeReplace(childeNodes.item(i), oldString, newString);
                }
            }
        } else 
        {
            String nodeText = node.getTextContent();
            if(nodeText != null)
            {
                node.setTextContent(nodeText.replaceAll(oldString, newString));
            }
        }
    
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null && attributes.getLength() > 0)
        {
            for (int i = 0; i < attributes.getLength(); i++)
            {
                Node attr = attributes.item(i);
                System.out.println("attr: " + attr);
                if(attr != null)
                {
                    String attrValue = attr.getNodeValue();
                    System.out.println("attrValue: " + attrValue);
                    if(attrValue != null)
                    {
                        attr.setNodeValue(attrValue.replaceAll(oldString, newString));
                    }
                }
            }
        }
    
        System.out.println("\n");
    }

    /**
     * Method replaceXPath(String inputFilePath, String oldString, String newString).</br>
     * 
     * This method was intended to use XPath approach of replacing node's text content and attribute values.
     * 
     */
    private void replaceXPath(String inputFilePath, String oldString, String newString)
    {
        // 1- Build the doc from the XML file
        Document doc = null;
        try
        {
            doc = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new InputSource(inputFilePath));
        } catch (SAXException e)
        {
            // TODO: Process exception here.
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO: Process exception here.
            e.printStackTrace();
        } catch (ParserConfigurationException e)
        {
            // TODO: Process exception here.
            e.printStackTrace();
        }

        // 2- Locate the node(s) with xpath
        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList nodes = null;
        if (doc != null)
        {
            try
            {
                nodes = (NodeList) xpath.evaluate(
                     "//*[contains(//*, " + oldString + ")]", doc, XPathConstants.NODESET);
//                    "//*[contains(@value, 'oldString')]", doc, XPathConstants.NODESET);
            } catch (XPathExpressionException e)
            {
                // TODO: Process exception here.
                e.printStackTrace();
            }
        }

        // 3- Make the change on the selected nodes
        if (nodes != null)
        {
            for (int idx = 0; idx < nodes.getLength(); idx++)
            {
                Node value = nodes.item(idx)
                        .getAttributes()
                        .getNamedItem("value");
                String val = value.getNodeValue();
                value.setNodeValue(val.replaceAll(oldString, newString));
            }
        }

        // 4- Save the result to a new XML doc
        Transformer xformer = null;
        try
        {
            xformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e)
        {
            // TODO: Process exception here.
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e)
        {
            // TODO: Process exception here.
            e.printStackTrace();
        }

        if (xformer != null && doc != null)
        {
            try
            {
                String outputFile = inputFilePath.replace(".xml", "_new.xml");
                xformer.transform(new DOMSource(doc),
                    new StreamResult(new File(outputFile)));
            } catch (TransformerException e)
            {
                // TODO: Process exception here.
                e.printStackTrace();
            }
        }
    }
}
