package ssm.controller;

import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserCongtroller {


        public void user(){

            ApplicationContext applicationContext=new ClassPathXmlApplicationContext("");
            applicationContext.getBean("");

        }


}
