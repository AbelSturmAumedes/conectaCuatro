

package conectaCuatro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private Socket socketP1;
    private Socket socketP2;
    //private BufferedReader inputP1;
    //private BufferedReader inputP2;
    private DataOutputStream outP1;
    private DataOutputStream outP2;
    private DataInputStream inP1;
    private DataInputStream inP2;
    private ServerSocket server;

    public Servidor(int port) {
        try {
            this.server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for client ...");
            this.socketP1 = this.server.accept();
            System.out.println("Client accepted");
            System.out.println("Waiting for client2 ...");
            this.socketP2 = this.server.accept();
            System.out.println("Client accepted");

            this.inP1 = new DataInputStream(new BufferedInputStream(this.socketP1.getInputStream()));
            this.inP2 = new DataInputStream(new BufferedInputStream(this.socketP2.getInputStream()));
            //this.inputP1 = new BufferedReader(new InputStreamReader(System.in));
            //this.inputP2 = new BufferedReader(new InputStreamReader(System.in));
            this.outP1 = new DataOutputStream(this.socketP1.getOutputStream());
            this.outP2 = new DataOutputStream(this.socketP1.getOutputStream());

            String lineP1 = "";
            String lineP2 = "";

            while(!lineP1.equals("Stop")) {
                try {

                    lineP1 = this.inP1.readUTF();
                    System.out.println("P1: " + lineP1);


                } catch (IOException var4) {
                    System.out.println("HOLA");
                }
            }

            System.out.println("Closing connection");
            this.socketP1.close();
            this.socketP2.close();
            this.inP1.close();
            this.inP2.close();
        } catch (IOException var5) {
            System.out.println("HOLA");
        }

    }

    public static void main(String[] args) {
        new Servidor(6666);
    }
}
