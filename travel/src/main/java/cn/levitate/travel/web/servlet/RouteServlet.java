package cn.levitate.travel.web.servlet;


import cn.levitate.travel.domain.Favorite;
import cn.levitate.travel.domain.PageBean;
import cn.levitate.travel.domain.Route;
import cn.levitate.travel.domain.User;
import cn.levitate.travel.service.FavoriteService;
import cn.levitate.travel.service.RouteService;
import cn.levitate.travel.service.impl.FavoriteServiceImpl;
import cn.levitate.travel.service.impl.RouteServiceImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service=new RouteServiceImpl();
    private FavoriteService fservice=new FavoriteServiceImpl();

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");
        int cid=0;
        if(cidStr!=null&&cidStr.length()>0&&!"null".equals(cidStr)){
            cid=Integer.parseInt(cidStr);
        }
        int currentPage=0;
        if(StringUtils.isNotBlank(currentPageStr)){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        int pageSize=0;
        if(StringUtils.isNotBlank(pageSizeStr)){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=5;
        }
        PageBean<Route> pageBean=service.pageQuery(cid,currentPage,pageSize,rname);
        writeValue(pageBean,response);
    }

    //详情
    public void findOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        Route route=service.findOne(rid);
        writeValue(route,resp);
    }

    public void isFavoriate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        User user = (User) req.getSession().getAttribute("user");
        int uid;
        if(user==null){
            uid=0;
        }else{
            uid = user.getUid();
        }
        boolean favorite = fservice.isFavorite(rid, uid);
        writeValue(favorite,resp);
    }

    public void addFavoriate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rid = req.getParameter("rid");
        User user = (User) req.getSession().getAttribute("user");
        int uid;
        if(user==null){
           return;
        }else{
            uid = user.getUid();
        }
        fservice.addFavoriate(rid,uid);
    }
}
