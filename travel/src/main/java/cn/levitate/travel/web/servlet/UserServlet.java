package cn.levitate.travel.web.servlet;

import cn.levitate.travel.domain.ResultInfo;
import cn.levitate.travel.domain.User;
import cn.levitate.travel.service.UserService;
import cn.levitate.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service=new UserServiceImpl();
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equalsIgnoreCase(code)){
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            response.setContentType("/json;charset=utf-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(resultInfo));
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        boolean flag=service.regist(user);
        ResultInfo info = new ResultInfo();
        if(flag){
            info.setFlag(true);
        }else{
            info.setErrorMsg("注册失败");
            info.setFlag(false);
        }
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().write(json.getBytes());
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User u=service.login(user);
        ResultInfo info = new ResultInfo();
        if(u==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        if(u!=null&&!"Y".equalsIgnoreCase(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }
        if(u!=null&&"Y".equals(u.getStatus())){
            info.setFlag(true);
            request.getSession().setAttribute("user",u);
        }
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    public void findUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(response.getOutputStream(),user);
    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if(code!=null){
            boolean flag=service.active(code);
            String msg=null;
            if(flag){
                msg="激活成功,请<a href='login.html'>登录</a>";
            }else{
                msg="激活失败,请联系管理员!";
            }
            response.setContentType("text/html;charset=urg-8");
            response.getWriter().write(msg);
        }
    }
}
