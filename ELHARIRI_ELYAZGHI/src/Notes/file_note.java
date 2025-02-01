package Notes;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import jxl.read.biff.BiffException;
public class file_note {

    public static void main(String argv[]) throws BiffException, IOException, ParserConfigurationException,
            TransformerException {
        note_Jexcel DT = new note_Jexcel();
        Iterator<Entry<String, HashMap<String, Note>>> it = DT.readExcel("Notes").entrySet().iterator();
        // iterating every set of entry in the HashMap.
        while (it.hasNext()) {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("Students");
            document.appendChild(root);
            Map.Entry<String, HashMap<String, Note>> set = (Map.Entry<String, HashMap<String, Note>>) it.next();
            String filename= "Notes_"+set.getKey();
            StreamResult streamResult = new StreamResult(new File("XML_FILES/"+filename+".xml"));
            Iterator<Entry<String, Note>> itt = set.getValue().entrySet().iterator();
            int i=0;
            while(itt.hasNext())
            {
                Map.Entry<String,  Note> sett= (Map.Entry<String,Note>) itt.next();
                Element student = document.createElement("Student");
                root.appendChild(student);
                Attr cne= document.createAttribute("CNE");
                cne.setValue(sett.getValue().getStudent_Code());
                student.setAttributeNode(cne);
                Attr name= document.createAttribute("Student_Name");
                name.setValue(sett.getValue().getStudentName());
                student.setAttributeNode(name);
                System.out.println(i++);
                for (paire s : sett.getValue().Notes)
                {
                    Element module = document.createElement("module");
                    Attr code_module = document.createAttribute("code");
                    code_module.setValue(s.Module_name);
                    module.setAttributeNode(code_module);
                    Element note =document.createElement("note");
                    note.appendChild(document.createTextNode(s.getNote()));
                    module.appendChild(note);
                    student.appendChild(module);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Note.dtd");
            transformer.transform(domSource, streamResult);
            System.out.println("Fichier XML est crée avec succès");        }

    }
}
