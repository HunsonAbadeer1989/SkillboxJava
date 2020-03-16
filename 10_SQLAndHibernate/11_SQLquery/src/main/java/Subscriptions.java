import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
public class Subscriptions implements Serializable
{
    @Id
    @Column(name = "student_id")
    private int studentId;
    @Id    @Column(name = "course_id")
    private int courseId;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Course course;

    @Column(name="subscription_date")
    private Date subscriptionDate;

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
