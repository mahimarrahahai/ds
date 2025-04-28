package assignment5;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MachineA {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Start a server to receive token
        ServerSocket server = new ServerSocket(4000);
        boolean hasToken = true; // Start with token

        while (true) {
            if (hasToken) {
                System.out.println("[Machine A] You have the token.");
                System.out.print("Enter 'y' to enter Critical Section: ");
                if (sc.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("[Machine A] In Critical Section...");
                    Thread.sleep(2000);
                    System.out.println("[Machine A] Exiting Critical Section.");
                }

                // Send token to Machine B
                Socket socket = new Socket("127.0.0.1", 5000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("TOKEN");
                socket.close();
                System.out.println("[Machine A] Token sent to Machine B.");
                hasToken = false;
            }

            // Wait to receive token back
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            in.readLine(); // Just receive token (no checking)
            client.close();
            System.out.println("[Machine A] Token received from Machine B.");
            hasToken = true;
        }
        
        
    }
}
