package Notes;
public class paire{
    public String Module_name;
    public String note;

    public paire(String module_name ,String note)
    {
        this.Module_name=module_name;
        this.note=note;
    }
    public String getModule_Name() {
        return Module_name;
    }
    public String getNote()
    {
        return note ;
    }
}