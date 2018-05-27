package xin.wangning.servlets;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import com.sun.deploy.net.HttpRequest;
import xin.wangning.domain.User;
import xin.wangning.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends MobileBaseServlet {
    private UserService service = new UserService();

    public String addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = CommonUtils.toBean(request.getParameterMap(),User.class);
        user.setUuid(CommonUtils.uuid());
        String result = service.add(user);
        return result;
    }

    public String findUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        if(uuid == null||uuid.equals("")) return "参数错误";
        String result = service.findById(uuid);
        return result;
    }

}
