package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "SetJifenServlet",urlPatterns = "/setjifen")
public class SetJifenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a = request.getParameter("user");
        int add = Integer.valueOf(request.getParameter("add"));
        if(a != null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/greentrack?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
                String username = "root";
                String password = "123456";
                Connection conn = DriverManager.getConnection(url,username,password);
                if(conn!=null){
                    System.out.println("数据库连接成");
                    String sql = "UPDATE up SET jifen = ? where user = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1,add);
                    ps.setString(2,a);
                    int rows = ps.executeUpdate();
                    ps.close();
                    conn.close();
                }
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

    }
}
