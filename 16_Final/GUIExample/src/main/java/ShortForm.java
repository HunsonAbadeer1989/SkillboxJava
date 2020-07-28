import javax.swing.*;
import java.awt.event.ActionListener;

public class ShortForm extends JPanel {
    private JPanel panel1;
    private JTextField textField1;
    private JButton expandButton;
    private JLabel ShortFormLabel;


    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel1 = this;
    }

    public void addApplyListener(ActionListener listener) {
        expandButton.addActionListener(listener);
    }

    public void removeApplyListener(ActionListener listener) {
        expandButton.removeActionListener(listener);
    }

    public Person getPerson(){
        String[] data = textField1.getText().trim().split("\\s+");
        switch(data.length){
            case 0:
                return new Person();
            case 1:
                return new Person(data[0], "", "");
            case 2:
                return new Person(data[0], data[1], "");
            case 3:
                return new Person(data[0], data[1], data[2]);
            default: return null;
        }
    }

    public void setPerson(Person person){
        textField1.setText(person.toString());
    }

}
