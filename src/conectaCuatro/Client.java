package conectaCuatro;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream out;
    private DataInputStream in;

    public Client(String address, int port) {
        try {
            this.socket = new Socket(address, port);
            System.out.println("Connected");
            this.input = new BufferedReader(new InputStreamReader(System.in));
            this.out = new DataOutputStream(this.socket.getOutputStream());
            this.in = new DataInputStream(new BufferedInputStream(this.socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
        }

        String line = "";
        String lines;

        while(!line.equals("Stop")) {
            lines = "";
            try {
                System.out.println();
                for(int i = 1; i <= 7; i++ ){
                        System.out.print(" " + i);
                }
                for(int i = 6; i != 0; i--){
                    System.out.println();
                    for(int j = 7;j != 0; j--){
                        System.out.print(" " + this.in.readChar());
                    }
                }
                System.out.println();
                while(!lines.equals("Bien") && !lines.contains("guanyat")){
                    line = this.input.readLine();
                    this.out.writeUTF(line);

                    lines = this.in.readUTF();
                    System.out.println("S: " + lines);
                }
                if (lines.contains("guanyat"))return;

            } catch (IOException e) {
                System.out.println("Che vato, q paso?");
            }
        }

        try {
            this.input.close();
            this.out.close();
            this.socket.close();
        } catch (IOException var5) {
            System.out.println(var5);
        }

    }

    public static void main(String[] args) {
        new conectaCuatro.Client("127.0.0.1", 6666);
    }
}
