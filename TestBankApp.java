import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

 class BankAppModified {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking App";
        final String CREATE_NEW_ACCOUNT="(01)-Create New Account";
        final String DEPOSIT_MONEY = "(02)-Deposit Money";
        final String WITHDRAW_MONEY= "(03)-Withdraw  Money";
        final String TRANFER_MONEY = "(04)-Tranfer Money";
        final String CHECK_ACCOUNT_BALANCE="(05)-Check Account Balance";
        final String DROP_EXISTING_ACCOUNT="(06)-Drop Existing Account";
        final String EXIT = "EXIT from the app";

        
      
        ArrayList<String[]> DetailsArray=new ArrayList<String[]>();

        String screen = DASHBOARD;
        String ID;

        boolean validNew=true;
        // String nameNew;
        // int initialDeposit;

        do {
            final String APP_TITLE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("-".repeat(40));
            System.out.println(" ".repeat((40 - APP_TITLE.length() + 7)/2).concat(APP_TITLE));
            System.out.println("-".repeat(40));

            switch(screen){
                case DASHBOARD: 
                    System.out.println("\n(01)-Create New Account");
                    System.out.println("(02)-Deposit Money");
                    System.out.println("(03)-Withdraw  Money");
                    System.out.println("(04)-Tranfer Money");
                    System.out.println("(05)-Check Account Balance");
                    System.out.println("(06)-Drop Existing Account");
                    System.out.println("(07)-Exit\n");
                    System.out.print("Enter an option to continue > ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option){
                        case 1: screen=CREATE_NEW_ACCOUNT ;break;                    
                        case 2: screen = DEPOSIT_MONEY; break;
                        case 3: screen = WITHDRAW_MONEY; break;
                        case 4: screen = TRANFER_MONEY; break;
                        case 5:screen=CHECK_ACCOUNT_BALANCE ;break;
                        case 6:screen=DROP_EXISTING_ACCOUNT; break;
                        case 7: screen=EXIT; break;
                        default: continue;
                    }
                    break;
                

                case CREATE_NEW_ACCOUNT:

                    ID=String.format("SDB-%05d", (DetailsArray.size() + 1));
                    System.out.println("ID :"+ID);
                    
                    String nameNew;
                    double initialDeposit;
                   
                      do{
                        validNew=true;
                        System.out.print("New USer Name : ");
                        nameNew = SCANNER.nextLine().strip();
                        if (nameNew.isBlank()){
                            System.out.printf("%sName can't be empty%s\n", COLOR_RED_BOLD, RESET);
                            validNew = false;
                            continue;
                        }
                        for (int i = 0; i < nameNew.length(); i++) {
                            if (!(Character.isLetter(nameNew.charAt(i)) || 
                                Character.isSpaceChar(nameNew.charAt(i))) ) {
                                System.out.printf("%sInvalid Name%s\n", COLOR_RED_BOLD, RESET);
                                validNew = false;
                                continue;
                            }

                        }
                    }while(!validNew);


                    do{
                        validNew=true;
                        System.out.print("Initial Deposit : ");
                        initialDeposit= SCANNER.nextDouble();
                        SCANNER.nextLine();

                        if(initialDeposit<5000){
                                System.out.printf("%sInsufficiant Amount%s\n", COLOR_RED_BOLD, RESET);
                                validNew = false;
                                continue;

                       

                        }

                


                    } while(!validNew);

                    String[] subArray={ID,nameNew,initialDeposit+""};
                    
                    DetailsArray.add(subArray);

                    //System.out.println(DetailsArray.get(0)[0].equals("SDB-00002"));
                    //System.out.println("Validation");

                    System.out.println();
                    System.out.printf( " %s : %shas been created sucessfully. Do you want to add new Customer(Y/n)? ",ID,nameNew);
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                    
                    case DEPOSIT_MONEY:
        lblDepositwMoney:
        
        do {        validNew=true;
                    System.out.print("Enter the user ID-:");
                    ID = SCANNER.nextLine().strip();

                    //Need to validate...(USer ID Format?)
                    
                    System.out.print("How much do you want to Deposit -: ");
                    double Deposit=SCANNER.nextDouble();
                    SCANNER.nextLine();


                    for (int index = 0; index < DetailsArray.size(); index++) {
                        if(DetailsArray.get(index)[0].equals(ID)){
                            double x=Double.valueOf(DetailsArray.get(index)[2]);
                            DetailsArray.get(index)[2]=x+Deposit+""; 
                            System.out.println("Deposit has been done successfully..");  
                            validNew=false;  
                        
                        } 
                        
                        
                    } 

                    if (validNew) {System.out.println("Entered User ID doesent exits....Enter a Corrected One");
                                        validNew=false;
                                         continue lblDepositwMoney;}

                    System.out.print(" Do you want to do Another Deposit(Y/n)");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue lblDepositwMoney;
                    screen = DASHBOARD;
                    break lblDepositwMoney;

        }while(!validNew); 



                case WITHDRAW_MONEY:
        lblWithDrawMoney:

        do {         validNew=true;
                    System.out.print("Enter the user ID-:");
                    ID = SCANNER.nextLine().strip();

                    //Need to validate...(USer ID Format?)
                    
                    System.out.print("How much do you want to withdraw -: ");
                    double withdraw=SCANNER.nextDouble();
                    SCANNER.nextLine();

                    for (int index = 0; index < DetailsArray.size(); index++) {
                        if(DetailsArray.get(index)[0].equals(ID)){
                            double x=Double.valueOf(DetailsArray.get(index)[2]);
                            if (x-withdraw>=500){
                                System.out.println("Withdraw Successfull......");
                                DetailsArray.get(index)[2]=x-withdraw+"";

                                
                                break;

                            }
                            else {System.out.println("Insufficient balance for withdraw...");
                                       validNew=false;    
                                        break;   }       } 
                        
                        else {System.out.println("Entered User ID doesent exits....Enter a Corrected One");
                                        validNew=false;
                                         continue lblWithDrawMoney;}      
                    } 

                    System.out.println(" Do you want to do Another Withdrawal(Y/n)");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = DASHBOARD;
                    break;

                }while(!validNew); 
            
    

                case TRANFER_MONEY:

                lblTranferMoney:

                do{   validNew=true;
                    System.out.print("Enter the user ID-:");
                    ID = SCANNER.nextLine().strip();

                    System.out.print("Enter the User ID which you want to transfer-:");
                    String transferID = SCANNER.nextLine().strip();

                    System.out.print("How much do you want to transfer -: ");
                    double transfer=SCANNER.nextDouble();
                    SCANNER.nextLine();

                    int count=0;

                    //Update DetailsArrayList
                    for (int i = 0; i < DetailsArray.size(); i++) {
                        if(DetailsArray.get(i)[0].equals(ID)){
                             double y=Double.valueOf(DetailsArray.get(i)[2]);
                             DetailsArray.get(i)[2]=y-transfer+"";
                             count++;
                            
                                

                        }
                        if(DetailsArray.get(i)[0].equals(transferID)){
                             double k=Double.valueOf(DetailsArray.get(i)[2]);
                             DetailsArray.get(i)[2]=k+transfer+"";
                             count++;
                                

                        }

                        if (count==2)  validNew=false;
                        
                    }
                    System.out.println();
                    System.out.printf( " Transaction between %s and %s has been created sucessfully. Do you want to do transaction(Y/n)? ",ID,transferID);
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue lblTranferMoney;
                    screen = DASHBOARD;
                    break;

                }while(validNew );



    
                case DROP_EXISTING_ACCOUNT:
    labelDropAccount:

        do{
                    validNew=true;
                    System.out.print("Enter the user ID You want to Delete-:");
                     ID = SCANNER.nextLine().strip();

                    //Need to validate...(USer ID Format?)
                    
                  
                    for (int index = 0; index < DetailsArray.size(); index++) {
                        if(DetailsArray.get(index)[0].equals(ID)){
                            DetailsArray.remove(index);
                            System.out.printf(" %s was removed succesfully...",ID); }
                           
                         else {
                            System.out.println("Entered account No is invalid..Try again...");
                            continue labelDropAccount;}
                            
                                
                        }   
                    System.out.println( "  Do you want to remove Customer(Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue labelDropAccount;
                    screen = DASHBOARD;
                    break;
                    
                
                }while(!validNew); 
                    
                    
                    
                case CHECK_ACCOUNT_BALANCE:

      labelCheckBalance:
            do{
                    validNew=true;
                    System.out.print("Enter the user ID You want to CHECk alance-:");
                     ID = SCANNER.nextLine().strip();

                  
                    for (int index = 0; index < DetailsArray.size(); index++) {
                        if(DetailsArray.get(index)[0].equals(ID)){
                            ID=DetailsArray.get(index)[0];
                            double Balance=Double.valueOf(DetailsArray.get(index)[2]);
                            
                            System.out.printf("Account %s has %s LKR...",ID,Balance); }

                         else {
                            System.out.println("Entered account No is invalid..Try again...");
                            continue ;}
                            
                                
                        }  
                    
                    System.out.printf( " %s account has  Do you want to remove Customer(Y/n)? ",ID);
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y")) continue labelCheckBalance;
                    screen = DASHBOARD;
                    break;

                    
                
                }while(!validNew); 



                case EXIT:
                    final String APP_CLOSE = String.format("%s%s%s",
                                COLOR_BLUE_BOLD, "Thank Your Using Smart Bank APP!", RESET);
                    
                    System.out.println("-".repeat(40));
                    System.out.println();
                    System.out.println();
                    System.out.println();
                     System.out.println(" ".repeat((40 - APP_CLOSE.length() + 7)/2).concat(APP_CLOSE));
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    
                    System.out.println("-".repeat(40));
                    
                    System.exit(0);
                    break;
            
                }
            
        }while(true);

    
    }



}









 