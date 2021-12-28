package com.example.concerto.controller;

import com.example.concerto.annotation.UserLoginToken;
import com.example.concerto.pojo.Project;
import com.example.concerto.response.CommonResponse;
import com.example.concerto.service.PdfService;
import com.example.concerto.service.ProjectService;
import com.example.concerto.vo.ProjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 22:49 2021/4/28
 * @Version: 1.0$
 */
@RestController()
public class PdfController {
    @Autowired
    PdfService pdfService;

    @GetMapping("/Pdf/list")
    public CommonResponse getAllPdfItem()
    {
        return  new CommonResponse(200,"ok",pdfService.getAll());
    }

    @GetMapping("/Pdf/Search/{keyword}")
    public CommonResponse getByKeyword(@PathVariable String keyword)
    {
        return  new CommonResponse(200,"ok",pdfService.getByKeyword(keyword));
    }



}
