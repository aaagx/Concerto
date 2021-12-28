package com.example.concerto.dao;

import com.example.concerto.pojo.PdfItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 23:09 2021/5/3
 * @Version: 1.0$
 */

@Mapper
public interface PdfItemDao {
        public List<PdfItem> getAll();

        public long insertPdfItem(PdfItem pdfItem);

        public void deletePdfItem(long pdfId);

        public List<PdfItem> selectByName(String keyword);
}
