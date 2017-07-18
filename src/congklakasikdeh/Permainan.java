/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package congklakasikdeh;

import java.util.Scanner;

/**
 *
 * @author kemalmh
 */
public class Permainan {

    public int[][] papanCongklak = new int[16][2];
    //7 punya musuh, 15 punya kita
    //array ke 2 itu posisi
    Scanner inputan = new Scanner(System.in);
    public boolean player = false;
    //int loop = 1;
    //int[][] step = new int[100][2];

    int[] hasilArray = {0, 0, 0, 0, 0, 0, 0};

    public void Deklarasi() {
        papanCongklak[0][0] = 1;
        papanCongklak[1][0] = 2;
        papanCongklak[2][0] = 3;
        papanCongklak[3][0] = 4;
        papanCongklak[4][0] = 5;
        papanCongklak[5][0] = 6;
        papanCongklak[6][0] = 7;

        papanCongklak[8][0] = 7;
        papanCongklak[9][0] = 6;
        papanCongklak[10][0] = 5;
        papanCongklak[11][0] = 4;
        papanCongklak[12][0] = 3;
        papanCongklak[13][0] = 2;
        papanCongklak[14][0] = 1;
    }

    public void Mulai() {
        //Deklarasi();
        papanCongklak[7][1] = 0;
        papanCongklak[15][1] = 0;
        for (int x = 0; x < 16; x++) {
            if ((x != 7) && (x != 15)) {
                papanCongklak[x][1] = 7;
            }
        }
    }

    public void Liat() {
        System.out.println("   [" + papanCongklak[0][1] + "][" + papanCongklak[1][1] + "]"
                + "[" + papanCongklak[2][1] + "][" + papanCongklak[3][1] + "][" + papanCongklak[4][1] + "]"
                + "[" + papanCongklak[5][1] + "][" + papanCongklak[6][1] + "]"
                + "[" + papanCongklak[7][1] + "]");
        System.out.println("[" + papanCongklak[15][1] + "][" + papanCongklak[14][1] + "]"
                + "[" + papanCongklak[13][1] + "][" + papanCongklak[12][1] + "][" + papanCongklak[11][1] + "]"
                + "[" + papanCongklak[10][1] + "][" + papanCongklak[9][1] + "][" + papanCongklak[8][1] + "]");
    }

    public void giliranSiapa() {
        if (papanCongklak[7][1] + papanCongklak[15][1] == 98) {
            if (papanCongklak[7][1] > papanCongklak[15][1]) {
                System.out.println("KAMU MENANG");
                System.exit(0);
            } else {
                System.out.println("AKU MENANG");
                System.exit(0);
            }

        }
        if (player == false) {
            int status;
            status = 0;
            for (int x = 8; x < 15; x++) {
                if (papanCongklak[x][1] != 0) {
                    status = 1;
                    break;
                }

            }
            if (status == 0) {
                player = true;
                giliranSiapa();
            }

            int pilihanku;
            do {
                System.out.println("(AKU) pilih mana yang mau diambil");
                pilihanku = inputan.nextInt();
            } while (pilihanku <= 7);

            int bijiSementara;
            int lokasi;
            int counter;
            lokasi = pilihanku;
            int a = 0;
            while (papanCongklak[lokasi][1] != 0) {
                bijiSementara = papanCongklak[lokasi][1];
                papanCongklak[lokasi][1] = 0;
                counter = 0;
                while (counter < bijiSementara) {
                    counter++;
                    if ((lokasi + counter) % 16 == 7) {
                        //counter++;
                        papanCongklak[(lokasi + counter) % 16 + 1][1]++;
                        lokasi++;
                    } else {
                        papanCongklak[(lokasi + counter) % 16][1]++;
                    }
                }
                lokasi = (lokasi + counter) % 16;
                if (lokasi == 15) {
                    break;
                } else if (papanCongklak[lokasi][1] == 1) {
                    break;
                }
                a++;
            }

            if (lokasi <= 6) {
                player = true;
                Liat();
                System.out.print("\n");
                giliranSiapa();
            } else if (lokasi > 7 && lokasi < 15 && papanCongklak[lokasi][1] != 0) { //nembak
                papanCongklak[15][1] += papanCongklak[16 - (lokasi + 2)][1] + 1;
                papanCongklak[lokasi][1] = 0;
                papanCongklak[16 - (lokasi + 2)][1] = 0;
                player = true;
                Liat();
                System.out.print("\n");
                giliranSiapa();
            } else if (lokasi == 15) {
                Liat();
                System.out.print("\n");
                giliranSiapa();
            } else if (lokasi > 7 && lokasi < 15 && papanCongklak[lokasi][1] == 0) { //nembak
                System.out.println("pilihan tidak boleh disini");
                giliranSiapa();
            }

        } else {
            //AI WOY AI
            int[][] simpenPapan = new int[16][2];
            int hasil = 0;
            int yangDiambil;
            for (int x = 0; x < 16; x++) {
                simpenPapan[x][1] = papanCongklak[x][1];
            }

            int status;
            status = 0;
            for (int x = 0; x < 7; x++) {
                if (papanCongklak[x][1] != 0) {
                    status = 1;
                    break;
                }

            }
            if (status == 0) {
                player = false;
                giliranSiapa();
            }

            int pilihanku = 0;
            /*do {
                System.out.println("(KAMU) pilih mana yang mau diambil");
                pilihanku = inputan.nextInt();
            } while (pilihanku >= 7);*/
            int bijiSementara;
            int lokasi;
            int counter;
            lokasi = pilihanku;
            int a = 0;
            AIScanning(simpenPapan);                                              //Untuk scanning jadi tau langkah mana yang harus diambil
            
            
            int y = 0;
            for (int x = 1; x < 7; x++) {
                if (hasilArray[y] < hasilArray[x]) {                             //untuk mensortir, dari hasil scanning tadi langkah mana yang paling banyak dapetnya
                    y = x;
                }
            }
            
            for (int x = 0; x < 16; x++) {
                papanCongklak[x][1] = simpenPapan[x][1];
            }
            pilihanku = y;
            System.out.println("pilihan musuh = " + (pilihanku+1) + "\n");
            lokasi = pilihanku;
            while (papanCongklak[lokasi][1] != 0) {
                bijiSementara = papanCongklak[lokasi][1];
                papanCongklak[lokasi][1] = 0;
                counter = 0;
                while (counter < bijiSementara) {
                    counter++;
                    if ((lokasi + counter) % 16 == 15) {
                        //counter++;
                        papanCongklak[(lokasi + counter + 1) % 16][1]++;
                        lokasi++;
                    } else {
                        papanCongklak[(lokasi + counter) % 16][1]++;
                    }
                }
                lokasi = (lokasi + counter) % 16;
                if (lokasi == 7) {
                    break;
                } else if (papanCongklak[lokasi][1] == 1) {
                    break;
                }
            }
            hasil = papanCongklak[7][1];
            hasilArray[y] = hasil;

            if (lokasi > 7) {
                player = false;
                Liat();
                System.out.print("\n");
                //System.out.println("masuk di lokasi > 7");
                giliranSiapa();
            } else if (lokasi < 7 && papanCongklak[lokasi][1] != 0) { //nembak
                papanCongklak[7][1] += papanCongklak[16 - (lokasi + 2)][1] + 1;
                papanCongklak[lokasi][1] = 0;
                papanCongklak[16 - (lokasi + 2)][1] = 0;
                player = false;
                Liat();
                System.out.print("\n");
                //System.out.println("masuk di lokasi < 7");
                giliranSiapa();
            } else if (lokasi == 7) {
                //System.out.println("masuk di lokasi == 7");
                Liat();
                System.out.print("\n");
                giliranSiapa();
                //AIScanning(papanCongklak);
            }
        }

    }

