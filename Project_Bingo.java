package proyecto_bingo;

import java.util.Scanner;
/*
    Adrián Burgos Gálvez
*/

public class Proyecto_Bingo {

    public static int rellenaCartonNoRepetido(int[] carton, int valorMinimo, int valorMaximo) {

        int numero = 0;

        for (int indice = 0; indice < carton.length; indice++) {
            do {
                numero = ((int) (Math.random() * (valorMaximo - valorMinimo + 1))) + valorMinimo;
            } while (compruebaNumRepetido(carton, numero, indice));
            carton[indice] = numero;
        }
        return numero;
    }

    public static boolean compruebaNumRepetido(int[] array, int numero, int valorMaximo) {

        int indice = 0;
        boolean comprobador = false;

        while (indice < valorMaximo) {
            if (array[indice++] == numero) {
                comprobador = true;
            }
        }
        return comprobador;
    }

    public static int rellenaBomboNoRepetido(int[] bombo, int valorMinimo, int valorMaximo) {

        int bola = 0, indice = 0;

        do {
            bola = ((int) (Math.random() * (valorMaximo - valorMinimo + 1))) + valorMinimo;
        } while (compruebaNumRepetido(bombo, bola, 99));
        bombo[indice++] = bola;

        return bola;
    }

    public static String muestraTachones(int numero) {

        String resultado = "" + Math.abs(numero);

        if (numero < 0) {
            resultado = resultado + " X ";
        } else if (numero > 0) {
            resultado = resultado + " · ";
        }
        return resultado;
    }

    public static void muestraArray(int[] array) {

        int contador = 1;

        for (int elemento : array) {
            if (-10 < elemento && elemento < 10) {
                System.out.print(" " + muestraTachones(elemento) + " ");
            } else {
                System.out.print(muestraTachones(elemento) + " ");
            }
            if ((contador++) % 5 == 0) {
                System.out.println();
            }
        }
    }

    public static int compruebaCarton(int[] carton, int bola, int contadorCarton) {

        for (int indice = 0; indice < (carton.length); indice++) {
            if (bola == carton[indice]) {
                //carton[indice] = 0;
                carton[indice] = carton[indice] * (-1);
                contadorCarton++;
            }
        }
        return contadorCarton;
    }

    public static void main(String[] args) {

        int[] carton1 = new int[15];
        int[] carton2 = new int[15];
        int[] carton3 = new int[15];
        int[] bombo = new int[99];
        int contCarton1 = 0, contCarton2 = 0, contCarton3 = 0, bola;
        String respuesta;

        Scanner teclado = new Scanner(System.in);

        System.out.println("¡BIENVENIDO AL BINGOWARS! QUE LA SUERTE TE ACOMPAÑE.");
        System.out.println("Los cartones son los siguientes: ");

        //Rellena los 3 cartones con números aleatorios sin repetir.
        rellenaCartonNoRepetido(carton1, 1, 99);
        rellenaCartonNoRepetido(carton2, 1, 99);
        rellenaCartonNoRepetido(carton3, 1, 99);

        do {
            //Muestra los 3 cartones.
            System.out.println("\n----------CARTÓN 1----------");
            muestraArray(carton1);
            System.out.println("\n----------CARTÓN 2----------");
            muestraArray(carton2);
            System.out.println("\n----------CARTÓN 3----------");
            muestraArray(carton3);

            System.out.println("\nIntroduzca 'ENTER' para continuar.");
            respuesta = teclado.nextLine();

            bola = rellenaBomboNoRepetido(bombo, 1, 99);
            System.out.printf("Ha salido la bola con el número |%d|.\n", bola);
            
            contCarton1 = compruebaCarton(carton1, bola, contCarton1);
            contCarton2 = compruebaCarton(carton2, bola, contCarton2);
            contCarton3 = compruebaCarton(carton3, bola, contCarton3);

        } while (contCarton1 != (carton1.length) && contCarton2 != (carton2.length) && contCarton3 != (carton3.length));

        System.out.println("\n¡¡BINGO!!");
        if (contCarton1 == (carton1.length)) {
            System.out.println("El ganador es el jugador con el cartón 1.");
            muestraArray(carton1);
        }
        if (contCarton2 == (carton2.length)) {
            System.out.println("El ganador es el jugador con el cartón 2.");
            muestraArray(carton2);
        }
        if (contCarton3 == (carton3.length)) {
            System.out.println("El ganador es el jugador con el cartón 3.");
            muestraArray(carton3);
        }

    }

}
