package polinom;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class NewMain {
    static Scanner in = new Scanner(System.in);
    static  double kp[];         
    static double x[],desimal[],tAntartitik,d,ba,bb,
                  decode[],f[],r[],tot[],pc,pm,mut[],
                  prob[],bestf[],bestx[];
    static int kromosom[][],kromosom2[][],ks[],cr[];
    static int generasi=100;static int gensekarang=1,i=0;
    static int m;
    static String kr[];
    public NewMain(int a,int bb,int ba,int e){
        kp=new double[a];
        NewMain.ba=ba;
        NewMain.bb=bb;
        d=e;
        String s="";
        for(int i =0; i<kp.length;i++){
            System.out.println("koefisien x derajat "+i);
            kp[i]=in.nextInt();
            if(kp[i]>=0)
            if(i==0)
                s=s+(int)kp[i]+" + ";
            else if(i<kp.length-1)
                s=s+(int)kp[i]+"x^"+(int)i+" + ";
            else
                s=s+(int)kp[i]+"x^"+(int)i;
            else
                if(i==0)
                s=s+(int)kp[i]+" + ";
            else if(i<kp.length-1)
                s=s+"( "+(int)kp[i]+"x^"+(int)i+") + ";
            else
                s=s+"( "+(int)kp[i]+"x^"+(int)i+")";}    
        System.out.println("fungsi = "+s);
        tAntartitik = Math.pow(10, (-d));
        System.out.println("jarak antar titik pada domain pada x");
        System.out.println(tAntartitik);
        System.out.println("jumlah titik pada domain x");
        System.out.println("jumlah titik pada domain x");        
        int ee = (int) ((double)(bb - ba) * Math.pow(10, (d)));
        System.out.println(ee);
        System.out.println("jumlah bit pada kromosom x adalah ");
        m = (int) Math.ceil((Math.log((bb - ba) * Math.pow(10, (d))+1) / Math.log(2)));
        System.out.println(m);
        System.out.println("Masukan probabilitas crossover = ");
        pc=in.nextDouble();
        System.out.println("Masukan probabilitas mutasi = ");
        pm=in.nextDouble();
        System.out.println("Masukan max generasi = ");
        generasi=in.nextInt();
        bestf=new double[generasi+1];bestx=new double[generasi+1];
        kromosom=new int[30][m];kromosom2=new int[30][m];
        ks=new int[30];kr=new String[30];desimal=new double[30];
        decode=new double[30];x=new double[30];
        f=new double[30];prob=new double[30];
        tot=new double[30];r=new double[30];               
        for (int j = 0; j < kr.length; j++) {
            kr[j]="";
        }generateKromosom();
        
    }   
    public static void pecahKromosom(){
        String str="";
        for (int j = 0; j <30; j++){
            for(int i=0;i<m;i++){
                str=str+kromosom[j][i];
            }System.out.println(str);
            //System.out.println(str2);System.out.println(str);
            kr[j]=str;
            System.out.println(kr[j]);
            str="";
        }
    }
    public static void desimal(){
        for (int j = 0; j < kr.length; j++) {
            desimal[j]= Integer.parseInt(kr[j],2);
            System.out.println("Desimalnya  x : "+(int)desimal[j]);                
        }decode();
    }
    public static void generateKromosom(){
        for (int j = 0; j <30; j++) {
            for(int i=0;i<m;i++){
                int gene1 = (int) Math.round(Math.random());

                kromosom[j][i] = gene1;
            }
        }pecahKromosom();
        desimal();

    }
    public static void decode() {
       for (int j = 0; j <kr.length; j++) {
           System.out.println("Decode gen "+j);
           decode[j]=ba+desimal[j]*((bb-ba)/(Math.pow(2, m)-1));
          System.out.println("Hasil Decode ");
           x[j] = decode[j];           
           System.out.println("decode x = "+x[j]);
       }calcFitness();
    }
    public static void best(double a[]){
        double max=-99;int u=0;
        for(int i=0;i<a.length;i++)
            if(a[i]>=max){
                max=a[i];
                u=i;
            }
        bestf[gensekarang-1]=f[u];
        bestx[gensekarang-1]=x[u];
    }
     public static void calcFitness(){
        double total=0,t=0;
        double xi=0;
        for(int j=0;j<kr.length;j++){
            xi=x[j];
            for(int k=0;k<kp.length;k++)
                total=total+(kp[k]+Math.pow(xi, k));
            f[j]=total;
            System.out.println("hasil poli "+f[j]);
            t=t+total;
            total=0;
        }
        best(f);
        double tl=0;
        for (int j = 0; j < kr.length; j++) {
            prob[j] = f[j]/t;
            tl=tl+prob[j];
            tot[j]=tl;
            System.out.println(prob[j]);
            System.out.println("total probabilitas = "+tot[j]);}
        t=0;
        seleksi();
     }
     
      //seleksi kromosom
    public static void seleksi(){
        if(gensekarang<=generasi){ 
        for(int i=0;i<r.length;i++)
            r[i]=Math.random();
        System.out.println(Arrays.toString(r));
        //System.out.println(Arrays.toString(r));
        System.out.println("r = "+tot.length);
        for(int i=0;i<tot.length;i++){
            for(int j=0;j<=tot.length-1;j++)
            if(r[i]>=tot[j]&&r[i]<=tot[j+1])
                ks[i]=j+1;
            else if (r[i]<=tot[0])
                ks[i]=0;
        
        System.out.println("seleksi "+ks[i]);}
        System.out.println("sebelum seleksi");
        System.out.println(Arrays.deepToString(kromosom));
        for(int ii=0;ii<tot.length;ii++){
            System.arraycopy(kromosom[(int)ks[ii]], 0, kromosom2[ii], 0, m); 
        }
        for(int i=0;i<tot.length;i++){
            System.arraycopy(kromosom2[i], 0, kromosom[i], 0, m);
        }
        System.out.println("setelah seleksi");
        System.out.println(Arrays.deepToString(kromosom));
        //System.out.println(Arrays.toString(ks));
        pecahKromosom();
        crossover();
        }else{
            System.out.println("best fitness, x, dan y");
            for(int i=0;i<bestf.length-1;i++){
                System.out.println("Best fitnes dan x Generasi"+(i+1));
                System.out.println(bestf[i+1]);
                System.out.println(bestx[i+1]);
            }
        }
    }
     public static void crossover(){
        int a=0;
        
        //random r
        for(int i=0;i<r.length;i++){
            r[i]=Math.random();
            if(r[i]<pc)
                a++;
        }
        if(a%2==0)
            cr=new int[a];
        else
            cr=new int[a-1];
        System.out.println("banyak indiv cros "+a);
         a=0;
        int z=0;
        for(int i=0;i<r.length;i++){
            if(r[i]<pc&&a<cr.length){
                cr[z]=i;
                z++;
                System.out.println("index "+i);
                a++;
            }
        }
        Random rnd= new Random();
        int de;
        for (int j = 0; j <a; j=j+2) {
            int c=rnd.nextInt(m-1);
            for(int i=c;i<m;i++){
                de=kromosom[cr[j]][i];
                kromosom[cr[j]][i] = kromosom[cr[j+1]][i];
                kromosom[cr[j+1]][i] = de;
            }
            System.out.println("hasil crossover "+cr[j]+" dengan "+cr[j+1]);
            System.out.println(Arrays.toString(kromosom[cr[j]]));
            System.out.println(Arrays.toString(kromosom[cr[j+1]]));
        }
        pecahKromosom();
        mutasi();
     }    
    //mutasi
    public static void mutasi(){
        int pbit=30*(m);
        System.out.println("panjang bit = "+pbit);
        mut=new double[pbit];
        int indiv = 0,inkr = 0;
        for(int i=1;i<mut.length+1;i++){
            mut[i-1]=Math.random();
            if(mut[i-1]<pm){
                if(i<m){
                    inkr=i-1;
                    indiv=0;
                    System.out.println("i 1 ="+i);
                }
               
                else if(i%(m)!=0){
                    inkr=i%(m)-1;
                    indiv=i/(m);
                    System.out.println("i 3="+i);
                    System.out.println("index kromosom yang mutasi "+inkr);
                    System.out.println("index indiv yang mutasi "+indiv);
                }
                else{
                    System.out.println("i 4="+i);
                    inkr=m-1;
                    System.out.println("index kromosom yang mutasi "+inkr);
                    indiv=i/(m)-1;
                    System.out.println("index indiv yang mutasi "+indiv);
                }
                if(kromosom[indiv][inkr]==0)
                    kromosom[indiv][inkr]=1;
                else
                    kromosom[indiv][inkr]=0;
            }
        } 
        i++;
        gensekarang++;
        pecahKromosom();
        desimal();
        calcFitness();
        for(int i=0; i<tot.length; i++){
            tot[i]=0;
            f[i]=0;
        }
        for(int i=0; i<prob.length; i++){
            prob[i]=0;
        }
    }
     
    public static void main(String[] args) {
        
        
        System.out.print("polinomial derajat = ");int c =in.nextInt();
        System.out.print("Masukan batas atas = ");int ba=in.nextInt();
        System.out.print("Masukan batas batas = ");int bb=in.nextInt();
        System.out.print("ketepatan angka = ");int f=in.nextInt();
        NewMain a= new NewMain((c+1),ba,bb,f);
        }
    }