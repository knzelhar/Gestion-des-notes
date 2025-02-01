package modules;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
public class Module {
    private String moduleName;
    private Vector<String> elementName;
    private String dept ;
    private String teacher;
    private String className;
    private String code_module;
    public Module(String moduleName,Vector<String> elementName, String dept, String className, String teacher, String code_module) {
        this.className = className;
        this.dept = dept;
        this.moduleName = moduleName;
        this.teacher = teacher;
        this.elementName = elementName;
        this.code_module=code_module;
    }
    public String getModuleName() {
        return moduleName;
    }
    public Vector<String> getElementName() {
        return elementName;
    }
    public String getDept() {
        return dept;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getClassName() {
        return className;
    }
    public String getCode()
    {
        return code_module;
    }
    public String getData() {
        String message = moduleName+"\n"+dept+"\n"+teacher+"\n"+className+"\n"+ code_module+"\n";
        for(int i=0;i<elementName.size();i++)
        {
            message += elementName.get(i)+" ";
        }
        return message+"\n";
    }


    public static class module_Jexcel {

        public HashMap<String , Vector<Module>> readExcel(String fileName) throws BiffException, IOException {

            HashMap<String , Vector<Module>> modules = new HashMap<String ,Vector<Module>>();

            String FilePath = "C:\\Users\\perso\\XML\\Fichier_Excel\\"+fileName+".xls";

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
}
