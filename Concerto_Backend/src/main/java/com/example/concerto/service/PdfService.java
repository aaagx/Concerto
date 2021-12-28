package com.example.concerto.service;

import com.example.concerto.pojo.*;
import com.example.concerto.vo.ProjectVo;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 16:05 2021/4/25
 * @Version: 1.0$
 */

public interface PdfService {
    List<PdfItem> getAll();
    List<PdfItem> getByKeyword(String keyword);
}
