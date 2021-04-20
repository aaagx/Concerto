package com.example.concerto.controller;

import com.example.concerto.response.CommonResponse;
import com.example.concerto.pojo.Project;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:52
 */
@RestController()
public class TestController {

    @RequestMapping("/rsp")
    public CommonResponse testCommonResponse(){
        Project project = new Project();
        project.setProjectDescription("hahhaha");
        project.setProjectId(404004L);

        return new CommonResponse(200,"对了",project);
    }
}
