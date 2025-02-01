package convertToPdf;

import apache.fop.apps.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.OutputStream;

public class XmlToPdfConverter {

    public static void main(String[] args) {
        try {
            // Chemins des fichiers
            String xslPath = "xml_to_html/Notes_GINF2.xsl";
            String xmlPath = "XML_FILES/Notes_GINF2.xml";
            String outputPath = "PDFs/output.pdf";

            // Convertir XML en PDF
            convertirEnPDF(xslPath, xmlPath, outputPath);
            System.out.println("Le fichier PDF a été généré avec succès : " + outputPath);
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void convertirEnPDF(String xslPath, String xmlPath, String outputPath)
            throws Exception {
        // Charger les fichiers XSL et XML
        File xslFile = new File(xslPath);
        StreamSource xmlSource = new StreamSource(new File(xmlPath));

        // Configurer FOP
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        // Créer le fichier PDF de sortie
        try (OutputStream out = new java.io.FileOutputStream(outputPath)) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);

            // Transformer XML en PDF
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xslFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        }
    }
}