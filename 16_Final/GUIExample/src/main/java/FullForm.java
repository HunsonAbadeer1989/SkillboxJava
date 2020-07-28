import javax.swing.*;
import java.awt.event.ActionListener;

public class FullForm extends JPanel {

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField patronimic;
    private JButton button1;
    private JPanel panel1;
    private JLabel FullFormLabel;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel1 = this;
    }

    public void addApplyListener(ActionListener listener) {
        button1.addActionListener(listener);
    }

    public void removeApplyListener(ActionListener listener) {
        button1.removeActionListener(listener);
    }

    public Person getPerson(){
        return new Person(nameField.getText().trim(), surnameField.getText().trim(), patronimic.getText().trim());
    }

    public void setPerson(Person person){
        nameField.setText(person.getName());
        surnameField.setText(person.getSurName());
        patronimic.setText(person.getPatronymic());
    }

    public String getNameField() {
        return nameField.getText();
    }

    public String getSurnameField() {
        return surnameField.getText();
    }
}
