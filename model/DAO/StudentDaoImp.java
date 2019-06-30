package ir.maktab.model.DAO;

import ir.maktab.BaseDao.BaseDaoImp;
import ir.maktab.model.Student;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.List;


public class StudentDaoImp extends BaseDaoImp<Student> implements StudentDAO {

    public StudentDaoImp(SessionFactory factory) {
        super(factory);
    }

    @Override
    protected String getEntityName() {
        return null;
    }

    @Override
    public List<Student> searchStudentsByName(String name) {

        Session session = factory.openSession();

        String hql = "from Student s where s.firstName = :name or s.lastName = :name";

        Query query = session.createQuery(hql);

        query.setString("name", name);

        List studentsByName = query.list();


        return studentsByName;

    }

    @Override
    public List<Student> searchStudentsByNameCriteria(String name) {

        Session session = factory.openSession();

        Criteria cr = session.createCriteria(Student.class);

        Criterion firstName = (Criterion) Restrictions.like("firstName", name);
        Criterion lastName = (Criterion) Restrictions.like("lastName", name);

        LogicalExpression or = Restrictions.or(firstName, lastName);

        cr.add(or);

        List results = cr.list();


        session.close();

        return results;
    }

    @Override
    public List<Student> findStudnesWhereTheirCityIsNot(String city) {
        Session session = factory.openSession();

        List<Student> students;

        students = session.createQuery("from Student s where s.address.city!=:c")
                .setParameter("c", city).getResultList();

        session.close();

        return students;
    }
}
