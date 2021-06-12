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
    public static double[] calculate_X(double radius, double rate,double time,double x0){
        //rate = time/rate;
        double x = radius*Math.cos(2*time/rate*Math.PI + Math.PI)+x0;
        double y = radius*Math.sin(2*time/rate*Math.PI + Math.PI)+x0;
        double[] xy={x,y};

        return xy;
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
                String tmp;

                while (input.hasNextLine()) {
                    tmp = input.nextLine();
                    //Dzielenie Stringa na czesci
                    temp = tmp.split(",");
                    if(temp.length!=3){
                        System.out.println("klops");
                        return 0;
                    }
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
                List<Double> list_x=new ArrayList<Double>();
                List<Double> list_y=new ArrayList<Double>();
                copy_p.addAll(promien);
                Collections.sort(copy_p,Collections.reverseOrder());
                for(int i =0;i<promien.size();i++){
                    idx = promien.indexOf(copy_p.get(i));
                    copy_c.add(czas.get(idx));
                    copy_n.add(nazwy.get(idx));
                }
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
                    double[] x;
                    for (int i= 0;i<copy_n.size();i++){
                        if (i == copy_n.size()-1){
                            //System.out.println(copy_c.get(i));
                            list_x.add(0.0);
                            list_y.add(0.0);
                            break;
                        }
                        x = calculate_X(copy_p.get(i),copy_c.get(i),dzien,0.0);
                        list_x.add(x[0]);
                        list_y.add(x[1]);
                        //System.out.println("Nazwa: "+copy_n.get(i)+" x: "+ x[0]+" y: "+x[1]);
                    }
                    //Sortowanie po wspolrzednych x
                    List<Double> copy_x=new ArrayList<Double>();
                    List<Double> copy_y=new ArrayList<Double>();
                    copy_x.addAll(list_x);
                    Collections.sort(copy_x);
                    promien.clear();
                    nazwy.clear();
                    czas.clear();
                    for (int i =0;i<copy_n.size();i++){
                        idx = list_x.indexOf(copy_x.get(i));
                        copy_y.add(list_y.get(idx));
                        promien.add(copy_p.get(idx));
                        nazwy.add(copy_n.get(idx));
                        czas.add(copy_c.get(idx));
                    }
                    List<String> output = new ArrayList<String>();
                    output.addAll(nazwy);
                    for (int i =0;i<copy_c.size();i++){
                        double tmpx1 = Math.round(copy_x.get(i)*100.0)/100.0;
                        double tmpy1 = copy_y.get(i);
                        for (int j=0;j<copy_c.size();j++){
                            if (i == j){
                                continue;
                            }
                            double tmpx2 = Math.round(copy_x.get(j)*100.0)/100.0;
                            double tmpy2 = copy_y.get(j);
                            if (tmpx1 == tmpx2){
                                if (tmpy1>tmpy2) {
                                    if((output.contains(nazwy.get(i)))){
                                        output.remove(nazwy.get(i));
                                    }
                                }
                                else if(tmpy2>tmpy1){
                                    if((output.contains(nazwy.get(j)))){
                                        output.remove(nazwy.get(j));
                                    }
                                }
                            }
                        }
                    }
                    for(int i =0;i<output.size();i++){
                        if (i == output.size()-1){
                            System.out.print(output.get(i));
                        }
                        else{
                            System.out.print(output.get(i)+", ");
                        }
                    }
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