package ir.maktab;
import ir.maktab.model.Address;
import ir.maktab.model.DAO.StudentDAO;
import ir.maktab.model.DAO.StudentDaoImp;
import ir.maktab.model.DAO.TeacherDao;
import ir.maktab.model.DAO.TeacherDaoImp;
import ir.maktab.model.Student;
import ir.maktab.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {


        SessionFactory factory = new Configuration().configure().buildSessionFactory();


        TeacherDao teacherDAO = new TeacherDaoImp(factory);
        StudentDAO studentDAO = new StudentDaoImp(factory);


        // This command open a new connection to the database.
        Session session = factory.openSession();





        Teacher teacher1 = new Teacher("Mohamad","hashemi",1234L,2000.00, LocalDate.of(1995,2,10));
        Teacher teacher2 = new Teacher("Mohamad1","hashemi",1235L,2500.00,LocalDate.of(1995,3,10));
        Teacher teacher3 = new Teacher("Mohamad2","hashemi",1236L,2800.00,LocalDate.of(1995,10,10));
        Teacher teacher4 = new Teacher("Mohamad3","hashemi",1237L,1000.00,LocalDate.of(1995,5,10));
        Teacher teacher5 = new Teacher("Mohamad4","hashemi",1238L,3000.00, LocalDate.of(1992,12,10));


        Student student1 = new Student("Mehdi", "Moghadam");
        Student student2 = new Student("Mohammad", "Aghayi");
        Student student3 = new Student("Mojtaba", "Noori");
        Student student4 = new Student("Hassan", "Ghasemi");
        Student student5 = new Student("Kamran", "Ghazi");


        Address address1 = new Address("Karaj", "Alborz", "093576756", "first avenue number 3",2563454L);
        Address address2 = new Address("Rasht", "Gilan", "091234413", "first avenue number 3",2563454L);
        Address address3 = new Address("Shahriar", "Tehran", "091234512", "first avenue number 3",2563454L);
        Address address4 = new Address("Karaj", "Alborz", "093837999", "first avenue number 3",2563454L);
        Address address5 = new Address("Tehran", "Tehran", "090334512", "first avenue number 3",2563454L);

        Address address6 = new Address("Karaj", "Alborz", "093545720", "first avenue number 3",2898954L);
        Address address7 = new Address("Rasht", "Gilan", "091234123", "first avenue number 3",25565654L);
        Address address8 = new Address("Shahriar", "Tehran", "091234098", "first avenue number 3",26565654L);
        Address address9 = new Address("Karaj", "Alborz", "093835612", "first avenue number 3",2565564L);
        Address address10 = new Address("Tehran", "Tehran", "0903897282", "first avenue number 3",2563454L);


        teacher1.setAddress(address1);
        teacher2.setAddress(address2);
        teacher3.setAddress(address3);
        teacher4.setAddress(address4);
        teacher5.setAddress(address5);


        student1.setAddress(address6);
        student2.setAddress(address7);
        student3.setAddress(address8);
        student4.setAddress(address9);
        student5.setAddress(address10);

        teacherDAO.create(teacher1);
        teacherDAO.create(teacher2);
        teacherDAO.create(teacher3);
        teacherDAO.create(teacher4);
        teacherDAO.create(teacher5);

        studentDAO.create(student1);
        studentDAO.create(student2);
        studentDAO.create(student3);
        studentDAO.create(student4);
        studentDAO.create(student5);


        // Find teachers where their city is Tehran
        System.out.println(teacherDAO.findTeachersByTheirCity("Tehran"));

        // Find teachers who's phone number starts with '0912'
        System.out.println(teacherDAO.findTeachersByTheirNumber("0912"));

        // Find teachers who's phone number starts with '0912' and their city is 'Rasht'
        System.out.println(teacherDAO.findTeachersByTheirNumberAndCity("0912", "Rasht"));

        // Find students where their city is not 'Tehran'
        System.out.println(studentDAO.findStudnesWhereTheirCityIsNot("Tehran"));

        factory.close();

    }
}
