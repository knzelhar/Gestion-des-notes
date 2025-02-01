package student;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
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

public class Xmlfile_filiere {

    public static void main(String[] args) throws BiffException, IOException, ParserConfigurationException, TransformerException {
        student_excel_file DT = new student_excel_file();
        Iterator<Entry<String, Vector<Student>>> it = DT.readExcel("students").entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Vector<Student>> set = it.next();
            String filename = "Students_" + set.getValue().get(0).getclassName();

            // Chemin de sortie pour les fichiers XML dans C:\xml8files\
            StreamResult streamResult = new StreamResult(new File("XML_FILES//" + filename + ".xml"));

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element root = document.createElement("Students");
            document.appendChild(root);

            Element filiere = document.createElement("Filiere");
            root.appendChild(filiere);
            Attr code_filiere = document.createAttribute("code_filiere");
            code_filiere.setValue(set.getValue().get(0).getclassName());
            filiere.setAttributeNode(code_filiere);

            for (Student s : set.getValue()) {
                Element student = document.createElement("student");
                filiere.appendChild(student);

                Attr attr = document.createAttribute("CNE");
                attr.setValue(s.getCNE());
                student.setAttributeNode(attr);

                Element firstName = document.createElement("firstname");
                firstName.appendChild(document.createTextNode(s.getfirstName()));
                student.appendChild(firstName);

                Element lastname = document.createElement("lastname");
                lastname.appendChild(document.createTextNode(s.getlastName()));
                student.appendChild(lastname);

                Element datebirth = document.createElement("Date_of_Birth");
                datebirth.appendChild(document.createTextNode(s.getDate()));
                student.appendChild(datebirth);

                Element classname = document.createElement("Class_name");
                classname.appendChild(document.createTextNode(s.getclassName()));
                student.appendChild(classname);

                Element phone = document.createElement("phone");
                phone.appendChild(document.createTextNode(s.getPhone()));
                student.appendChild(phone);

                Element email = document.createElement("Email");
                email.appendChild(document.createTextNode(s.getEmail()));
                student.appendChild(email);
            }

            // Écrire le fichier XML une seule fois après avoir ajouté tous les étudiants
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Students.dtd");
            DOMSource domSource = new DOMSource(document);
            transformer.transform(domSource, streamResult);

            System.out.println("Fichier XML créé avec succès : C://Users//ASUS//Downloads//ELHARIRI_ELYAZGHI//XML_FILES//" + filename + ".xml");
        }
    }
}