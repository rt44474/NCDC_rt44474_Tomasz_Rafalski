import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Uproszczenia {
    public static int uproszczenia(String[] args){
        if (args.length == 1) {
            //String
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(args[0]);
            boolean checkforspecials = matcher.find();
            if (checkforspecials) {
                System.out.println("klops");
                return 0;
            }
            String part2 = args[0].replaceAll("[^A-Za-z]","");
            //Integer
            String part1 = args[0].replaceAll("[^0-9]","");
            try{
                Integer.parseInt(part1);
            }
            catch (NumberFormatException e){
                System.out.println("klops");
                return 0;
            }
            int part11 = Integer.parseInt(part1);
            if ((part2.equals("N")||part2.equals("S")||part2.equals("E")||part2.equals("W"))&&(part11>=1 && part11<=10000)) {
                // 1- 3x4, 2- 4x5, 3- 5x6 etc.
                int x = 0;
                int y = 0;
                int years =0;
                char[][] output;
                if (part2.equals("W") || part2.equals("E")) {

                    y = 3;
                    x = 4;
                    for(int i = 1;i<part11;i++){
                        x++;
                        y++;
                    }
                    output = new char[x][y];
                    for (int i=0;i<x;i++){
                        for (int j=0;j<y;j++){
                            output[i][j] = '*';
                        }
                    }
                }else{
                    y = 4;
                    x = 3;
                    for(int i = 1;i<part11;i++){
                        x++;
                        y++;
                    }
                    output = new char[x][y];
                    for (int i=0;i<x;i++){
                        for (int j=0;j<y;j++){
                            output[i][j] = '*';
                        }
                    }
                }
                int counter = -1;
                switch(part2){
                    case "N":
                        Character start= 'N';
                        int n_x =0;
                        int n_y = y-2;
                        int n_x1 = x-1;
                        int e_x = x-2;
                        int e_y = y-2;
                        int e_y1 = 0;
                        int s_x = x-2;
                        int s_y = 1;
                        int s_x1 = 0;
                        int w_x = 1;
                        int w_y = 1;
                        int w_y1 = y-3;
                        output[n_x][n_y] = ' ';
                        while(counter != part11){
                            if (start == 'N'){
                                int i = n_x;
                                while (i<n_x1) {
                                    output[i][n_y]=' ';
                                    i++;
                                }
                                n_x+=2;
                                n_y-=2;
                                n_x1-=2;
                                start = 'E';
                            } else if (start =='E') {
                                int i = e_y;
                                while (i > e_y1) {
                                    output[e_x][i]=' ';
                                    i--;
                                }
                                e_y-=2;
                                e_x-=2;
                                e_y1+=2;
                                start = 'S';
                            } else if (start =='S') {
                                int i = s_x;
                                while (i>s_x1) {
                                    output[i][s_y]=' ';
                                    i--;
                                }
                                s_x-=2;
                                s_y+=2;
                                s_x1+=2;
                                start = 'W';
                            } else {
                                int i = w_y;
                                while (i < w_y1) {
                                    output[w_x][i]=' ';
                                    i++;
                                }
                                w_y1-=2;
                                w_x+=2;
                                w_y+=2;
                                start = 'N';
                            }
                            counter++;
                        }
                        break;
                    case "S":
                        start = 'S';
                        s_y = 1;
                        s_x = x-1;
                        s_x1 = 0;
                        w_x = 1;
                        w_y = 1;
                        w_y1 = y-1;
                        n_y = y-1;
                        n_x = 1;
                        n_x1 = x-1;
                        e_y = y-2;
                        e_x = x-2;
                        e_y1 = 2;
                        output[s_x][s_y] = 'X';
                        while (counter != part11) {
                            if (start =='S') {
                                int i =s_x;
                                while (i > s_x1) {
                                    output[i][s_y] = ' ';
                                    i--;
                                }
                                s_x1+=2;
                                s_x -=2;
                                s_y +=2;
                                start = 'W';
                            } else if (start =='W') {
                                int i = w_y;
                                while (i<w_y1) {
                                    output[w_x][i]=' ';
                                    i++;
                                }
                                w_y+=2;
                                w_x+=2;
                                w_y1-=2;
                                start ='N';
                            } else if (start =='N') {
                                int i = n_x;
                                while (i<n_x1) {
                                    output[i][n_y-1] = ' ';
                                    i++;
                                }
                                n_y-=2;
                                n_x+=2;
                                n_x1-=2;
                                start ='E';
                            } else{
                                int i = e_y;
                                while(i>e_y1){
                                    output[e_x][i]=' ';
                                    i--;
                                }
                                e_y-=2;
                                e_x-=2;
                                e_y1+=2;
                                start = 'S';
                            }
                            counter++;
                        }
                        break;
                    case "E":
                        start = 'E';
                        s_x = x-2;
                        s_y = 1;
                        s_x1 = 0;
                        e_x = x-2;
                        e_y = y -1;
                        int e_x1 = 0;
                        w_y = 1;
                        w_x = 1;
                        w_y1 = y;
                        n_x1 = 1;
                        int n_y1 = y-2;
                        output[e_x][e_y] = 'X';
                        while(counter != part11){
                            if(start == 'E'){
                                int i = e_y;
                                while(i>e_x1){
                                    output[e_x][i] = ' ';
                                    i--;
                                }
                                e_x1 +=2;
                                e_y -=2;
                                e_x -=2;
                                start = 'S';
                            } else if (start =='S') {
                                int i = s_x;
                                while (i>s_x1){
                                    output[i][s_y] = ' ';
                                    i--;
                                }
                                s_x1 += 2;
                                s_y +=2;
                                s_x -=2;
                                start = 'W';
                            } else if (start == 'W') {
                                int i = w_y;
                                while (i<w_y1-1){
                                    output[w_x][i] = ' ';
                                    i++;
                                }
                                w_y +=2;
                                w_x +=2;
                                w_y1 -=2;
                                start = 'N';
                            } else{
                                int i =n_x1;
                                while (i<n_y1){
                                    output[i][n_y1] = ' ';
                                    i++;
                                }
                                n_x1 +=2;
                                n_y1 -=2;
                                start = 'E';
                            }
                            counter++;
                        }
                        break;
                    case "W":
                        start= 'W';
                        w_x = 1;
                        w_y = 0;
                        w_y1 = y;
                        n_x = 1;
                        n_x1 = x-1;
                        n_y = 0;
                        e_x = 0;
                        e_x1 = x -2;
                        s_x = x-2;
                        int s_x11 = 1;
                        s_y = 1;
                        output[w_x][w_y] = ' ';
                        while(counter != part11){
                            if (start == 'W'){
                                int i = w_y;
                                while (i<w_y1-1){
                                    output[w_x][i] = ' ';
                                    i++;
                                }
                                w_y1 -= 2;
                                w_y += 2;
                                w_x += 2;
                                start = 'N';
                            } else if (start =='N') {
                                n_y = w_y1;
                                int i = n_x;
                                while (i<n_x1){
                                    output[i][n_y] = ' ';
                                    i++;
                                }
                                n_x += 2;
                                n_x1 -= 2;
                                start = 'E';
                            } else if (start =='E') {
                                int i = n_y;
                                while(i>e_x){
                                    output[e_x1][i] = ' ';
                                    i--;
                                }
                                e_x+=2;
                                e_x1-=2;
                                start = 'S';
                            } else{
                                int i = s_x;
                                while (i>s_x11+1){
                                    output[i][s_y] = ' ';
                                    i--;
                                }
                                s_x11 += 2;
                                s_y +=2;
                                s_x -= 2;
                                start = 'W';
                            }
                            counter++;
                        }
                        break;
                }
                for(int i = 0;i<output.length;i++){
                    for(int j=0;j<output[i].length;j++){
                        if(output[i][j]==' '){
                            years++;
                        }
                    }
                }
                System.out.println();
                for (int i=0;i<x;i++){
                    for (int j=0;j<y;j++){
                        System.out.print(output[i][j]);
                    }
                    System.out.println();
                }
                System.out.println(years);
            }
            else{
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
        uproszczenia(args);
    }
}
