package com.example.swspenc.advappdevproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.swspenc.advappdevproject.R;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlStuff extends AppCompatActivity
{

    public Connection con;

    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String name1 = "";
        String result1 = "";


        @Override
        protected String doInBackground(String... params)
        {
            try
            {
                con = connectionclass();        // Connect to database
                if (con == null)
                {
                    z = "Check Your Internet Access!";
                }
                else
                {
                    // Change below query according to your own database.
                    String query = "select * from [Stephen+Spencer]";
                    //String query2 = "insert into [Stephen+Spencer](FirstName, LastName) values('Captain', 'Kopernicus')";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    //stmt.executeUpdate(query2);
                    if(rs.next())
                    {
                        name1 = rs.getString("LastName"); //Name is the string label of a column in database, read through the select query
                        z = "query successful";
                        isSuccess=true;
                        con.close();
                    }
                    else
                    {
                        z = "Invalid Query!";
                        isSuccess = false;
                    }
                }
            }
            catch (Exception ex)
            {
                isSuccess = false;
                z = ex.getMessage();

                Log.d ("sql error", z);
            }
            return z;
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionclass()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //your database connection string goes below
            ConnectionURL ="jdbc:jtds:sqlserver://simmering.database.windows.net:1433;DatabaseName=SBS;user=Bedtime@simmering;password=Southerners!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            //"jdbc:jtds:sqlserver://simmering.database.windows.net:1433;DatabaseName=SBS;user=Bedtime@simmering;password=Southerners!;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            //"jdbc:jtds:Server=classprep.database.windows.net;DatabaseName=msis4363;user=classprep@classprep;password=Password1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }

    public void Update (String TableName, String Column1, String Column2, String Value1, String Value2) {
        String z = "";

        Boolean isSuccess = false;
        con = connectionclass();

        try {
            String query2 = "update [" + TableName + "]" + "set " + Column1 + " = " + Value1 + "where " + Column2 + "=" + Value2;
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(query2);

            if (i > 0) {
                z = "query successful";
                isSuccess = true;
                con.close();
            } else {
                z = "Invalid Query!";
                isSuccess = false;
            }
        }
        catch (Exception ex)
        {
            Log.e("sql error", ex.getMessage());
        }
    }

    public void Delete (String TableName, String Column1, String Value1) {
        String z = "";

        Boolean isSuccess = false;
        con = connectionclass();

        try {
            //"delete from [Stephen+Spencer]" + "where FirstName = 'Captain'"
            String query2 = "delete from [" + TableName + "]" + "where " + Column1 + " = " + Value1;
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(query2);

            if (i > 0) {
                z = "query successful";
                isSuccess = true;
                con.close();
            } else {
                z = "Invalid Query!";
                isSuccess = false;
            }
        }
        catch (Exception ex)
        {
            Log.e("sql error", ex.getMessage());
        }
    }

    public void Insert (String TableName, String Column1, String Value1) {
        String z = "";

        Boolean isSuccess = false;
        con = connectionclass();

        try {
            //insert into [Stephen+Spencer](FirstName, LastName) values('Captain', 'Kopernicus')"
            String query2 = "insert into [" + TableName + "](" + Column1 + ") values(" + Value1 + ")";
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(query2);

            if (i > 0) {
                z = "query successful";
                isSuccess = true;
                con.close();
            } else {
                z = "Invalid Query!";
                isSuccess = false;
            }
        }
        catch (Exception ex)
        {
            Log.e("sql error", ex.getMessage());
        }
    }

    public boolean Login(String TableName, String Column1, String Column2, String Value1, String Value2) {
        String z = "";
        String Col2 = "";
        boolean isSuccess = false;
        boolean isValid = false;

        con = connectionclass();

        try {
            //insert into [Stephen+Spencer](FirstName, LastName) values('Captain', 'Kopernicus')"
            String query2 = "Select " + Column2 + "from [" + TableName + "] where " + Column1 + "=" + Value1 +";";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query2);

            if (rs.next()) {
                Col2 = rs.getString(Column2);
                z = "query successful";
                isSuccess=true;
                con.close();
            } else {
                z = "Invalid Query!";
                isSuccess = false;
            }
        }
        catch (Exception ex)
        {
            Log.e("sql error", ex.getMessage());
        }

        if (Col2.equals(Value2))
        {
            isValid = true;
        }
        return isValid;
    }
}
