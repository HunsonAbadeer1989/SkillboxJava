import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Loader
{
    public static void main(String[] args) {
        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

//        Course course = session.get(Course.class, 1);
//        List<Student> studentList = course.getStudents();
//        studentList.forEach(student -> {
//            System.out.println(student.getName() + " " + student.getRegistrationDate());
//        });

        Teacher teacher = session.get(Teacher.class, 4);
        System.out.println(teacher.getCourse());

        transaction.commit();
        sessionFactory.close();
    }
}
