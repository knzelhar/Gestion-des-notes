package modules;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class module_Jexcel {

    public HashMap<String , Vector<Module>> readExcel(String fileName) throws BiffException, IOException {

        HashMap<String , Vector<Module>> modules = new HashMap<String ,Vector<Module>>();

        String FilePath = "EXCEL_FILES/"+fileName+".xls";

        FileInputStream fs = new FileInputStream(FilePath);
        Workbook wb = Workbook.getWorkbook(fs);

        Sheet sh = wb.getSheet("Feuil1");
        int totalNoOfRows = sh.getRows();
        int totalNoOfCols = sh.getColumns();


        for (int row =1; row < totalNoOfRows; row++) {
            String[] module = new String[totalNoOfCols-1];
            for (int col = 0; col < totalNoOfCols-1; col++) {
                module[col] = sh.getCell(col, row).getContents();
            }
            if(module[5].equals(""))continue;
            if(modules.get(module[5]) == null) {

                modules.put(module[5],new Vector<Module>());
            }
            Vector<String> Elements = new Vector<String>();

            for(int i=1;i<4;i++)
            {
                if(!module[i].equals("")) {
                    Elements.add(module[i]);
                }
            }


            modules.get(module[5]).add(new Module(module[0],Elements,module[4],module[5],module[6],module[7]));
        }
        HashMap<String, Vector<Module>> sorted = new HashMap<String, Vector<Module>>();
        sorted.putAll(modules);
        return sorted;
    }

}
