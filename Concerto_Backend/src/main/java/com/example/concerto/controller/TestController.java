package com.example.concerto.controller;

import com.example.concerto.pojo.Project;
import com.example.concerto.pojo.Task;
import com.example.concerto.response.CommonResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sarise
 * @version 1.0
 * @date 2021/4/20 下午7:52
 */
@RestController()
@RequestMapping("test")
public class TestController {

    @RequestMapping("rsp")
    public CommonResponse testCommonResponse(){
        Project project = new Project();
        project.setProjectDescription("hahhaha");
        project.setProjectId(404004L);
        return new CommonResponse(200,"对了",project);
    }

    @RequestMapping("param")
    public Long testCParam(@RequestBody Task task){
         task.getTags().forEach(tag -> {
             System.out.println(tag.getTagId());
        });
         return task.getTaskStartTime().getTime();
    }


    @RequestMapping("param1")
    public String testCParam1(@RequestBody String hi){
        String aaaa = hi;
        return aaaa;
    }
}
