package com.java.controller;


import com.github.pagehelper.PageInfo;
import com.java.entity.Book;
import com.java.entity.BookType;
import com.java.service.BookService;
import com.java.service.BookTypeService;
import com.java.utils.JSONUtils;
import com.java.utils.ResultInfo;
import com.java.vo.BookVo;
import com.java.vo.ConditionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookTypeService bookTypeService;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAll(){
        List<Book> allBook = bookService.getAllBook();
        ResultInfo resultInfo = new ResultInfo();
        if (allBook!=null && allBook.size()>0){
            resultInfo.setData(allBook);
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
        }
        return JSONUtils.getJson(resultInfo);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delete(@RequestParam(value = "bookid") Integer id){
        boolean flag=bookService.deleteBook(id);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        return JSONUtils.getJson(resultInfo);
    }

    @RequestMapping(value = "/pre_add",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String pre_add(){
        List<BookType> bookType=bookTypeService.getAllBookType();
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setData(bookType);
        return JSONUtils.getJson(resultInfo);
    }

    @RequestMapping(value = "/addBook11",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addBook(Book book){
        boolean flag=bookService.addBook(book);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        return JSONUtils.getJson(resultInfo);
    }


    @RequestMapping(value = "/pre_update",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> pre_update(@RequestParam(value = "bookid") Integer bookid){
        Book book=bookService.getBookById(bookid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("book",book);
        List<BookType> type = bookTypeService.getAllBookType();
        map.put("type",type);
        return map;

    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String update(Book book){
       boolean flag= bookService.updateBook(book);
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);
        return JSONUtils.getJson(resultInfo);
    }

    @RequestMapping(value = "/detailaa",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String detailaa(@RequestParam(value = "bookid") Integer bookid){
        BookVo bookVo=bookService.getBookVoById(bookid);
        ResultInfo resultInfo = new ResultInfo();
        if (bookVo!=null){
            resultInfo.setData(bookVo);
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
        }
        return JSONUtils.getJson(resultInfo);
    }




    @RequestMapping(value = "/page_list",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String,Object> page_list(ConditionVo vo,
                                        @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(value = "pageSize",defaultValue = "3") Integer pageSize){
        HashMap<String, Object> map = new HashMap<>();
        PageInfo<BookVo> pageInfo=bookService.getPageData(vo,pageNum,pageSize);
        map.put("page",pageInfo);
        List<BookType> types = bookTypeService.getAllBookType();
        map.put("types",types);
        map.put("vo",vo);
        return map;
    }

    //批量删除1
    @RequestMapping(value = "/deleteChecked",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteChecked(@RequestParam(value = "checkId") String checkId){
        ResultInfo resultInfo = new ResultInfo();
        boolean flag=bookService.deleteChecked(checkId);
        resultInfo.setFlag(flag);
        return JSONUtils.getJson(resultInfo);
    }

    //批量删除2

    @RequestMapping(value = "/batDelete",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String batDelete(@RequestParam(value = "arr[]") Integer[] arr){
        ResultInfo resultInfo = new ResultInfo();
        boolean flag=bookService.batDelete(arr);
        resultInfo.setFlag(flag);
        return JSONUtils.getJson(resultInfo);
    }
}
