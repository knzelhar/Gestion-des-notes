package Notes;
import java.util.Vector;
public class Note {
    private String Student_Code;
    private String ClassName;
    private String Student_Name;
    public Vector<paire> Notes ;
    public Note(String Student_Code,String ClassName,String Student_Name) {
        this.Student_Code = Student_Code;
        this.ClassName = ClassName;
        this.Student_Name=Student_Name;
        this.Notes=new Vector<paire>();
    }
    public void add_notes(String key,String value) {
        Notes.add(new paire(key,value));
    }
    public String getStudentName()
    {
        return Student_Name;
    }
    public String getStudent_Code() {
        return Student_Code;
    }
    public String getClassName() {
        return ClassName;
    }
}
