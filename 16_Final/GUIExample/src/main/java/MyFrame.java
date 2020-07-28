import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    private String title;
    private FullForm fullForm = new FullForm();
    private ShortForm shortForm = new ShortForm();
    private Person person;

    {
        fullForm.addApplyListener(e -> switchToShort());
        shortForm.addApplyListener(e -> switchToFull());

    }

    public MyFrame(String title) {
        setTitle(this.title = title);
        setVisible(true);
        setSize(600, 480);
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(fullForm);

    }

    protected void switchToFull() {
        Person person = shortForm.getPerson();
        fullForm.setPerson(person);
        setContentPane(fullForm);
        repaint();
        revalidate();
    }

    protected void switchToShort() {
        if(fullForm.getNameField().equals("") || fullForm.getSurnameField().equals("")){

            JOptionPane.showMessageDialog(null,
                                 "Input your name and surname!",
                                        "Warning", JOptionPane.WARNING_MESSAGE);

        }
        else {
            person = fullForm.getPerson();
            shortForm.setPerson(person);
            setContentPane(shortForm);
            repaint();
            revalidate();
        }
    }

}
