package com.java.service.impl;

import com.java.entity.BookType;
import com.java.mapper.BookTypeMapper;
import com.java.service.BookTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookTypeServiceimpl implements BookTypeService{
    @Autowired
    private BookTypeMapper bookTypeMapper;
    @Override
    public List<BookType> getAllBookType() {
        return bookTypeMapper.selectByExample(null);
    }
}
