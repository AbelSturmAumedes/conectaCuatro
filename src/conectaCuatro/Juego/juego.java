package conectaCuatro.Juego;

import java.util.Scanner;

public class juego {

    static char[][] conecta = new char[8][9];
    static int contador;
    static Scanner scan = new Scanner(System.in);
    static boolean haGuanat = false;


    public static void main(String[] args) {
        inicialitzarConecta('·');
        while (!haGuanat) {
            jugar();
        }


    }

    private static void jugar() {
        mostrarCamp();
        char player = scan.nextLine().charAt(0);
        int posicio = scan.nextInt();
        scan.nextLine();
        colocarFicha(posicio, player);
        haGuanat = comprobarVictoria(player);

    }



    private static void mostrarCamp() {
        for(int i = 0; i < conecta.length - 1; ++i) {
            for (int j = 1; j < conecta[0].length - 1; ++j) {
                if (i == 0 && j < 9) System.out.print(j + "  ");
                else if (i == 0 && j >= 9) System.out.print(j + " ");
                else System.out.print(conecta[i][j] + "  ");
            }
            System.out.println();
        }
    }

    static void inicialitzarConecta(char caracter){
        for(int i = 1;i < conecta.length - 1; i++ ){
            for (int j = 1;j < conecta[0].length - 1;j++){
                conecta[i][j] = caracter;
            }
        }

    }

    static void colocarFicha(int j,char player){
        contador = 0;
        for(int i = 6;i >= 1; i-- ){
            if(conecta[i][j] != 'X' && conecta[i][j] != 'O'){
                conecta[i][j] = player;
                break;
            }
        }
    }



    public static boolean comprobarVictoria(char player){
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
            for(int col = 1; col < conecta[0].length; col++){
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
