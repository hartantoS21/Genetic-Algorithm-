package banana.newpackage;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.Random;

public class NewJFrame extends javax.swing.JFrame {
    int kromosom[][],kromosom2[][],ks[],cr[],m,n;
    static double pm,pc;
    static int generasi=100; int gensekarang=1,i=0;
    double bax,bbx,bay,bby,d,tAntartitik,
           desimalx[],desimaly[],decodex[],
           decodey[],x[],y[],fx[],
           prob[],tot[],r[],mut[],bestf[],bestx[],besty[];
    String kr[],kr1[],kr2[];
    
    public NewJFrame() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
            (screenSize.width - frameSize.width)/2,
            (screenSize.height - frameSize.height)/2);
    }
    
    public NewJFrame(double a,double b,double g,double h, double d){
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
            (screenSize.width - frameSize.width)/2,
            (screenSize.height - frameSize.height)/2);
        bestf=new double[generasi+1];bestx=new double[generasi+1];besty=new double[generasi+1];
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
        pecahKromosom();
        desimal();
        System.out.println("Kromosom");
        System.out.println(Arrays.deepToString(kromosom));
        System.out.println(Arrays.toString(kr));
        System.out.println(Arrays.toString(kr1));
        System.out.println(Arrays.toString(kr2));
    }
    //convert kromosom dari biner ke desimal
    public void desimal(){
        for (int j = 0; j < kr.length; j++) {
            desimalx[j]= Integer.parseInt(kr1[j],2);
            System.out.println("Desimalnya  x : "+(int)desimalx[j]); 
            desimaly[j]= Integer.parseInt(kr2[j],2);
            System.out.println("Desimalnya  y : "+(int)desimaly[j]);   
        }decode();
    }
    //menghitung probabilitasnya
    public void decode() {
       for (int j = 0; j <kr.length; j++) {
           System.out.println("Decode gen "+j);
           decodex[j]=bax+desimalx[j]*((bbx-bax)/(Math.pow(2, m)-1));
           decodey[j]=bay+desimaly[j]*((bby-bay)/(Math.pow(2, n)-1));
           System.out.println("Hasil Decode ");
           x[j] = decodex[j];           
           y[j] = decodey[j];
           System.out.println("decode x = "+x[j]);
           System.out.println("decode y = "+y[j]);
       }calcFitness();
    }
    public void best(double a[]){
        double max=-99;int u=0;
        for(int i=0;i<a.length;i++)
            if(a[i]>=max){
                max=a[i];
                u=i;
            }
        bestf[gensekarang-1]=fx[u];
        nilaix.setText(String.valueOf(x[u]));
        nilaiy.setText(String.valueOf(y[u]));
        nilaifungsi.setText(String.valueOf(fx[u]));
        bestx[gensekarang-1]=x[u];
        besty[gensekarang-1]=y[u];
    }
    //menghitung banana function
    public void calcFitness(){
        double total=0;
        for (int j = 0; j < kr.length; j++) {
        double f=100*Math.pow((decodey[j]-Math.pow(decodex[j], 2)),2)+(1-Math.pow(decodex[j], 2));
        System.out.println("fungsi banana dengan x = "+x[j]+" y = "+y[j]);
        double temp = Math.pow(10, d);
        fx[j] = (double) Math.round(f * temp) / temp;
        total=total+fx[j];
        System.out.println(fx[j]);
        }
        best(fx);
        
        System.out.println("total fx");
        System.out.println(total);
        System.out.println("probabilitas fx");
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
            System.out.println(prob[j]);
            System.out.println("total probabilitas = "+tot[j]);
        }System.out.println(Arrays.toString(tot));
       
        seleksi();
    }
    //seleksi kromosom
    public void seleksi(){
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
        }else{
            System.out.println("best fitness, x, dan y");
            System.out.println(Arrays.toString(bestf));
            System.out.println(Arrays.toString(bestx));
                        System.out.println(Arrays.toString(besty));
                        nilaix.setText(String.valueOf(bestx[bestx.length-1]));
                        nilaiy.setText(String.valueOf(besty[besty.length-1]));
                        nilaifungsi.setText(String.valueOf(bestf[bestf.length-1]));
        }
    }
     public void crossover(){
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
            int c=rnd.nextInt(m+n-1);
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
        int indiv = 0,inkr = 0;
        for(int i=1;i<mut.length+1;i++){
            mut[i-1]=Math.random();
            if(mut[i-1]<pm){
                if(i<m+n){
                    inkr=i-1;
                    indiv=0;
                    System.out.println("i 1 ="+i);
                }
               
                else if(i%(m+n)!=0){
                    inkr=i%(m+n)-1;
                    indiv=i/(m+n);
                    System.out.println("i 3="+i);
                    System.out.println("index kromosom yang mutasi "+inkr);
                    System.out.println("index indiv yang mutasi "+indiv);
                }
                else{
                    System.out.println("i 4="+i);
                    inkr=m+n-1;
                    System.out.println("index kromosom yang mutasi "+inkr);
                    indiv=i/(m+n)-1;
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
        for(int i=0; i<tot.length; i++){
            tot[i]=0;
            fx[i]=0;
            prob[i]=0;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        batasbawahx = new javax.swing.JTextField();
        batasatasy = new javax.swing.JTextField();
        batasbawahy = new javax.swing.JTextField();
        batasatasx = new javax.swing.JTextField();
        belakangkoma = new javax.swing.JTextField();
        pcrossover = new javax.swing.JTextField();
        pmutasi = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        maxgen = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        nilaix = new javax.swing.JTextField();
        nilaiy = new javax.swing.JTextField();
        nilaifungsi = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("ALGORITMA GENETIKA");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("BANANA FUNCTION");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Input");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Batas bawah x");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Batas atas x");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Batas bawah y");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Batas atas y");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Ketepatan angka di");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Peluang crossover");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Parameter mutasi");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("belakang koma");

        batasbawahx.setText(" ");
        batasbawahx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batasbawahxActionPerformed(evt);
            }
        });

        batasatasy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batasatasyActionPerformed(evt);
            }
        });

        batasbawahy.setText(" ");
        batasbawahy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batasbawahyActionPerformed(evt);
            }
        });

        batasatasx.setText(" ");
        batasatasx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batasatasxActionPerformed(evt);
            }
        });

        pcrossover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pcrossoverActionPerformed(evt);
            }
        });

        pmutasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pmutasiActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Jumlah generasi");

        maxgen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxgenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(maxgen, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pmutasi)
                                    .addComponent(belakangkoma, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(batasatasx, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                    .addComponent(batasbawahx)
                                    .addComponent(batasbawahy)
                                    .addComponent(batasatasy)
                                    .addComponent(pcrossover, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(batasbawahx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(batasatasx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batasbawahy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(batasatasy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(belakangkoma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(jLabel11)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(pcrossover, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(pmutasi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(maxgen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Output");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Nilai x");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Nilai y");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("Nilai f(x,y)");

        nilaix.setText(" ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nilaifungsi))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nilaix, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                            .addComponent(nilaiy))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(nilaix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(nilaiy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(nilaifungsi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jButton1.setText("Hitung nilai maksimum");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Exit");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "", "x", "y", "f(x,y)"
            }
        ));
        jScrollPane2.setViewportView(Tabel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel4)))
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void batasbawahxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batasbawahxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batasbawahxActionPerformed

    private void batasbawahyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batasbawahyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batasbawahyActionPerformed

    private void batasatasxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batasatasxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batasatasxActionPerformed

    private void batasatasyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batasatasyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_batasatasyActionPerformed

    private void pcrossoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pcrossoverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pcrossoverActionPerformed

    private void pmutasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pmutasiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pmutasiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        double ax = Double.parseDouble(batasbawahx.getText());
        double bx = Double.parseDouble(batasatasx.getText());
        double ay = Double.parseDouble(batasbawahy.getText());
        double by = Double.parseDouble(batasatasy.getText());
        double D = Double.parseDouble(belakangkoma.getText());
        pc = Double.parseDouble(pcrossover.getText());
        pm = Double.parseDouble(pmutasi.getText());
        generasi=Integer.parseInt(maxgen.getText());
        NewJFrame a=new NewJFrame(ax,bx,ay,by,D);
      
        
        
        
    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void maxgenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxgenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxgenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabel;
    private javax.swing.JTextField batasatasx;
    private javax.swing.JTextField batasatasy;
    private javax.swing.JTextField batasbawahx;
    private javax.swing.JTextField batasbawahy;
    private javax.swing.JTextField belakangkoma;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField maxgen;
    private javax.swing.JTextField nilaifungsi;
    private javax.swing.JTextField nilaix;
    private javax.swing.JTextField nilaiy;
    private javax.swing.JTextField pcrossover;
    private javax.swing.JTextField pmutasi;
    // End of variables declaration//GEN-END:variables
}