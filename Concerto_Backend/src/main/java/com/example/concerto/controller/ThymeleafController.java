package com.example.concerto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:49 2021/12/27
 * @Version: 1.0$
 */


@Controller
@RequestMapping("/")
public class ThymeleafController {

    @RequestMapping(value = "/thymeleaf")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "world") String name,
                           Model model) {
        model.addAttribute("xname", name);
        return "index";
    }
}

