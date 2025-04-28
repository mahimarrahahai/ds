import ReverseModule.ReversePOA;

public class ReverseImpl extends ReversePOA{

    ReverseImpl(){
    super();
    }

    @Override
    public String reverseString(String str) {
     StringBuffer s=new StringBuffer(str);
     s.reverse();
     String up=str.toUpperCase();
     System.out.println("Uppercase "+up);
     return s.toString();
    }
    
}
