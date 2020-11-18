package com.example.school.starter;

import com.example.school.autoconfig.bean.ISchool;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RunStarter {
    @Resource
    private ISchool school;

    public void execute() {
        System.out.println();
        System.out.println("--------------业务方法开始执行---------------");
        school.print();
        System.out.println("--------------业务方法执行结束---------------");
        System.out.println();
    }
}
