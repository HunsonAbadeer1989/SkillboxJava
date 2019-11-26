public class Person
{
    private String surName;
    private String name;
    private String patronymic;

    public Person(){

    }

    public Person(String surname, String name, String patronymic)
    {
        this.surName = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        if( surName != null ){
            this.surName = surName;
        }
        else this.surName = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if( name != null ){
            this.name = name;
        }
        else this.name = "";
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for(String value : new String[]{getSurName(), getName(), getPatronymic()}){
            if(data.length() > 0 && value != null && value.length() > 0)
                data.append(" ");
            data.append(value);
        }
        return data.toString();
    }
}
