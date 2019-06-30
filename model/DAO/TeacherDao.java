package ir.maktab.model.DAO;



import ir.maktab.BaseDao.BaseDao;
import ir.maktab.model.Teacher;

import java.util.List;


public interface TeacherDao extends BaseDao<Teacher> {


    void deleteByTeacherCode(Long teacherCode);


    List<Teacher> maxAndMinSalaryOfTeachers();



    List<Teacher> youngestAndOldestTeachers();


    List<Teacher> findTeachersByTheirCity(String city);

    List<Teacher> findTeachersByTheirNumber(String phoneNumber);

    List<Teacher> findTeachersByTheirNumberAndCity(String phoneNumber, String city);
}