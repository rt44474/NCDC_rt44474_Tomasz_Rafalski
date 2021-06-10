import java.io.UnsupportedEncodingException;
import java.util.Scanner;
public class Drwal {
    public static char[][] color(char[][] input, Character kolor,int x, int y){
        input[x][y] = kolor;
        try {
            if (input[x + 1][y] == ' ') {
                color(input, kolor, x + 1, y);
            }
            if (input[x][y + 1] == ' ') {
                color(input, kolor, x, y + 1);
            }
            if (input[x - 1][y] == ' ') {
                color(input, kolor, x - 1, y);
            }
            if (input[x][y - 1] == ' ') {
                color(input, kolor, x, y - 1);
            }
            return input;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return input;
        }
    }
    public static int drwal(String[] args){
        //Sprawdzanie czy jest 5 argumentow
        if (args.length == 5) {
            //Sprawdzanie czy argument 1,2,4,5 to inty
            try {
                Integer.parseInt(args[0]);
                Integer.parseInt(args[1]);
                Integer.parseInt(args[3]);
                Integer.parseInt(args[4]);
            }catch (NumberFormatException e){
                System.out.println("klops");
                return 0;
            }
            int xStart = Integer.parseInt(args[1]);
            int yStart = Integer.parseInt(args[0]);
            Character kolor = args[2].charAt(0);
            int szerokosc = Integer.parseInt(args[3]);
            int wysokosc = Integer.parseInt(args[4]);
            Scanner input = new Scanner(System.in);
            char[][] output1 = new char[wysokosc][szerokosc];
            int wyscount = 0;
            int counter = 0;
            //Jezeli szerokosc albo wysokosc > 5000
            //Jezeli xstart > szerokosc, ystart > wysokosc
            if (!((szerokosc > 5000 || wysokosc > 5000)||(xStart > szerokosc || yStart > wysokosc))){
                while(input.hasNextLine()){
                    String tmp;
                    byte[] isUTF = null;
                    try {
                        tmp = input.nextLine();
                        isUTF = tmp.getBytes("UTF-8");
                    }
                    catch(UnsupportedEncodingException e){
                        System.out.println("klops");
                        return 0;
                    }
                    if (wyscount > wysokosc || tmp.length() > szerokosc){
                        System.out.println("klops");
                        return 0;
                    }
                    for (int i = 0;i<tmp.length();i++){
                        output1[counter][i] = tmp.charAt(i);
                    }
                    counter++;
                    wyscount++;
                }
                int startx = xStart -1;
                int starty = yStart -1;
                //Jezeli punkt startowy to spacja to zaczynamy kolorowanie
                if (output1[startx][starty]==' '){
                    output1 = color(output1,kolor,startx,starty);
                }
                //wypisywanie na wyjsciu pokolorwanego obrazka
                for (int i=0;i<wyscount;i++){
                    for (int j=0;j<szerokosc;j++){
                        System.out.print(output1[i][j]);
                    }
                    System.out.println();
                }
            }
            else {
                System.out.println("klops");
                return 0;
            }
        }
        else{
            System.out.println("klops");
            return 0;
        }
        return 0;
    }
    public static void main(String[] args) {
        drwal(args);
    }
}