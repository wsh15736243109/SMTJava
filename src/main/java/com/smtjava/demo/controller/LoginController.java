package com.smtjava.demo.controller;

import com.smtjava.demo.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Reader;

@Controller
public class LoginController {
    @RequestMapping("/index")
    public String toIndex(HttpServletRequest servlet) {
        //  按ID查询书名
        String resource="mybatis-config.xml";
        Reader reader=null;
        try {
            reader= Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlMapper=new SqlSessionFactoryBuilder().build(reader);
        SqlSession session=sqlMapper.openSession();
        Student user=session.selectOne("findById",1);
        servlet.setAttribute("name",user.getBookName());
        session.close();
        return "login";
    }
}
