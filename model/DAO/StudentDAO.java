package ir.maktab.model.DAO;


import ir.maktab.BaseDao.BaseDao;
import ir.maktab.model.Student;

import java.util.List;


public interface StudentDAO extends BaseDao<Student> {


    List<Student> searchStudentsByName(String name);


    List<Student> searchStudentsByNameCriteria(String name);


    List<Student> findStudnesWhereTheirCityIsNot(String city);
}
