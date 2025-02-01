package student;

import jxl.read.biff.BiffException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

public class generate_student_xml {
    public static void main(String argv[]) throws BiffException, IOException, ParserConfigurationException, TransformerException {

        student_excel_file DT = new student_excel_file();
        // Lire le fichier Excel "students.xls"
        Iterator<Entry<String, Vector<Student>>> it = DT.readExcel("students").entrySet().iterator();

        // Chemin de sortie pour le fichier XML
        StreamResult streamResult = new StreamResult(new File("XML_FILES/students.xml"));

        // Créer le document XML
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        // Créer l'élément racine
        Element root = document.createElement("Students");
        document.appendChild(root);

        // Parcourir les données et créer les éléments XML
        while (it.hasNext()) {
            Map.Entry<String, Vector<Student>> set = it.next();
            Element filiere = document.createElement("Filiere");
            root.appendChild(filiere);

            // Ajouter l'attribut "code_filiere"
            Attr code_filiere = document.createAttribute("code_filiere");
            code_filiere.setValue(set.getValue().get(0).getclassName());
            filiere.setAttributeNode(code_filiere);

            // Ajouter les étudiants
            for (Student s : set.getValue()) {
                Element student = document.createElement("student");
                filiere.appendChild(student);

                // Ajouter les attributs de l'étudiant
                Attr cne = document.createAttribute("CNE");
                cne.setValue(s.getCNE());
                student.setAttributeNode(cne);

                Element firstName = document.createElement("firstname");
                firstName.appendChild(document.createTextNode(s.getfirstName()));
                student.appendChild(firstName);

                Element lastName = document.createElement("lastname");
                lastName.appendChild(document.createTextNode(s.getlastName()));
                student.appendChild(lastName);

                Element dateOfBirth = document.createElement("Date_of_Birth");
                dateOfBirth.appendChild(document.createTextNode(s.getDate()));
                student.appendChild(dateOfBirth);

                Element className = document.createElement("Class_name");
                className.appendChild(document.createTextNode(s.getclassName()));
                student.appendChild(className);

                Element phone = document.createElement("phone");
                phone.appendChild(document.createTextNode(s.getPhone()));
                student.appendChild(phone);

                Element email = document.createElement("Email");
                email.appendChild(document.createTextNode(s.getEmail()));
                student.appendChild(email);
            }
        }

        // Écrire le fichier XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        transformer.transform(domSource, streamResult);

        System.out.println("Fichier XML créé avec succès : XML_FILES/students.xml");
    }
}