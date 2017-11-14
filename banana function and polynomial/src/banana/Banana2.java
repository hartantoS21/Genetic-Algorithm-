package banana;
import java.util.Arrays;
import java.util.Scanner;
public class Banana2 {
    double a, b, d,tAntartitik,desimal1[],decode1[],x[],y[],fx[];
    int kromosom1[][],m;
    String kr1[];
    public Banana2(){
    }
    public Banana2(double d,double b ,double a){
        tAntartitik = Math.pow(10, (-d));
        System.out.println("jarak antar titik pada domain");
        System.out.println(tAntartitik);
        System.out.println("jumlah titik pada domain");
        int e=(int) ((b - a) * Math.pow(10, (d)));
        System.out.println(e);
        System.out.println("jumlah bit pada kromosom adalah ");
        System.out.println((int)Math.ceil(Math.log(e+1)/Math.log(2)));
        m = (int) Math.ceil((Math.log((b - a) * Math.pow(10, (d))) / Math.log(2))+1);
        kromosom1=new int[30][m];
        kr1=new String[30];
        decode1=new double[30];
        desimal1=new double[30];
        x=new double[30];
        y=new double[30];
        fx=new double[30];
        for (int j = 0; j < kr1.length; j++) {
            kr1[j]="";
        }
    }
        public void generateKromosom(){
        for (int j=0;j<30;j++) {
            for (int i = 0; i < kr1.length; i++) {
                int gene1 = (int) Math.round(Math.random());
                kromosom1[j][i] = gene1;
                kr1[i]=kr1[i]+gene1;
            }
            System.out.println(Arrays.toString(kr1));
        }
        }
         
        public void desimal(){
        for (int j = 0; j < kromosom1.length; j++) {
            desimal1[j]= Integer.parseInt(kr1[j],2);
            System.out.println("Desimalnya  : "+(int)desimal1[j]);
            }
        }
       public void decode() {
       for (int j = 0; j < kromosom1.length; j++) {
           System.out.println("Decode gen "+j);
           decode1[j]=a+desimal1[j]*((b-a)/(Math.pow(2, m)-1));
           System.out.println("Hasil Decode ");
           double temp = Math.pow(10, d);
           x[j] = (double) Math.round(decode1[j] * temp) / temp;
           System.out.println(x[j]);
       }
       }
       public void calcFitness(Banana2 a,Banana2 b){
        for (int j = 0; j < kromosom1.length; j++) {
        double f=100*Math.pow((decode1[j]-Math.pow(decode1[j], 2)),2)+(1-Math.pow(decode1[j], 2));
        System.out.println("fungsi banana dengan x = "+x[j]+" y = "+y[j]);
        double temp = Math.pow(10, d);
        fx[j] = (double) Math.round(f * temp) / temp;
        System.out.println(fx[j]);
        }
    }
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("-----banana function-----");
        System.out.print("masukan batas atas x = ");
        double ax = in.nextDouble();
        System.out.print("masukan batas bawah x = ");
        double bx = in.nextDouble();
        System.out.print("masukan batas atas y = ");
        double ay = in.nextDouble();
        System.out.print("masukan batas bawah y = ");
        double by = in.nextDouble();
        System.out.println("ketepatan angka dibelakang koma = ");
        double d = in.nextDouble();
        Banana2 x=new Banana2(d, ax, bx);
        Banana2 y=new Banana2(d,ay,by);
        System.out.println("Inisialisasi x");
        x.generateKromosom();
        x.desimal();
        x.decode();
        System.out.println("Inisialisasi y");
        y.generateKromosom();
        y.desimal();
        y.decode();
    }
    
}
