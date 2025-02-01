package convertToHtml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
public class xmlToHtml {
    public static void main(String args[])
    {
        try {
            TransformerFactory tFactory=TransformerFactory.newInstance();

	        StreamSource xslDoc=new StreamSource("xml_to_html/Notes_GINF2.xsl");
	        StreamSource xmlDoc=new StreamSource("XML_FILES/Notes_GINF2.xml");
	        String outputFileName="xml_to_html\\Notes_GINF2.html";


            OutputStream htmlFile=new FileOutputStream(outputFileName);
            Transformer transform=tFactory.newTransformer(xslDoc);
            transform.transform(xmlDoc, new StreamResult(htmlFile));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (TransformerConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (TransformerFactoryConfigurationError e)
        {
            e.printStackTrace();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
    }


}