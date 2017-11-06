package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a = request.getParameter("username");
        String b = request.getParameter("password");
        String result = "2";
        if(a!=null&&b!=null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/greentrack?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
                String username = "root";
                String password = "123456";
                Connection conn = DriverManager.getConnection(url,username,password);
                if(conn!=null){
                    System.out.println("数据库连接成");
                    String sql = "INSERT INTO up(user,password,jifen)VALUES (?,?,?)";
                    PreparedStatement ps=conn.prepareStatement(sql);
                    ps.setString(1,a);
                    ps.setString(2,b);
                    ps.setInt(3,0);
                    int rows = ps.executeUpdate();
                    if (rows == 1){
                        result = "1";
                    }
                    //430321198312070628
                    ps.close();
                    conn.close();
                }
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            }
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write(result);
            out.flush();
            out.close();
        }else {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.write("2");
            out.flush();
            out.close();
        }
    }
}
