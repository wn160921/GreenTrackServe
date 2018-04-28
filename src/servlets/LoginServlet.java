package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String a = request.getParameter("username");
        String b = request.getParameter("password");
        String result = "1";
        if(a!=null&&b!=null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/greentrack?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
                String username = "root";
                String password = "123456";
                Connection conn = DriverManager.getConnection(url,username,password);
                if(conn!=null){
                    System.out.println("数据库连接成");
                    String sql = "select * from up where user = '"+a+"' and password = '"+b+"'";
                    Statement  statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    if(resultSet.wasNull()){
                        result = "2";
                    }
                    statement.close();
                    resultSet.close();
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
