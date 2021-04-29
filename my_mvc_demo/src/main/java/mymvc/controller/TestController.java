package mymvc.controller;

import mymvc.entity.SystemAdmin;
import mymvc.service.SystemAdminService;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.HashMap;

public class TestController {
    public String test(HttpServletRequest request, HashMap model){
        model.put("user","tyong");
        return  "test";
    }

    public void test1(HttpServletRequest request, HashMap model){
        Integer[] integers = new Integer[3];
        integers[0] = 123;
        integers[1] = 456;
        integers[2] = 789;
        model.put("name","tyong");
        model.put("integers",integers);
    }

    public int test2(HttpServletRequest request, HashMap model){
        model.put("user","tyong");
        return  123;
    }

    public void jdbc(HttpServletRequest request, HashMap model) throws ClassNotFoundException, SQLException {
        SystemAdmin systemAdmin = new SystemAdmin("你好", "123123123", "sdfsdf");
        SystemAdminService systemAdminService = new SystemAdminService();
        SystemAdmin register = systemAdminService.register(systemAdmin);
        if(register == null){
            model.put("msg","注册失败");
        }else{
            model.put("msg","注册成功");
        }

    }
}
