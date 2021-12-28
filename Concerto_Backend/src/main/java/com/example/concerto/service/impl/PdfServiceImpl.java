package com.example.concerto.service.impl;

import com.example.concerto.dao.PdfItemDao;
import com.example.concerto.dao.ProjectManagementDao;
import com.example.concerto.dao.TaskDao;
import com.example.concerto.dao.TaskInfoDao;
import com.example.concerto.pojo.PdfItem;
import com.example.concerto.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 21:42 2021/12/27
 * @Version: 1.0$
 */
@Slf4j
@Service
public class PdfServiceImpl implements PdfService {
    @Autowired
    PdfItemDao pdfItemDao;
    @Override
    public List<PdfItem> getAll() {
        return pdfItemDao.getAll();
    }

    @Override
    public List<PdfItem> getByKeyword(String keyword) {
        return pdfItemDao.selectByName(keyword);
    }
}
