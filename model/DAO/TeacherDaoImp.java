package ir.maktab.model.DAO;

import ir.maktab.BaseDao.BaseDaoImp;
import ir.maktab.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


public class TeacherDaoImp extends BaseDaoImp<Teacher> implements TeacherDao {

    public TeacherDaoImp(SessionFactory factory) {
        super(factory);
    }

    @Override
    protected String getEntityName() {
        return "Teacher";
    }

    @Override
    public void deleteByTeacherCode(Long teacherCode) {
        Session session = factory.openSession();

        session.beginTransaction();

        session.createQuery("delete from Teacher t where t.teacherCode = ?1").setParameter(1, teacherCode).executeUpdate();

        session.close();
    }

    @Override
    public List<Teacher> maxAndMinSalaryOfTeachers() {
        Session session = factory.openSession();


        Object singleResultMax = session.createQuery("from Teacher t order by t.salary").setMaxResults(1).getSingleResult();
        Object singleResultMin = session.createQuery("from Teacher t order by t.salary desc ").setMaxResults(1).getSingleResult();
        Teacher eMax = (Teacher) singleResultMax;
        Teacher eMin = (Teacher) singleResultMin;

        List<Teacher> maxMin = new ArrayList<>();
        maxMin.add(eMax);
        maxMin.add(eMin);

        session.close();

        return maxMin;
    }


    @Override
    public List<Teacher> youngestAndOldestTeachers() {

        Session session = factory.openSession();

        Object singleResultMax = session.createQuery("from Teacher t order by t.birthday asc ").setMaxResults(1).getSingleResult();
        Object singleResultMin = session.createQuery("from Teacher t order by t.birthday desc").setMaxResults(1).getSingleResult();

        Teacher eMax = (Teacher) singleResultMax;
        Teacher eMin = (Teacher) singleResultMin;

        List<Teacher> maxMin = new ArrayList<>();
        maxMin.add(eMax);
        maxMin.add(eMin);

        session.close();

        return maxMin;

    }

    @Override
    public List<Teacher> findTeachersByTheirCity(String str) {

        Session session = factory.openSession();

        List teachers;

        teachers = session.createQuery("from Teacher t where t.address.city=:str")
                .setParameter("str", str).getResultList();

        session.close();

        return teachers;
    }

    @Override
    public List<Teacher> findTeachersByTheirNumber(String phoneNumber) {

        Session session = factory.openSession();

        List<Teacher> teachers;

        teachers = session.createQuery("from Teacher t  where  t.address.number like :p")
                .setParameter("p", phoneNumber + "%").getResultList();

        session.close();

        return teachers;
    }

    @Override
    public List<Teacher> findTeachersByTheirNumberAndCity(String phoneNumber, String city) {

        Session session = factory.openSession();

        List<Teacher> teachers;

        teachers = session.createQuery("from Teacher t where t.address.city=:c and t.address.number like :p")
                .setParameter("p", phoneNumber + "%").setParameter("c", city).getResultList();

        session.close();

        return teachers;
    }
}