package com.example.concerto.service;

import com.example.concerto.pojo.Project;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:32 2021/4/28
 * @Version: 1.0$
 */


public interface ProjectService {

    long insertProject(Project project, HttpSession httpSession);

    void joinProject(HttpSession httpSession, long projectId);

    List<Project> getAllProject(HttpSession httpSession);
}
