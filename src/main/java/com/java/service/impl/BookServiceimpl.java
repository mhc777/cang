package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.entity.Book;
import com.java.entity.BookExample;
import com.java.entity.BookType;
import com.java.mapper.BookMapper;
import com.java.mapper.BookTypeMapper;
import com.java.service.BookService;
import com.java.vo.BookVo;
import com.java.vo.ConditionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookServiceimpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookTypeMapper bookTypeMapper;
    @Override
    public List<Book> getAllBook() {
        return bookMapper.selectByExample(null);
    }

    @Override
    public boolean deleteBook(Integer bookid) {
        return bookMapper.deleteByPrimaryKey(bookid)>0;
    }

    @Override
    public boolean addBook(Book book) {
        return bookMapper.insertSelective(book)>0;
    }

    @Override
    public Book getBookById(Integer bookid) {
        return bookMapper.selectByPrimaryKey(bookid);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookMapper.updateByPrimaryKeySelective(book)>0;
    }

    @Override
    public BookVo getBookVoById(Integer bookid) {
        BookVo bookVo = new BookVo();
        Book book = bookMapper.selectByPrimaryKey(bookid);
        BeanUtils.copyProperties(book,bookVo);
        BookType bookType = bookTypeMapper.selectByPrimaryKey(book.getTypeid());
        bookVo.setTypename(bookType.getTypename());
        return bookVo;
    }

    @Override
    public PageInfo<BookVo> getPageData(ConditionVo vo, Integer pageNum, Integer pageSize) {
        //标志着分页开始
        PageHelper.startPage(pageNum,pageSize);
        BookExample example = new BookExample();
        BookExample.Criteria criteria=example.createCriteria();
        if (vo!=null){
            Integer typeid = vo.getTypeid();
            if (typeid!=null && typeid!=-1){
                criteria.andTypeidEqualTo(typeid);
            }
            String author = vo.getAuthor();
            if (StringUtils.isNoneBlank(author)){
                criteria.andAuthorLike("%"+author+"%");
            }
            Double min_price=vo.getMin_price();
            Double max_price=vo.getMax_price();
            if (max_price!=null && min_price!=null){
                criteria.andPriceBetween(min_price,max_price);
            }
        }
        List<Book> books = bookMapper.selectByExample(example);
        PageInfo<Book> bookPageInfo = new PageInfo<>(books);
        ArrayList<BookVo> bookVos = new ArrayList<>();
        for (Book book : books) {
            BookVo bookVo = new BookVo();
            BeanUtils.copyProperties(book,bookVo);
            BookType bookType = bookTypeMapper.selectByPrimaryKey(book.getTypeid());
            bookVo.setTypename(bookType.getTypename());
            bookVos.add(bookVo);
        }
        PageInfo<BookVo> bookVoPageInfo = new PageInfo<>(bookVos);
        bookVoPageInfo.setPageNum(bookPageInfo.getPageNum());
        bookVoPageInfo.setPages(bookPageInfo.getPages());

        return bookVoPageInfo;
    }

    @Override
    public boolean deleteChecked(String checkId) {
        return bookMapper.deleteChecked(checkId.split(","))>0;
    }

    @Override
    public boolean batDelete(Integer[] arr) {
        return bookMapper.batDelete(arr)>0;
    }
}