    public void AIScanning(int[][] simpenPapan) {
        //bentar
        int pilihanku;
        int lokasi;
        int bijiSementara;
        int counter;
        int hasil;
        for (int y = 0; y < 7; y++) {    //looping sebanyak jumlah penampung kecil 
            for (int x = 0; x < 16; x++) {
                papanCongklak[x][1] = simpenPapan[x][1];                         //reset papan dummy supaya seprti kondisi papan terakhir
            }

            pilihanku = y;
            lokasi = pilihanku;
            while (papanCongklak[lokasi][1] != 0) {
                bijiSementara = papanCongklak[lokasi][1];
                papanCongklak[lokasi][1] = 0;
                counter = 0;
                while (counter < bijiSementara) {
                    counter++;
                    if ((lokasi + counter) % 16 == 15) {
                        //counter++;
                        papanCongklak[(lokasi + counter + 1) % 16][1]++;
                        lokasi++;
                    } else {
                        papanCongklak[(lokasi + counter) % 16][1]++;
                    }
                }
                lokasi = (lokasi + counter) % 16;
                // System.out.println(lokasi);
                if (lokasi == 7) {                                              
                    //step[loop][0] = y;
                    //step[loop][1] = papanCongklak[7][1];
                    //loop++;
                    break;
                } else if (papanCongklak[lokasi][1] == 1) {                     
                    break;
                }
            }
            hasil = papanCongklak[7][1];
            System.out.println("pilihan ke " + (pilihanku+1));
            Liat();                                                                     //melihat hasil pilihan ke y
            System.out.println("prediksi isi lumbung " + papanCongklak[7][1] + "\n");
            hasilArray[y] = hasil;                                                   //simpan hasil pilihan y ke array[y]

            if (lokasi > 7) {                                                       //kondisi dimana dia berhenti
                //player = false;
                //Liat();
                //giliranSiapa();
            } else if (lokasi < 7 && papanCongklak[lokasi][1] != 0) { //nembak      //kondisi dimana dia berhenti
                papanCongklak[7][1] += papanCongklak[16 - (lokasi + 2)][1] + 1;
                papanCongklak[lokasi][1] = 0;
                papanCongklak[16 - (lokasi + 2)][1] = 0;
                System.out.println("prediksi isi lumbung setelah nembak " + papanCongklak[7][1] + "\n");
                //player = false;
                //Liat();
                //giliranSiapa();
            } else if (lokasi == 7) {                                              //kondisi dimana dia berhenti
                //Liat();
                //AIScanning(papanCongklak);
            } else if (lokasi < 7 && papanCongklak[lokasi][1] == 0) {
            }
        }
        //bentar
        //return hasilArray;
    }

}
