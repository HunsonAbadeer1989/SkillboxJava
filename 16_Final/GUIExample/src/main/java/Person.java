public class Person {

    private String name;
    private String surname;
    private String patronymic;

    public Person(){
    }

    public Person(String firstname, String surname, String patronimic) {
        this.name = firstname;
        this.surname = surname;
        this.patronymic = patronimic;
    }

    public String getName() {
        return name;
    }


    public String getSurName() {
        return surname;
    }


    public String getPatronymic() {
        return patronymic;
    }


    @Override
    public String toString() {
        StringBuilder data = new StringBuilder();
        for(String value : new String[]{getName(), getSurName(), getPatronymic()}){
            if(data.length() > 0 && value != null && value.length() > 0)
                data.append(" ");
            data.append(value);
        }
        return data.toString();
    }
}
