package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class GetJifenServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int jifen = -1;
        String a = request.getParameter("user");
        if(a!=null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/greentrack?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
                String username = "root";
                String password = "123456";
                Connection conn = DriverManager.getConnection(url,username,password);
                if(conn!=null){
                    System.out.println("数据库连接成");
                    String sql = "select * from up where user = '"+a+"'";
                    Statement statement = conn.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        jifen = resultSet.getInt(3);
                    }
                    statement.close();
                    conn.close();
                }
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.write(String.valueOf(jifen));
        out.flush();
        out.close();
    }
}
