package xin.wangning.servlets;

import cn.itcast.commons.CommonUtils;
import com.alibaba.fastjson.JSON;
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

    public String findUserToLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        if(username == null||username.equals("")) return "参数错误";
        System.out.println("usernaem:"+username);
        String password = request.getParameter("password");
        if(password==null) return "参数错误";
        System.out.println("password:"+password);
        User user = service.findByUserName(username);
        if(user==null){
            return "参数错误";
        }
        System.out.println("真正密码："+user.getPassword());
        if(user.getPassword().equals(password)){
            return JSON.toJSONString(user);
        }
        //返回查找结果，找不到用户或者，json字符串
        return "密码错误";
    }

    public String getUserByUuid(HttpServletRequest request,HttpServletResponse response)
        throws ServletException, IOException{
        String uuid = request.getParameter("uuid");
        if(uuid==null||uuid.equals("")) return "无uuid";
        User user = service.findById(uuid);
        if(user!=null){
            return JSON.toJSONString(user);
        }else {
            return "无信息";
        }
    }

    public String addRewardPoints(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        String uuid = request.getParameter("uuid");
        if(uuid==null||uuid.equals("")) return "无uuid";
        int updateNum = Integer.parseInt(request.getParameter("updateNum"));
        service.updateRewardPoints(uuid,updateNum);
        return "积分改变成功";
    }

}
