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
        String lines = "";

        while(!line.equals("Stop")) {
            try {
                line = this.input.readLine();
                this.out.writeUTF(line);

                lines = this.in.readUTF();
                System.out.println("S: " + lines);

            } catch (IOException e) {
                System.out.println(e);
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
        new conectaCuatro.Client("10.94.255.167", 6666);
    }
}
