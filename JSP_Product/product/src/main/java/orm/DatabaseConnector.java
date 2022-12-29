package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
   private static DatabaseConnector dbc=new DatabaseConnector();
   private Connection conn=null;   //url, user, pw 값을 설정하기 위해서 sql에서 제공하는 인터페이스
   private String jdbcDriver="com.mysql.cj.jdbc.Driver";
   private String jdbcUrl="jdbc:mysql://localhost:3306/javadb";
   
   private DatabaseConnector() {
      try {
         Class.forName(jdbcDriver); 
         conn=DriverManager.getConnection(jdbcUrl, "javauser", "mysql");
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버를 찾을 수 없습니다.");
         e.printStackTrace();
      } catch (SQLException e) {
         System.out.println("연결 정보를 찾을 수 없습니다.");
         e.printStackTrace();
      }
   }
   
   //싱글톤 방식으로 생성
   public static DatabaseConnector getInstance() {
      return dbc;
   }
   
   public Connection getConnection() {
      return conn;
   }
}