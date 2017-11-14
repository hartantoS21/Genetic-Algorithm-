package banana;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public final class NewMain {
    int indiv = 0,inkr = 0;
    static int gen=0,gen1=0;
    int kromosom[][],kromosom2[][],ks[],cr[],m,n;
    double pm=0.01,pc=0.25,
           bax,bbx,bay,bby,d,tAntartitik,
           desimalx[],desimaly[],decodex[],decodey[],
           x[],y[],fx[],
           total,prob[],tot[],r[],mut[];
    String kr[],kr1[],kr2[];
    public NewMain(){
    }
    //inisialisasi awal
    public NewMain(double a,double b,double g,double h, double d){
        bax=a;bbx=b;bay=g;bby=h;
        tAntartitik = Math.pow(10, (-d));
        System.out.println("jarak antar titik pada domain pada x dan y ");
        System.out.println(tAntartitik);
        System.out.println("jumlah titik pada domain x");
        int e=(int) ((b - a) * Math.pow(10, (d)));
        System.out.println(e);
        System.out.println("jumlah bit pada kromosom x adalah ");
        System.out.println((int)Math.ceil(Math.log(e+1)/Math.log(2)));
        m = (int) Math.ceil((Math.log((b - a) * Math.pow(10, (d))+1) / Math.log(2)));
        System.out.println("jumlah titik pada domain y");
        int ee=(int) ((h - g) * Math.pow(10, (d)));
        System.out.println(ee);
        System.out.println("jumlah bit pada kromosom y adalah ");
        System.out.println((int)Math.ceil(Math.log(ee+1)/Math.log(2)));
        n = (int) Math.ceil((Math.log((h - g) * Math.pow(10, (d))+1) / Math.log(2)));
        System.out.println(m+n);
        kromosom=new int[30][m+n];kromosom2=new int[30][m+n];
        ks=new int[30];kr=new String[30];kr1=new String[30];
        kr2=new String[30];desimalx=new double[30];desimaly=new double[30];
        decodex=new double[30];decodey=new double[30];x=new double[30];
        y=new double[30];fx=new double[30];prob=new double[30];
        tot=new double[30];r=new double[30];
        for (int j = 0; j < kr.length; j++) {
            kr[j]="";
            kr1[j]="";
            kr2[j]="";
        }
        generateKromosom();
        
    }
    //memecah kromosom x dan y
    public void pecahKromosom(){
    String str="",str2="",str3="";
        for (int j = 0; j <30; j++){
            for(int i=0;i<m+n;i++){
                str=str+kromosom[j][i];
                if(i<m)
                    str2=str2+kromosom[j][i];
                else
                    str3=str3+kromosom[j][i];
            }System.out.println(str);
            //System.out.println(str2);System.out.println(str);
            kr[j]=str;
            kr1[j]=str2;
            kr2[j]=str3;
            str="";
            str2="";
            str3="";
        }
    }
    //generate kromosom
    public void generateKromosom(){
        for (int j = 0; j <30; j++) {
            for(int i=0;i<m+n;i++){
                int gene1 = (int) Math.round(Math.random());
                kromosom[j][i] = gene1;
            }
        }
                System.out.println("generate Kromosom selesai");
        pecahKromosom();
        desimal();

        //System.out.println(Arrays.deepToString(kromosom));
        //System.out.println(Arrays.toString(kr));
        //System.out.println(Arrays.toString(kr1));
        //System.out.println(Arrays.toString(kr2));
    }
    //convert kromosom dari biner ke desimal
    public void desimal(){
        for (int j = 0; j < kr.length; j++) {
            desimalx[j]= Integer.parseInt(kr1[j],2);
            //System.out.println("Desimalnya  x : "+(int)desimalx[j]);                
            desimaly[j]= Integer.parseInt(kr2[j],2);
            //System.out.println("Desimalnya  y : "+(int)desimaly[j]);    
        }decode();
    }
    //menghitung probabilitasnya
    public void decode() {
       for (int j = 0; j <kr.length; j++) {
           //System.out.println("Decode gen "+j);
           decodex[j]=bax+desimalx[j]*((bbx-bax)/(Math.pow(2, m)-1));
           decodey[j]=bay+desimaly[j]*((bby-bay)/(Math.pow(2, m)-1));

           x[j] = decodex[j];           
           y[j] = decodey[j];
           //System.out.println("decode x = "+x[j]);
           //System.out.println("decode y = "+y[j]);
       }
       System.out.println("Decode selesai");
       calcFitness();
    }
    //menghitung banana function
    public void calcFitness(){
        for (int j = 0; j < kr.length; j++) {
        double f=100*Math.pow((decodey[j]-Math.pow(decodex[j], 2)),2)+(1-Math.pow(decodex[j], 2));
        //System.out.println("fungsi banana dengan x = "+x[j]+" y = "+y[j]);
        double temp = Math.pow(10, d);
        fx[j] = (double) Math.round(f * temp) / temp;
        total=total+fx[j];
        //System.out.println(fx[j]);
        }
        //System.out.println("total fx");
        //System.out.println(total);
        //System.out.println("probabilitas fx");
        double tl=0;
            /*double a;
            double temp = Math.pow(10, d);
            a=fx[j]/total;
            prob[j] = (double) Math.round(a * temp) / temp;
            tl=tl+prob[j];
            tot[j]=tl;
            System.out.println(prob[j]);
            System.out.println("total probabilitas = "+tot[j]);*/
        for (int j = 0; j < kr.length; j++) {
            prob[j] = fx[j]/total;
            tl=tl+prob[j];
            tot[j]=tl;
        //    System.out.println(prob[j]);
        //    System.out.println("total probabilitas = "+tot[j]);
        }System.out.println("fitness sudah dihitung");
        //System.out.println(Arrays.toString(tot));
        seleksi();
    }
    //seleksi kromosom
    public void seleksi(){
        if(gen<=gen1){
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
        for(int i=0;i<tot.length;i++){
            System.arraycopy(kromosom[ks[i]], 0, kromosom2[i], 0, m+n);    
        }
        for(int i=0;i<tot.length;i++){
            System.arraycopy(kromosom2[i], 0, kromosom[i], 0, m+n);
        }
        System.out.println("setelah seleksi");
        System.out.println(Arrays.deepToString(kromosom));
        //System.out.println(Arrays.toString(ks));
        pecahKromosom();
        crossover();
        }System.out.println("selesai");
        System.out.println(Arrays.toString(fx));
        System.out.println(Arrays.toString(tot));
    }
    //crossover kromosom
    public void crossover(){
        int a=0;
        for(int i=0;i<r.length;i++){
            r[i]=Math.random();
            if(r[i]<pc)
                a++;
        }
        if(a%2==0)
            cr=new int[a];
        else
            cr=new int[a-1];
        System.out.println("banyak indiv cros"+a);
        a=0;
        int z=0;
        for(int i=0;i<r.length;i++){
            r[i]=Math.random();
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
            int c=rnd.nextInt(m+n);
            for(int i=c;i<m+n;i++){
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
    public void mutasi(){
        int pbit=30*(m+n);
        System.out.println("panjang bit = "+pbit);
        mut=new double[pbit];
        for(int i=1;i<mut.length+1;i++){
            mut[i-1]=Math.random();
            if(mut[i-1]<pm){
                if(i<m+n){
                    indiv=0;
                    inkr=i-1;
                    System.out.println("i 1="+i);
                }
                else if(i%(m+n)==0){
                    System.out.println("i 2= "+i);
                    inkr=m+n-1;
                    System.out.println("index kromosom yang mutasi "+inkr);
                    indiv=(i/(m+n))-1;
                    System.out.println("index indiv yang mutasi "+indiv);
                }else{
                    System.out.println("i 3="+i+" m+n "+(m+n));
                    int c=i%(m+n);
                    System.out.println(c);
                    int d=i/(m+n);
                    System.out.println(d);
                    inkr=c-1;
                    indiv=d-1;
                    System.out.println("index kromosom yang mutasi "+inkr);
                    System.out.println("index indiv yang mutasi "+indiv);
                }
                if(kromosom[indiv][inkr]==0)
                    kromosom[indiv][inkr]=1;
                else
                    kromosom[indiv][inkr]=0;
            }
        }    
        gen1++;
        pecahKromosom();
        total=0;
        desimal();
        calcFitness();
        for(int i=0;i<prob.length;i++){
            prob[i]=0;
            tot[i]=0;
        }
        seleksi();

        
    }
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
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
        gen=in.nextInt();
        NewMain a=new NewMain(ax,bx,ay,by,d);
        
    }
}