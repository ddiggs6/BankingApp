import java.util.Scanner
import java.sql.DriverManager
import java.sql.Connection
import java.io.File
import java.io.PrintWriter

object BankingApp{

    
    //Main Method
    def main(args:Array[String]): Unit ={


            
         val driver = "com.mysql.cj.jdbc.Driver"
        val url = "jdbc:mysql://localhost:3306/BankingApp" // Modify for whatever port you are running your DB on
        val username = "root"
        val password = "KeepGoing2022*" // Update to include your password
        
      val log = new PrintWriter(new File ("BankingApp.log"))
        
        
        var connection:Connection = null
      
        var acctnum = ""
         var name = ""
         var accttype = ""
        var Sbalance = 0.00
        var acctBalance = 0.00
        var takeout = 0
       var dAmount = 0
        //Scanner input = new Scanner(System.in)
    
    def acctDetails(): Unit ={
            println("What is your name?")
            name = scala.io.StdIn.readLine()
            print("Please enter your account type: ")
            accttype = scala.io.StdIn.readLine()
            print("Please enter your account number: ")
            acctnum = scala.io.StdIn.readLine()
            print("Please enter your starting balance: ")
            acctBalance = scala.io.StdIn.readInt()
        }

        def deposit(acctBalance: Double): Double ={
            
            println("Enter the amount you are depositing: ")
            dAmount = scala.io.StdIn.readInt()
            var acctBalance = Sbalance + dAmount
            return acctBalance

        }

        def withdrawal(takeOut: Double): Double={
            

             println("How much would you like to withdraw?")
            takeout = scala.io.StdIn.readInt()
            if( takeout > acctBalance){
                println(" You are withdrawing more than you have.")
            }else{
                var acctBalance = Sbalance - takeout
            println("You withdrew " + takeout + ".")
            }

            return acctBalance
        }

        
        
        def showAccount(): Unit ={

            println("Name: " + name)
            println("Account Number: " + acctnum)
            println("Account Type: " + accttype)
            println("Account Balance: " + acctBalance)

        }
        try {
      // make the connection
      Class.forName(driver)
      connection = DriverManager.getConnection(url, username, password)

      // create the statement, and run the select query
      val statement = connection.createStatement()
     // val resultSet = statement.executeQuery("SELECT * FROM BankingApp") // Change query to your table
      /*val receiptTable = connection.createStatement()
      receiptTable.executeUpdate("create table RECEIPTS(Acctnum VARCHAR, Name VARCHAR, Transaction VARCHAR, Balance DOUBLE)")
      receiptTable.close*/
    // val creat_Table = statement.executeUpdate("Create table BankingApp(AcctNum varchar(255), Name varchar(255), Transaction varchar(255), Balance decimal(2)); ")
      //log.write("Executing 'Create table BankingApp(AcctNum varchar(255), Name varchar(255), Transaction varchar(255), Balance decimal(2)';\n")
      val resultSet = statement.executeQuery("SELECT * FROM BankingApp") // Change query to your table
      log.write("Executing 'SELECT * FROM BankingApp';\n")  
       
        //originally under val resulltSet creation
        
                 def createReceipt( acctnum: String, name: String,transaction: String, balance: Double ): Unit = {

            val receiptTable = connection.createStatement()
            receiptTable.executeUpdate("create table RECEIPTS(Acctnum VARCHAR, Name VARCHAR, Transaction VARCHAR, Balance DOUBLE)")
            log.write("Executing 'create table RECEIPTS(Acctnum VARCHAR, Name VARCHAR, Transaction VARCHAR, Balance DOUBLE');")
            receiptTable.close
        }
             println("Hello. What transaction would you like to complete? Select from the options below:")
            println("1. Check Account Balance")
            println("2. Deposit Money")
            println("3. Withdraw Money")
            var option = scala.io.StdIn.readInt()
            var transaction = ""

            
                 acctDetails()           
                if(option == 1){
                showAccount()   
                val statement = connection.createStatement()
                transaction = "Show Account Details"
                var option1 = "INSERT INTO BankingApp(Acctnum, Name, Transaction, Balance) VALUES ('"+ acctnum +"','"+ name +"', '"+ transaction +"', "+ acctBalance + ");"
                 val resultSet1 = statement.executeUpdate(option1 ) // Change query to your table
                    log.write("INSERT Account Details 'BankingApp';\n")
                //log.write("let's see sumn")
                //var option1 = "INSERT INTO BankingApp (Transaction) VALUES" 
                //var transaction1 = statement.executeUpdate(option1)
                
                }
                if(option ==2){
                   // acctDetails()
                    deposit(dAmount)
                    println("Your account balance is now " + (acctBalance + dAmount) + ".")
                    transaction = "Deposit"
                    val statement = connection.createStatement()
                  var option2 = "INSERT INTO BankingApp(Acctnum, Name, Transaction, Balance) VALUES ('"+ acctnum +"','"+ name +"', '"+ transaction +"', "+ (acctBalance+dAmount) + ");"
                    val resultSet2 = statement.executeUpdate(option2 ) // Change query to your table
                    log.write("INSERT Deposit info into 'BankingApp';\n")
                    
                }
                if(option == 3){
                   // acctDetails()
                    withdrawal(takeout)
                    println("Your account balance is now " + (acctBalance - takeout) + ".")
                    transaction = "Withdrawal"
                    val statement = connection.createStatement()
                    var option3 = "INSERT INTO BankingApp(Acctnum, Name, Transaction, Balance) VALUES ('"+ acctnum +"','"+ name +"', '"+ transaction +"', "+ (acctBalance - takeout) + ");"
                    val resultSet3 = statement.executeUpdate(option3) // Change query to your table
                    log.write("INSERT Withdrawal info into 'BankingApp';\n")
                    
                }

                println("Would you like to cancel your last transaction? Yes or No")
                var cancelTransaction = scala.io.StdIn.readLine()
                if(cancelTransaction == "YES" || cancelTransaction =="yes"){
                     val statement = connection.createStatement()
                    var cancel = "DELETE from BankingApp where Acctnum = ('"+ acctnum +"');"
                    val resultSet4 = statement.executeUpdate(cancel) // Change query to your table
                    log.write("DELETE last transaction from 'BankingApp';\n")
                    println("Last transaction deleted")
                }else{}

               
           // print(createReceipt(acctnum, name, transaction, acctBalance))
            println("Transaction complete. Thank you.")
       
                
            // class bankDetails{
         


       /* while ( resultSet.next() ) {
            print(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4))
            println()
        }*/
            } catch {
        case e: Exception => e.printStackTrace
        }
        connection.close()
        log.close()
        
        
      


    }
                //continue ? switch
                 
            

           


    

    
       
}