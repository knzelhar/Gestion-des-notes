package modules;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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
public class file_filiere {
    public static void main(String argv[]) throws BiffException, IOException, ParserConfigurationException,
            TransformerException {

        module_Jexcel DT = new module_Jexcel();

        Iterator<Entry<String, Vector<Module>>> it = DT.readExcel("modules").entrySet().iterator();
        // iterating every set of entry in the HashMap.

        while (it.hasNext()) {

            Map.Entry<String, Vector<Module>> set = (Map.Entry<String, Vector<Module>>) it.next();
            String filename= "modules_"+set.getValue().get(0).getClassName();

            StreamResult streamResult = new StreamResult(new File("XML_FILES/"+filename+".xml"));
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();


            Element root = document.createElement("Modules");
            document.appendChild(root);

            Element filiere = document.createElement("Filiere");
            root.appendChild(filiere);
            Attr code_filiere = document.createAttribute("code_filiere");
            code_filiere.setValue(set.getValue().get(0).getClassName());
            filiere.setAttributeNode(code_filiere);



            for (Module m : set.getValue())
            {


                Element module = document.createElement("Module");
                filiere.appendChild(module);
                Attr attr = document.createAttribute("code_module");
                attr.setValue(m.getCode());
                module.setAttributeNode(attr);
                Element firstName = document.createElement("Module_Name");
                firstName.appendChild(document.createTextNode(m.getModuleName()));
                module.appendChild(firstName);
                Element dept = document.createElement("departement");
                dept.appendChild(document.createTextNode(m.getDept()));
                module.appendChild(dept);
                Element teacher = document.createElement("Teacher");
                teacher.appendChild(document.createTextNode(m.getTeacher()));
                module.appendChild(teacher);
                Element classname = document.createElement("Class_name");
                classname.appendChild(document.createTextNode(m.getClassName()));
                module.appendChild(classname);
                if(m.getElementName()!=null && m.getElementName().size()>0) {

                    Element matieres = document.createElement("matieres");
                    module.appendChild(matieres);
                    for(String s: m.getElementName()) {

                        Element matiere =document.createElement("matiere");
                        Element nom = document.createElement("nom");
                        matiere.appendChild(nom);
                        nom.appendChild(document.createTextNode(s));
                        matieres.appendChild(matiere);
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource domSource = new DOMSource(document);
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "Modules.dtd");
                transformer.transform(domSource, streamResult);



            }
            System.out.println("Fichier XML est crée avec succès");
        }










    }}
