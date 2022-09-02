package com.tedu.firstspringbootdemo.controller;

import com.tedu.firstspringbootdemo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * 使用当前类处理所有与用户相关的业务操作
 *
 * 当页面提交表单时，浏览器显示404，说明服务端没有找到对应的业务类处理，可能的问题:
 * 1:业务类(这里比如是UserController)所在的包(controller)是否放在了项目启动类
 *   FirstSpringBootApplication所在的包下(com.tedu.firstspringboot)，这个
 *   是框架要求的，必须严格执行。
 * 2:当前业务类UserController上是否有注解@Controller
 * 3:处理对应业务的方法上是否有注解@RequestMapping,并且该注解上的参数值是否与页面表单
 *   上action的值一致（保证一致的同时，该地址必须以"/"开头，比如/regUser）
 * 低级错误:
 * 1:服务器没有重启，类和页面修改过后，服务器都要重启，如果改过页面浏览器还需刷新。
 * 2:检查浏览器请求路径是否为8080端口，不能是63342这个端口(这个是在IDEA中预览页面的端口)！！！
 *
 */
//spring框架要求，只有被注解@Controller标注的类才是处理业务的类
@Controller
public class UserController {

    private static File userDtr;//存放用户所有目录信息
    static{
        userDtr=new File("./users");
        if (!userDtr.exists()){
            userDtr.mkdirs();
        }
    }

    //@RequestMapping注解用于标注处理某个具体业务的方法，参数传入的字符串与对应页面中表单的action地址一致
    @RequestMapping("/regUser")
    public void reg(HttpServletRequest request, HttpServletResponse response) {
         /*
            处理注册的流程:
            1:获取注册页面上表单里用户输入的注册信息
            2:将注册信息保存在硬盘上
            3:回复浏览器一个页面，用来告知注册结果(成功或失败)
         */
        /*
            获取注册页面reg.html中表单提交的注册信息
            请求对象:
            HttpServletRequest
            它表示浏览器本次提交上来的所有内容
         */
        //通过request对象获取表单中4个输入框的内容
        //获取表单内容
        String username = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        String nick = request.getParameter("nick");
        String age = request.getParameter("age");
        int a=Integer.parseInt(age);
        User user=new User(username,pwd,nick,a);
        File file=new File(userDtr,username+"obj");
        try (
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos=new ObjectOutputStream(fos);
                )
        {
            oos.writeObject(user);
            //利用响应对象要求浏览器访问注册成功页面
            response.sendRedirect("/reg_success.html");
        }catch (Exception e){
            e.printStackTrace();
        }



        System.out.println(username + "," + pwd + "," + nick + "," + age);
    }
}
