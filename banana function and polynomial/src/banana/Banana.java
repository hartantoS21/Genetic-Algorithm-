package banana;
import java.util.Scanner;
public class Banana {
    static double a, b, d,tAntartitik,desimal1[],desimal2[],decode1[],decode2[],x[],y[],fx[];
    static int kromosom1[][],kromosom2[][],m;
    static String kr1[],kr2[];
    public void evaluate() {
        
    }
    public static void generateKromosom(){
        for (int j = 0; j < kromosom1.length; j++) {
        for (int i = 0; i < kromosom1.length; i++) {
            int gene1 = (int) Math.round(Math.random());
            kromosom1[j][i] = gene1;
            int gene2 = (int) Math.round(Math.random());
            kromosom2[j][i] = gene2;
            kr1[i]=kr1[i]+String.valueOf(gene1);
            kr2[i]=kr2[i]+String.valueOf(gene1);
            
        }
        System.out.println("Kromosom x gen "+j);
            System.out.println(kr1[j]);
            System.out.println("Kromosom y gen "+j);
            System.out.println(kr2[j]);}
    }
    public static void desimal(){
    for (int j = 0; j < kromosom1.length; j++) {
        desimal1[j]= Integer.parseInt(kr1[j],2);
        System.out.println("Desimalnya x : "+(int)desimal1[j]);
        desimal2[j]= Integer.parseInt(kr2[j],2);
        System.out.println("Desimalnya y : "+(int)desimal2[j]);
    }
    }
    public static void decode() {
       for (int j = 0; j < kromosom1.length; j++) {
           System.out.println("Decode gen "+j);
       decode1[j]=a+desimal1[j]*((b-a)/(Math.pow(2, m)-1));
       System.out.println("Hasil Decode x");
       double temp = Math.pow(10, d);
       x[j] = (double) Math.round(decode1[j] * temp) / temp;
       System.out.println(x[j]);
       decode2[j]=a+desimal2[j]*((b-a)/(Math.pow(2, m)-1));
       System.out.println("Hasil Decode y");
       y[j] = (double) Math.round(decode2[j] * temp) / temp;
       System.out.println(y[j]);
       }
    }
    public static void calcFitness(){
        for (int j = 0; j < kromosom1.length; j++) {
        double f=100*Math.pow((decode2[j]-Math.pow(decode1[j], 2)),2)+(1-Math.pow(decode1[j], 2));
        System.out.println("fungsi banana dengan x = "+x[j]+" y = "+y[j]);
        double temp = Math.pow(10, d);
        fx[j] = (double) Math.round(f * temp) / temp;
        System.out.println(fx[j]);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("-----banana function-----");
        System.out.print("masukan a = ");
        a = in.nextDouble();
        System.out.print("masukan b = ");
        b = in.nextDouble();
        System.out.println("ketepatan angka dibelakang koma = ");
        d = in.nextDouble();
        tAntartitik = Math.pow(10, (-d));
        System.out.println("jarak antar titik pada domain");
        System.out.println(tAntartitik);
        System.out.println("jumlah titik pada domain");
        int e=(int) ((b - a) * Math.pow(10, (d)));
        System.out.println(e);
        System.out.println("jumlah bit pada kromosom adalah ");
        System.out.println((int)Math.ceil(Math.log(e+1)/Math.log(2)));
        m = (int) Math.ceil((Math.log((b - a) * Math.pow(10, (d))) / Math.log(2))+1);
        kromosom1=new int[m][m];
        kromosom2=new int[m][m];
        kr1=new String[m];
        kr2=new String[m];
        decode1=new double[m];
        decode2=new double[m];
        desimal1=new double[m];
        desimal2=new double[m];
        x=new double[m];
        y=new double[m];
        fx=new double[m];
        for (int j = 0; j < kromosom1.length; j++) {
            kr1[j]="";
            kr2[j]="";
        }
        generateKromosom();
        desimal();
        decode();
        calcFitness();
        //DecimalFormat df=new DecimalFormat(koma);
        //double c = 11.2345678;
        //double temp = Math.pow(10, d);
        //double y = (double) Math.round(c * temp) / temp;
        //System.out.println(y);
        //System.out.println(Double.valueOf(df.format(c)));
    }
}