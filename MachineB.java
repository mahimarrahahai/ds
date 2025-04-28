package assignment5;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MachineB {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Start a server to receive token
        ServerSocket server = new ServerSocket(5000);
        boolean hasToken = false; // Start without token

        while (true) {
            // Wait to receive token
            Socket client = server.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            in.readLine(); // Just receive token (no checking)
            client.close();
            System.out.println("[Machine B] Token received from Machine A.");
            hasToken = true;

            if (hasToken) {
                System.out.println("[Machine B] You have the token.");
                System.out.print("Enter 'y' to enter Critical Section: ");
                if (sc.nextLine().equalsIgnoreCase("y")) {
                    System.out.println("[Machine B] In Critical Section...");
                    Thread.sleep(2000);
                    System.out.println("[Machine B] Exiting Critical Section.");
                }

                // Send token back to Machine A
                Socket socket = new Socket("127.0.0.1", 4000);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println("TOKEN");
                socket.close();
                System.out.println("[Machine B] Token sent to Machine A.");
                hasToken = false;
            }
        }
    }
}
