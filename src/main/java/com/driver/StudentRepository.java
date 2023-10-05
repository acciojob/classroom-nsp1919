package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {
    HashMap<String,Student> studentHashMap=new HashMap<>();
    HashMap<String,Teacher> teacherHashMap=new HashMap<>();
    HashMap<String,List<String>> teachedStudentMap=new HashMap<>();

    public void addStudent(Student student) {
        studentHashMap.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teacherHashMap.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        if (studentHashMap.containsKey(student) && teacherHashMap.containsKey(teacher)){
            if (teachedStudentMap.containsKey(teacher)){
                List<String>temp=teachedStudentMap.get(teacher);
                temp.add(student);
                teachedStudentMap.put(teacher,temp);
            }
            else{
                List<String> studentList=new ArrayList<>();
                studentList.add(student);
                teachedStudentMap.put(teacher,studentList);
            }
        }
    }

    public Student getStudentByName(String mname) {
//        if (studentHashMap.containsKey(mname)){
            return studentHashMap.get(mname);
//        }
//        return null;
    }

    public Teacher getTeacherByName(String name) {
//        if (teacherHashMap.containsKey(name)){
            return teacherHashMap.get(name);
//        }
//        return null;
    }

    public List<String> getStudentsByTeacherName(String teacher) {
//        if (teachedStudentMap.containsKey(teacher)){
            return teachedStudentMap.get(teacher);
//        }
//        return null;
    }

    public List<String> getAllStudents() {
        List<String>studentList=new ArrayList<>();
        studentList.addAll(studentHashMap.keySet());
        return studentList;
    }

    public void deleteTeacherByName(String teacher) {
        if (teachedStudentMap.containsKey(teacher)){
                List<String>temp=teachedStudentMap.get(teacher);
                for (String studentname:temp)
                    studentHashMap.remove(studentname);
            teachedStudentMap.remove(teacher);
            teacherHashMap.remove(teacher);
        }
    }

    public void deleteAllTeachers() {
        for (String teachername:teachedStudentMap.keySet()){
            List<String>temp=teachedStudentMap.get(teachername);
            for (String studentname:temp)
                studentHashMap.remove(studentname);
            teachedStudentMap.remove(teachername);
            teacherHashMap.remove(teachername);
        }
    }
}
