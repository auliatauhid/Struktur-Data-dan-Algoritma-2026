public class soalsatu {

    public static void main(String[] args) {

        /* ---------- Data awal ---------- */
        String[] nama = {
            "Fahmi", "Romi", "Andri", "Fadillah",
            "Ruli", "Rudi", "Dendi", "Zaki"
        };
        String[] alamat = {
            "Jakarta", "Solo", "Jakarta", "Banyuwangi",
            "Bandung", "Bali", "Purwokerto", "Madiun"
        };

        int n = nama.length;
        int i, j;

        /* Salinan data untuk Bubble Sort (agar data asli tidak berubah) */
        String[] namaBubble = new String[n];
        String[] alamatBubble = new String[n];
        for (i = 0; i < n; i++) {
            namaBubble[i] = nama[i];
            alamatBubble[i] = alamat[i];
        }

        /* Salinan data untuk Selection Sort */
        String[] namaSelection = new String[n];
        String[] alamatSelection = new String[n];
        for (i = 0; i < n; i++) {
            namaSelection[i] = nama[i];
            alamatSelection[i] = alamat[i];
        }

        /* ========================================================
           1. BUBBLE SORT (berdasarkan Nama, urut abjad A-Z)
           ======================================================== */
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - 1 - i; j++) {

                /* Bandingkan string secara manual, karakter per karakter */
                char[] s1 = namaBubble[j].toCharArray();
                char[] s2 = namaBubble[j + 1].toCharArray();

                int a = 0, b = 0;
                boolean lebihBesar = false;
                boolean ditemukanBeda = false;

                while (a < s1.length && b < s2.length) {
                    if (s1[a] != s2[b]) {
                        lebihBesar = s1[a] > s2[b];
                        ditemukanBeda = true;
                        break;
                    }
                    a++;
                    b++;
                }
                /* Jika sampai akhir tidak ada perbedaan, string yang lebih panjang dianggap lebih besar */
                if (!ditemukanBeda) {
                    lebihBesar = s1.length > s2.length;
                }

                if (lebihBesar) {
                    /* Tukar nama */
                    String tempNama = namaBubble[j];
                    namaBubble[j] = namaBubble[j + 1];
                    namaBubble[j + 1] = tempNama;

                    /* Tukar alamat (mengikuti pasangannya) */
                    String tempAlamat = alamatBubble[j];
                    alamatBubble[j] = alamatBubble[j + 1];
                    alamatBubble[j + 1] = tempAlamat;
                }
            }
        }

        /* ========================================================
           2. SELECTION SORT (berdasarkan Nama, urut abjad A-Z)
           ======================================================== */
        for (i = 0; i < n - 1; i++) {
            int idxMin = i;

            for (j = i + 1; j < n; j++) {
                /* Bandingkan string secara manual */
                char[] s1 = namaSelection[j].toCharArray();
                char[] s2 = namaSelection[idxMin].toCharArray();

                int a = 0, b = 0;
                boolean lebihKecil = false;
                boolean ditemukanBeda = false;

                while (a < s1.length && b < s2.length) {
                    if (s1[a] != s2[b]) {
                        lebihKecil = s1[a] < s2[b];
                        ditemukanBeda = true;
                        break;
                    }
                    a++;
                    b++;
                }
                if (!ditemukanBeda) {
                    lebihKecil = s1.length < s2.length;
                }

                if (lebihKecil) {
                    idxMin = j;
                }
            }

            if (idxMin != i) {
                /* Tukar nama */
                String tempNama = namaSelection[i];
                namaSelection[i] = namaSelection[idxMin];
                namaSelection[idxMin] = tempNama;

                /* Tukar alamat */
                String tempAlamat = alamatSelection[i];
                alamatSelection[i] = alamatSelection[idxMin];
                alamatSelection[idxMin] = tempAlamat;
            }
        }

        /* ========================================================
           CETAK HASIL
           ======================================================== */
        System.out.println("=== DATA AWAL ===");
        cetakTabel(nama, alamat, n);

        System.out.println("\n=== HASIL BUBBLE SORT (berdasarkan Nama) ===");
        cetakTabel(namaBubble, alamatBubble, n);

        System.out.println("\n=== HASIL SELECTION SORT (berdasarkan Nama) ===");
        cetakTabel(namaSelection, alamatSelection, n);
    }

    /* Fungsi bantu hanya untuk mencetak tabel, tidak melakukan proses sorting */
    static void cetakTabel(String[] nama, String[] alamat, int n) {
        System.out.printf("%-12s %s%n", "Nama", "Alamat");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-12s %s%n", nama[i], alamat[i]);
        }
    }
}