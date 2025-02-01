package convertToPdf;

import apache.fop.apps.*;

import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class PdfCreation {

    public static void main(String[] args) {
        try {
            // Chemins des fichiers
            String xslPath = "C:\\Users\\perso\\XML\\test\\GINF2releve.xsl";
            String xmlPath = "C:\\Users\\perso\\XML\\test\\GINF2releve.xml";
            String outputPath = "C:\\Users\\perso\\XML\\test\\GINF2releve.pdf";

            // Convertir XML en PDF
            convertirEnPDF(xslPath, xmlPath, outputPath);
            System.out.println("Le fichier PDF a été généré avec succès : " + outputPath);
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void convertirEnPDF(String xslPath, String xmlPath, String outputPath)
            throws IOException, TransformerException, FOPException {
        // Charger les fichiers XSL et XML
        File xslFile = new File(xslPath);
        StreamSource xmlSource = new StreamSource(new File(xmlPath));

        // Configurer FOP
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();

        // Créer le fichier PDF de sortie
        try (OutputStream outputStream = new java.io.FileOutputStream(outputPath)) {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outputStream);

            // Transformer XML en PDF
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        }
    }
}