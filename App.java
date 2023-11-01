import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

class App {
    public static void main(String ar[]){
        try{
            System.out.println("Database Tutorial");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "password123A$");
            Statement st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs=st.executeQuery("select * from student");
            System.out.println("Database Connected");
            rs.first();
            do {
                System.out.println("ID is "+rs.getInt(1)+" Name is "+rs.getString(2));
            } while (rs.next());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
