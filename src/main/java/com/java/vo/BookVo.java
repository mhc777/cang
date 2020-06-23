package com.java.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookVo {
    private Integer bookid;

    private String bookname;

    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubtime;

    private String author;

    private Integer typeid;

    private String typename;
}
