package comineashantrehan.linkedin.httpswww.ucourse;

import android.app.Application;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class db extends Application  {

    Connection conn;
    String url = "";
    String DBName="ucourse";
    String UserName="anandi";
    String Password="anandi";
    String Servername="PostgreSQL 11";
    Statement st;

    public void open(){
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            //url ="jdbc:postgresql://anandi:anandi@localhost:5432/ucourse";
            //test.connectDB("jdbc:postgresql://localhost:5432/csc343h-patela65?currentSchema=parlgov", "patela65", "");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/ucourse","anandi","anandi");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    public void save(String sql) {
        try{
            st = conn.createStatement();
            st.executeQuery(sql);
        }
        catch (Exception ex ){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}