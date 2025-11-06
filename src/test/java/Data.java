import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class Data {

    String Url = "https://automationteststore.com/";

    Connection con;
    Statement stmt;
    ResultSet rs;

    //String SignUp ="";

    String TheFirstName;
    String TheLastName;
    String TheEmail;
    String TheTelephone ;
    String TheFax = "13940";

    Random rand = new Random();
    int RandomNumber = rand.nextInt(10000);

    String Domain = "@gmail.com";

    String TheAddress ;
    String TheCountry;

    String TheCity;

    String ThePostalCode = "18881";

    String Name ;

    String ThePassword = "123!@#P@ssw0rd";

    String ExpectedMessage = "Your Account Has Been Created!";



}
