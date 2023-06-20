package org.example.main;

public enum TypeOfCommand {
    help("help"),
    info("info"),
    show("show"),
    clear("clear"),
    print_Field_Ascending_Students_Count("print_Field_Ascending_Students_Count"),
    print_Unique_Form_Of_Education("print_Unique_Form_Of_Education"),
    remove_by_id("remove_by_id"),
    remove_greater("remove_greater"),
    reorder("reorder"),
    sum_Of_Students_Count("sum_Of_Students_Count"),
    add("add"),
    add_if_max("add_if_max"),
    execute_script("execute_script"),
    update("update"),
    register("register"),
    login("login"),
    exit("exit");

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