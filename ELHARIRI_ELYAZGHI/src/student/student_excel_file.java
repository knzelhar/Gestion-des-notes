package student;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

public class student_excel_file {

    public HashMap<String, Vector<Student>> readExcel(String fileName) throws BiffException, IOException {
        HashMap<String, Vector<Student>> classes = new HashMap<>();

        // Chemin du fichier Excel
        String FilePath = "EXCEL_FILES/" + fileName + ".xls";

        // Vérifier si le fichier existe
        File file = new File(FilePath);
        if (!file.exists()) {
            throw new FileNotFoundException("Le fichier Excel n'a pas été trouvé : " + FilePath);
        }

        // Ouvrir le fichier Excel
        try (FileInputStream fs = new FileInputStream(file)) {
            Workbook wb = Workbook.getWorkbook(fs);
            Sheet sh = wb.getSheet("Feuil1"); // Remplacez "Feuil1" par le nom de votre feuille

            // Lire les données
            int totalNoOfRows = sh.getRows();
            int totalNoOfCols = sh.getColumns();

            for (int row = 1; row < totalNoOfRows; row++) {
                String[] student = new String[totalNoOfCols];
                for (int col = 0; col < totalNoOfCols; col++) {
                    student[col] = sh.getCell(col, row).getContents();
                }
                if (classes.get(student[4]) == null) {
                    classes.put(student[4], new Vector<>());
                }
                classes.get(student[4]).add(new Student(student[0], student[1], student[2], student[3], student[4], student[5], student[6]));
            }

            // Fermer le fichier Excel
            wb.close();
        }

        return classes;
    }
}