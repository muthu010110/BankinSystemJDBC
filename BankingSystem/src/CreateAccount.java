import java.sql.*;
import java.util.Scanner;
public class CreateAccount {

	public static void createaccount() throws Exception{
		String url="jdbc:mysql://localhost:3306/bankingsystem";
		String user="root";
		String password="Muthu@2003";
		
try (Connection con=DriverManager.getConnection(url,user,password);)
				
		{
			Scanner sc=new Scanner(System.in);
			 System.out.println("1. Create new Account");
	            System.out.println("2. View Balance");
	            System.out.println("3. Deposite");
	            System.out.print("Enter your choice: ");
	            int choice = sc.nextInt();
	if(choice==1) {
			PreparedStatement insert=con.prepareStatement("INSERT INTO account_table VALUES(?, ?, ?, ?)");
			System.out.println("welcome to account creation");
			
			
			System.out.println("enter your Account number: ");
			int Accountnumber=sc.nextInt();
			sc.nextLine();
			
			System.out.println("enter account holder name: ");
			String Account_holder_name=sc.nextLine();
			
			System.out.println("enter your age: ");
			int age=sc.nextInt();
			
			System.out.println("add amount in account: ");
			double account_balance=sc.nextDouble();
			
			insert.setInt(1,Accountnumber );
			insert.setString(2, Account_holder_name);
			insert.setInt(3, age);
			insert.setDouble(4,account_balance);
			
			int rowInserted=insert.executeUpdate();
			
			if(rowInserted>0) {
				System.out.println("data inserted successfully");
			}
			else {
				System.out.println("faild to insert");
			}
			}
			
	else if(choice==2) {
			PreparedStatement read=con.prepareStatement ("SELECT Account_holder_name,account_balance  FROM account_table WHERE Account_number=?");
			System.out.println("view your Account balance");
			System.out.println("eneter the account number: ");
			int accountnum=sc.nextInt();
			read.setInt(1, accountnum);
			ResultSet rst1=read.executeQuery();
			if(rst1.next()) {
			System.out.println("Balance from "+rst1.getString("Account_holder_name")+" account:" +rst1.getDouble("account_balance") );
			}
			}
	else if(choice==3){
		PreparedStatement updatepst=con.prepareStatement("UPDATE account_table SET account_balance = account_balance + ? WHERE account_number=?");
		System.out.println("Welcome to deposite ");
		System.out.println("enter your account number: ");
		int accountnum=sc.nextInt();
		System.out.println("enter the ammount to be deposited: ");
		int deposite=sc.nextInt();
		updatepst.setDouble(1,deposite);
		updatepst.setInt(2, accountnum);
		int row=updatepst.executeUpdate();
		System.out.println(row);
		if(row>0) {
			System.out.println("deposite succesfully");
			
			PreparedStatement check=con.prepareStatement("SELECT Account_holder_name,account_balance  FROM account_table WHERE Account_number=?");
			check.setInt(1, accountnum);
			ResultSet rst2=check.executeQuery();
			if(rst2.next()) {
			System.out.println("updated balance "+rst2.getString("Account_holder_name")+" :"+rst2.getDouble("account_balance"));
		}
	}
}
	
	else if(choice==4) {
		PreparedStatement Transation=con.prepareStatement("");
		System.out.println("Welcome to transcation page");
		
	}
	else {
		System.out.println("Account not found");
	}
			
		
}
catch(Exception e){
	e.printStackTrace();
}
		
		
		
		
		
		
	}
	public static void main(String[]args) throws Exception{
		createaccount();
	}
}
