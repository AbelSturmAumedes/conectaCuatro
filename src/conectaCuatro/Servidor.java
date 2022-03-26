//server

package conectaCuatro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



public class Servidor {
    private Socket socketP1;
    private Socket socketP2;
    //private BufferedReader inputP1;
    //private BufferedReader inputP2;
    static private DataOutputStream outP1;
    private DataOutputStream outP2;
    private DataInputStream inP1;
    private DataInputStream inP2;
    private ServerSocket server;
    static char[][] conecta = new char[8][9];
    static boolean haGuanat = false;

    public Servidor(int port) {
        try {
            this.server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for client ...");
            this.socketP1 = this.server.accept();
            System.out.println("Client accepted");
            System.out.println("Waiting for client2 ...");
            //this.socketP2 = this.server.accept();
            System.out.println("Client accepted");

            this.inP1 = new DataInputStream(new BufferedInputStream(this.socketP1.getInputStream()));
            //this.inP2 = new DataInputStream(new BufferedInputStream(this.socketP2.getInputStream()));
            //this.inputP1 = new BufferedReader(new InputStreamReader(System.in));
            //this.inputP2 = new BufferedReader(new InputStreamReader(System.in));
            this.outP1 = new DataOutputStream(this.socketP1.getOutputStream());
            //this.outP2 = new DataOutputStream(this.socketP1.getOutputStream());

            String lineP1 = "";
            String lineP2 = "";
            inicialitzarConecta('.');

            while(!lineP1.equals("Stop") || !lineP2.equals("Stop"))  {

                try {

                    mostrarCampP1();
                    while(true){
                        lineP1 = this.inP1.readUTF();
                        if (lineP1.equals("Stop")) break;
                        if (!verificarEntrada(lineP1, 'X')) continue;
                        colocarFicha(Integer.parseInt(lineP1), 'X');
                        if (comprobarVictoria('X')){
                            this.outP1.writeUTF("El jugador X ha guanyat");
                         break;
                        }
                        this.outP1.writeUTF("Espera al teu torn...");
                        break;
                    }
                    if (comprobarVictoria('X') || lineP1.equals("Stop")){
                        mostrarCampP1();
                        break;
                    }
                   /* while(true){
                        lineP2 = this.inP2.readUTF();
                        if (lineP2.equals("Stop")) break;
                        if (!verificarEntrada(lineP2), 'O') continue;
                        colocarFicha(Integer.parseInt(lineP2), 'O');
                        this.outP2.writeUTF("Bien");
                        break;
                    }*/


                } catch (IOException e) {
                    System.out.println("ERROR");
                }
            }

            System.out.println("Closing connection");
            this.socketP1.close();
            //this.socketP2.close();
            this.inP1.close();
            //this.inP2.close();
        } catch (IOException var5) {
            System.out.println("HOLA");
        }

    }

    public static void main(String[] args) {
        new Servidor(6666);
    }

    private void mostrarCamp() throws IOException {
        for (int i = 1; i <= 6; i++){
            for (int j = 1; j <= 7; j++){
                this.outP1.writeChar(conecta[i][j]);
                //this.outP2.writeChar(conecta[i][j]);
            }
        }
    }
    private void enviar(){

    }

    private void inicialitzarConecta(char caracter){
        for(int i = 1;i < conecta.length - 1; i++ ){
            for (int j = 1;j < conecta[0].length - 1;j++){
                conecta[i][j] = caracter;
            }
        }

    }

    private void colocarFicha(int j,char player){

        for(int i = 6;i >= 1; i-- ){
            if(conecta[i][j] != 'X' && conecta[i][j] != 'O'){
                conecta[i][j] = player;
                break;
            }
        }
    }

    private boolean verificarEntrada(String line,char caracter) throws IOException {
        try{
            if(Integer.parseInt(line) < 1 || Integer.parseInt(line) > 7){
                this.outP1.writeUTF("Escribe numero ente 1 y 7");
                return false;
            }
            if(conecta[1][Integer.parseInt(line)] == caracter){
                this.outP1.writeUTF("Esta línea esta llena");
                return false;
            }

        }catch (NumberFormatException | IOException e ){
            if(caracter == 'X') this.outP1.writeUTF("Escribe numero ente 1 y 7");
            else  this.outP2.writeUTF("Escribe numero ente 1 y 7");
            return false;
        }
        return true;
    }

    private boolean comprobarVictoria(char player){
        //check for 4 across
        for(int row = 1; row<conecta.length; row++){
            for (int col = 1;col < conecta[0].length - 3;col++){
                if (conecta[row][col] == player   &&
                        conecta[row][col+1] == player &&
                        conecta[row][col+2] == player &&
                        conecta[row][col+3] == player){
                    return true;
                }
            }
        }
        //check for 4 up and down
        for(int row = 1; row < conecta.length - 3; row++){
            for(int col = 1; col < conecta[0].length - 1; col++){
                if (conecta[row][col] == player   &&
                        conecta[row+1][col] == player &&
                        conecta[row+2][col] == player &&
                        conecta[row+3][col] == player){
                    return true;
                }
            }
        }
        //check upward diagonal
        for(int row = 4; row < conecta.length; row++){
            for(int col = 1; col < conecta[0].length - 4; col++){
                if (conecta[row][col] == player   &&
                        conecta[row-1][col+1] == player &&
                        conecta[row-2][col+2] == player &&
                        conecta[row-3][col+3] == player){
                    return true;
                }
            }
        }
        //check downward diagonal
        for(int row = 1; row < conecta.length - 4; row++){
            for(int col = 1; col < conecta[0].length - 4; col++){
                if (conecta[row][col] == player   &&
                        conecta[row+1][col+1] == player &&
                        conecta[row+2][col+2] == player &&
                        conecta[row+3][col+3] == player){
                    return true;
                }
            }
        }
        return false;



    }



}
