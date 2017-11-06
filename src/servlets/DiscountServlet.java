package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "DiscountServlet",urlPatterns = "/discount")
public class DiscountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder builder=new StringBuilder();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/greentrack?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
            String username = "root";
            String password = "123456";
            Connection conn = DriverManager.getConnection(url,username,password);
            if(conn!=null){
                System.out.println("数据库连接成");
                String sql = "select * from discount";
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()){
                    builder.append(resultSet.getString(1)+",");
                    builder.append(resultSet.getString(2)+",");
                    builder.append(resultSet.getString(3)+",");
                    builder.append(";");
                }
                response.setCharacterEncoding("utf-8");
                PrintWriter out = response.getWriter();
                out.write(String.valueOf(builder.toString()));
                out.flush();
                out.close();
                statement.close();
                conn.close();
            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
