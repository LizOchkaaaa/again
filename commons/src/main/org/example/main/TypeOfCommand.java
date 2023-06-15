package main.org.example.main;

public enum TypeOfCommand {
    Help("Help"),
    Info("Info"),
    Show("Show"),
    Clear("Clear"),
    Print_Field_Ascending_Students_Count("Print_Field_Ascending_Students_Count"),
    Print_Unique_Form_Of_Education("Print_Unique_Form_Of_Education"),
    Remove_by_id("Remove_by_id"),
    Remove_greater("Remove_greater"),
    Reorder("Reorder"),
    Sum_Of_Students_Count("Sum_Of_Students_Count"),
    Add("Add"),
    Add_if_max("Add_if_max"),
    Execute_script("Execute_script"),
    Update("Update"),
    Register("Register"),
    Login("Login"),
    Exit("Exit");

    private final String value;

    TypeOfCommand(String aValue) {
        value = aValue;
    }

    public String getValue() {
        return value;
    }

    public static TypeOfCommand getEnum(String value) {
        if (value != null) {
            for (TypeOfCommand v : values())
                if (v.getValue().equalsIgnoreCase(value)) return v;
        }
        return null;
    }
}