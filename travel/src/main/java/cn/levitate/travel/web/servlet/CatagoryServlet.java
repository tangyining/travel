package cn.levitate.travel.web.servlet;

import cn.levitate.travel.domain.Category;
import cn.levitate.travel.service.CategoryService;
import cn.levitate.travel.service.impl.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catagory/*")
public class CatagoryServlet extends BaseServlet {
    private CategoryService service=new CategoryServiceImpl();
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> all = service.findAll();
        writeValue(all,response);
    }


}
