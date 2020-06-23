package com.java.service;

import com.github.pagehelper.PageInfo;
import com.java.entity.Book;
import com.java.vo.BookVo;
import com.java.vo.ConditionVo;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    boolean deleteBook(Integer bookid);

    boolean addBook(Book book);

    Book getBookById(Integer bookid);

    boolean updateBook(Book book);

    BookVo getBookVoById(Integer bookid);

    PageInfo<BookVo> getPageData(ConditionVo vo, Integer pageNum, Integer pageSize);

    boolean deleteChecked(String checkId);

    boolean batDelete(Integer[] arr);

}
