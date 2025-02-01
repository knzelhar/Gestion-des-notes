package Notes;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class note_Jexcel {
    public HashMap<String , HashMap<String ,Note>> readExcel(String fileName) throws BiffException, IOException {
        HashMap<String , HashMap<String ,Note>> Notes = new HashMap<String , HashMap<String ,Note>>();
        String FilePath = "EXCEL_FILES/"+fileName+".xls";
        FileInputStream fs = new FileInputStream(FilePath);
        Workbook wb = Workbook.getWorkbook(fs);
        Sheet sh = wb.getSheet("Feuil1");
        int totalNoOfRows = sh.getRows();
        int totalNoOfCols = sh.getColumns();
        System.out.println(totalNoOfRows);
        for (int row =1; row < totalNoOfRows; row++) {
            String[] note = new String[totalNoOfCols];
            for (int col = 0; col < totalNoOfCols; col++) {note[col] = sh.getCell(col, row).getContents();}
            if(note[3].equals(""))continue;
            if(Notes.get(note[3]) == null) {
                Notes.put(note[3],new HashMap<String ,Note>());
                Notes.get(note[3]).put(note[0], new Note(note[0],note[3],note[4]));
            }
            System.out.println(note[3]);
            if(Notes.get(note[3]).get(note[0])==null) {Notes.get(note[3]).put(note[0], new Note(note[0],note[3],note[4]));}
            Notes.get(note[3]).get(note[0]).add_notes(note[1], note[2]);
        }
        HashMap<String , HashMap<String ,Note>> sorted = new HashMap<String , HashMap<String ,Note>>();
        sorted.putAll(Notes);
        return sorted;
    }

}
