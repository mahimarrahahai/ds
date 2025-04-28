import java.net.Socket;
import java.util.Scanner;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import ReverseModule.Reverse;
import ReverseModule.ReverseHelper;

public class ReverseClient {
    public static void main(String[] args) {
        Reverse  reverseImpl=null;
        try{
        org.omg.CORBA.ORB orb=org.omg.CORBA.ORB.init(args,null);
        
        org.omg.CORBA.Object objRef=orb.resolve_initial_references("NameService");

        NamingContextExt ncRef=NamingContextExtHelper.narrow(objRef);
        
        String name="Reverse";
         
        reverseImpl=ReverseHelper.narrow(ncRef.resolve_str(name));

        System.out.println("Enter string ");
        
        Scanner sc = new Scanner(System.in);

        String str=sc.nextLine();

         String temp=reverseImpl.reverseString(str);

         System.out.println(temp);

        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
