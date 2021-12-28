package com.example.concerto.pojo;

import lombok.NonNull;

import java.util.Date;

/**
 * @ Author     ：aaagx.
 * @ Date       ：Created in 20:15 2021/12/27
 * @Version: 1.0$
 */

public class PdfItem {
    @NonNull
    Long pdfId ;
    String pdfName ;
    String pdfDescription;
    Date pdfUpdateTime;
    int type;
    int subject;

    @Override
    public String toString() {
        return "PdfItem{" +
                "pdfId=" + pdfId +
                ", pdfName='" + pdfName + '\'' +
                ", pdfDescription='" + pdfDescription + '\'' +
                ", pdfUpdateTime=" + pdfUpdateTime +
                ", type=" + type +
                ", subject=" + subject +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public Long getPdfId() {
        return pdfId;
    }

    public void setPdfId(Long pdfId) {
        this.pdfId = pdfId;
    }

    public String getPdfName() {
        return pdfName;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public String getPdfDescription(String s) {
        return pdfDescription;
    }

    public void setPdfDescription(String pdfDescription) {
        this.pdfDescription = pdfDescription;
    }

    public Date getPdfUpdateTime() {
        return pdfUpdateTime;
    }

    public void setPdfUpdateTime(Date pdfUpdateTime) {
        this.pdfUpdateTime = pdfUpdateTime;
    }

}
