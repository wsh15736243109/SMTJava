package com.smtjava.demo.controller;

import com.smtjava.demo.base.BaseRes;
import com.smtjava.demo.mapper.StudentMapper;
import com.smtjava.demo.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Reader;

@Controller
public class LoginController {
    @RequestMapping("/index")
    public String toIndex(HttpServletRequest servlet) {

        return "login";
    }

    @ResponseBody
    @RequestMapping("/login")
    public BaseRes login(@RequestParam(value = "username") String userName, @RequestParam(value = "password") String password) {
        //  按ID查询书名
        String resource = "mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlMapper.openSession();
        Student user = session.getMapper(StudentMapper.class).queryByName(userName);
        session.close();
        if (user == null) {
            return new BaseRes(-1, "该用户不存在").setData("该用户不存在");
        } else {
            //
            return new BaseRes().setData(user);
        }
    }

    @ResponseBody
    @RequestMapping("/registerUser")
    public BaseRes userRegister(@RequestParam(value = "bookName") String userName, @RequestParam(value = "password") String password) {
        //  按ID查询书名
        String resource = "mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlMapper.openSession();
        Student student = new Student(userName);
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        int re = studentMapper.registerUser(student);
        if (re > 0) {
            session.commit();
            session.close();
            return new BaseRes().setData("register");
        } else {
            session.rollback();
            return new BaseRes(-1, "操作失败").setData("register fail");
        }
    }

    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/main")
    public String toMain() {
        return "main";
    }
}
