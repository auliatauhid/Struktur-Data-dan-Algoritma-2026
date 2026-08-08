import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class soaldua {

    public static void main(String[] args) {
        int[] data = {19, 40, 10, 90, 2, 50, 60, 50, 1};
        Scanner sc = new Scanner(System.in);

        System.out.println("Array asli : " + Arrays.toString(data));
        System.out.println("Indeks     : 1..." + data.length + " (indeks dimulai dari 1)");
        System.out.println("----------------------------------------------------");

        boolean lanjut = true;
        while (lanjut) {
            System.out.print("Masukkan angka yang dicari (atau ketik 'exit' untuk keluar): ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                lanjut = false;
                continue;
            }

            try {
                int target = Integer.parseInt(input);
                cariData(data, target);
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid, masukkan angka (integer).");
            }

            System.out.println("----------------------------------------------------");
        }

        System.out.println("Program selesai.");
        sc.close();
    }

    /**
     * Mencari 'target' pada array 'data' menggunakan binary search
     * (dilakukan pada salinan array yang sudah diurutkan),
     * kemudian menampilkan seluruh posisi (indeks asli, 1-based)
     * dari nilai tersebut pada array sebelum diurutkan.
     */
    public static void cariData(int[] data, int target) {
        // 1. Buat salinan array lalu urutkan, khusus untuk proses binary search
        int[] sorted = data.clone();
        Arrays.sort(sorted);

        // 2. Lakukan binary search pada array yang sudah terurut
        boolean ditemukan = binarySearch(sorted, target);

        if (ditemukan) {
            // 3. Cari semua indeks (posisi asli) dari nilai target di array awal
            List<Integer> indeks = new ArrayList<>();
            for (int i = 0; i < data.length; i++) {
                if (data[i] == target) {
                    indeks.add(i + 1); // +1 karena indeks dimulai dari 1
                }
            }

            if (indeks.size() == 1) {
                System.out.println("Angka " + target + " ada di indeks ke " + indeks.get(0));
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < indeks.size(); i++) {
                    sb.append(indeks.get(i));
                    if (i < indeks.size() - 2) {
                        sb.append(", ");
                    } else if (i == indeks.size() - 2) {
                        sb.append(" dan ");
                    }
                }
                System.out.println("Angka " + target + " ada di indeks ke " + sb.toString());
            }
        } else {
            System.out.println("Angka " + target + " tidak ada dalam array");
        }
    }

    /**
     * Algoritma binary search standar.
     * Mengembalikan true jika target ditemukan pada array yang sudah terurut.
     */
    public static boolean binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }
}