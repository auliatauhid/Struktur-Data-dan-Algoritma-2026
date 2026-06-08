import java.util.Scanner;

public class MenuMakananUTS {
    public static void main(String[] args) {
        // Data menu menggunakan array paralel
        String[] namaMakanan = {"Soto", "Rawon", "Pecel", "Bakso", "Siomay"};
        int[] hargaMakanan   = {15000, 20000, 10000, 12500, 25000};

        Scanner scanner = new Scanner(System.in);

        System.out.print("Isikan Makanan yang dipesan : ");
        String inputLine = scanner.nextLine().trim();
        String[] tokens = inputLine.split("\\s+");

        // Array untuk menyimpan pesanan (maks 10 item pesanan)
        String[] pesananNama  = new String[10];
        int[]    pesananHarga = new int[10];
        int[]    pesananJumlah = new int[10];
        int jumlahPesanan = 0;

        int i = 0;
        while (i < tokens.length - 1) {
            String inputNama = tokens[i];
            int inputJumlah  = Integer.parseInt(tokens[i + 1]);

            // Cari nama makanan di array menu (case-insensitive)
            boolean ditemukan = false;
            for (int j = 0; j < namaMakanan.length; j++) {
                if (namaMakanan[j].equalsIgnoreCase(inputNama)) {
                    pesananNama[jumlahPesanan]   = namaMakanan[j];
                    pesananHarga[jumlahPesanan]  = hargaMakanan[j];
                    pesananJumlah[jumlahPesanan] = inputJumlah;
                    jumlahPesanan++;
                    ditemukan = true;
                    break;
                }
            }

            if (!ditemukan) {
                System.out.println("Makanan '" + inputNama + "' tidak ada di menu.");
            }

            i += 2;
        }

        // Hitung dan tampilkan total
        System.out.println("\nTotal Harga :");
        int total = 0;
        for (int k = 0; k < jumlahPesanan; k++) {
            int subtotal = pesananHarga[k] * pesananJumlah[k];
            System.out.println("✓ " + pesananNama[k]
                + " @" + pesananHarga[k]
                + " * " + pesananJumlah[k]
                + " = " + subtotal);
            total += subtotal;
        }
        System.out.println("Total = " + total);

        scanner.close();
    }
}