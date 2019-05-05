package com.smtjava.demo.mapper;

import com.smtjava.demo.pojo.Student;

public interface StudentMapper {
    Student queryByName(String bookName);
    int registerUser(Student student);

    Student authPwd(String userName, String password);
}
