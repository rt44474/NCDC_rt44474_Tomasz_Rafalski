import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

public class DeRevolutionibus {
    public static int count_decimals(double d){
        String tmp = Double.toString(Math.abs(d));
        int integers = tmp.indexOf('.');
        int decimals = tmp.length() -  integers -1;
        return decimals;
    }
    public static int revolutionibus(String[] args){
        //Sprawdzanie czy to double
        if(args.length==1){
            try{
                Double.parseDouble(args[0]);
            }catch(NumberFormatException e){
                System.out.println("klops");
                return 0;
            }
            //double dzien = Math.round(Double.parseDouble(args[0])*100.0)/100.0;
            double dzien = Double.parseDouble(args[0]);
            int check = count_decimals(dzien);
            int check1 = 0;
            //Sprawdzanie czy jest dobre zaokraglenie
            if(check>2){
                System.out.println("klops");
                return 0;
            }
            int lines = 0;
            String[] temp;
            List<String> full = new ArrayList<String>();
            //Lista nazw planet
            List<String> nazwy= new ArrayList<String>();

            //String[] nazwy;
            //Lista czasów obiegu
            List<Double> czas=new ArrayList<Double>();
            //Double[] czas;
            //Lista promieni planet
            List<Double> promien=new ArrayList<Double>();
            //Double[] promien;
            //Sprawdzanie czy dzien jest wiekszy niz 1
            if (dzien >= 0) {
                Scanner input = new Scanner(System.in);
                while (input.hasNextLine()) {
                    String tmp;
                    tmp = input.nextLine();

                    //Dzielenie Stringa na czesci
                    temp = tmp.split(",");
                    nazwy.add(temp[0]);
                    //Liczenie miejsc po przecinku
                    check = count_decimals(Double.parseDouble(temp[1]));
                    czas.add(Double.parseDouble(temp[1]));
                    //Usuwanie \n z liczby
                    temp[2]=temp[2].replace("\\n","");
                    check1 = count_decimals(Double.parseDouble(temp[2]));
                    promien.add(Double.parseDouble(temp[2]));
                    //sprawdzanie zaokraglenia, czy czas nie jest wiekszy niz wskazany lub mniejszy
                    //I czy promien nie jest za duzy albo za maly
                    if ((check >2 || check1 >2)||(czas.get(czas.size()-1)>200000||czas.get(czas.size()-1)<0)||(promien.get(promien.size()-1)>100000||promien.get(promien.size()-1)<0)) {
                        System.out.println("klops");
                        return 0;
                    }
                    //promien.add(Math.round(Double.parseDouble(temp[2])*100.0)/100.0);
                    //Sprawdzanie czy teskt jest w UTF-8
                    byte[] isUTF = null;
                    try{
                        isUTF=nazwy.get(nazwy.size()-1).getBytes("UTF-8");
                    }
                    catch(UnsupportedEncodingException e){
                        System.out.println("klops");
                        return 0;
                    }
                    lines++;
                }
                check = 0;
                for (Double i : czas){
                    if (i ==0) {
                        check++;
                    }
                }
                Set<Double> set = new HashSet<Double>(promien);
                //Sprawdzanie czy ilosc wierszy w pliku jest dobra
                if ((lines < 2 || lines > 100000)||(check!=1)||(set.size()<promien.size())) {
                    System.out.println("klops");
                    return 0;
                }
                //sortowanie wzgledem promieniami od najwiekszego do centrum
                int idx = 0;
                List<Double> copy_p=new ArrayList<Double>();
                List<Double> copy_c=new ArrayList<Double>();
                List<String> copy_n= new ArrayList<String>();
                copy_p.addAll(promien);
                Collections.sort(copy_p,Collections.reverseOrder());
                for(int i =0;i<promien.size();i++){
                    idx = promien.indexOf(copy_p.get(i));
                    copy_c.add(czas.get(idx));
                    copy_n.add(nazwy.get(idx));
                }
                //System.out.println(copy_n);
                //System.out.println(copy_c);
                //System.out.println(copy_p);
                if (dzien == 0) {
                    for(int i =0;i<copy_n.size();i++){
                        if (i == 0){
                            System.out.print(copy_n.get(i));
                        }
                        else{
                            System.out.print(", "+copy_n.get(i));
                        }

                    }
                }
                else {



                }




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
        revolutionibus(args);
    }
}
