import java.util.Scanner;

/**
 * ============================================================
 *   SIMULASI PENDAKIAN GUNUNG SEMERU
 *   Tugas Pertemuan Ke-4 : Array
 * ============================================================
 *
 * PETA GRID  :  6 baris  x  12 kolom
 *               Row 0 = paling atas  (Puncak Mahameru)
 *               Row 5 = paling bawah (Pos Ranu Pane)
 *               Col 0 = paling kiri
 *               Col 11= paling kanan
 *
 *  Nilai sel :
 *    0 = Jurang / merah (X)  -> tidak bisa dilewati
 *    1 = Jalur hijau biasa   -> bisa dilewati, TIDAK bisa istirahat
 *    2 = Pos / checkpoint    -> bisa dilewati DAN bisa istirahat
 *
 *       Col:  0    1    2    3    4    5    6    7    8    9   10   11
 *  Row 0 :    P    X    X    X    X    X    X    X    X    1    1    1
 *  Row 1 :    1    X    1    1    1    X    X   P3    X    1    X    1
 *  Row 2 :    1    X    X    X    1   P4    1    1    1    1    X    1
 *  Row 3 :   TC    1    1    1    1    X    1    1    X    X    X    1
 *  Row 4 :    1    1    X    1    1    X   P2    X    X    X    X    1
 *  Row 5 :   P5    1    X   RK    1    X    1    1    1    1    1   P1
 *
 *  Posisi awal : P1 (Pos Ranu Pane) = [5][11]
 *  Tujuan      : P  (Puncak)        = [0][0]
 *
 *  Tanda gerak :
 *    'L' = kiri      (col - 1)
 *    'U' = naik      (row - 1)
 *    'D' = turun     (row + 1)
 *    'R' = istirahat -> +10 energi, hanya boleh di Pos (nilai 2)
 *         (Catatan: di soal 'R' besar = istirahat, sesuai contoh 1 dan 2)
 *
 *  Setiap langkah gerak (L/U/D) mengurangi energi sebesar 1.
 * ============================================================
 */
public class auliatauhid_tugassemeru {

    /* ---------- konstanta tipe sel ---------- */
    static final int JURANG = 0;
    static final int JALUR  = 1;
    static final int POS    = 2;

    /* ---------- nama tiap sel khusus ---------- */
    static String namaSel(int row, int col) {
        if (row == 0 && col == 0)  return "Puncak Mahameru (P)";
        if (row == 1 && col == 7)  return "Pos 3 (P3)";
        if (row == 2 && col == 5)  return "Pos 4 (P4)";
        if (row == 3 && col == 0)  return "Tanjakan Cinta (TC)";
        if (row == 4 && col == 6)  return "Pos 2 (P2)";
        if (row == 5 && col == 0)  return "Pos Kalimati (P5)";
        if (row == 5 && col == 3)  return "Pos Ranu Kumbolo (RK)";
        if (row == 5 && col == 11) return "Pos Ranu Pane (P1)";
        return "Jalur [" + row + "][" + col + "]";
    }

    public static void main(String[] args) {

        /* ============================================================
         *  PETA  6 x 12
         *  Col :  0    1    2    3    4    5    6    7    8    9   10   11
         * ============================================================ */
        int[][] peta = {
            /* Row 0 */ { 2,   0,   0,   0,   0,   0,   0,   0,   0,   1,   1,   1 },
            /* Row 1 */ { 1,   0,   1,   1,   1,   0,   0,   2,   0,   1,   0,   1 },
            /* Row 2 */ { 1,   0,   0,   0,   1,   2,   1,   1,   1,   1,   0,   1 },
            /* Row 3 */ { 2,   1,   1,   1,   1,   0,   1,   1,   0,   0,   0,   1 },
            /* Row 4 */ { 1,   1,   0,   1,   1,   0,   2,   0,   0,   0,   0,   1 },
            /* Row 5 */ { 2,   1,   0,   2,   1,   0,   1,   1,   1,   1,   1,   2 },
        };

        int ROWS = peta.length;      // 6
        int COLS = peta[0].length;   // 12

        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("    SIMULASI PENDAKIAN GUNUNG SEMERU");
        System.out.println("==============================================");
        System.out.print("Masukkan tenaga awal : ");
        int tenaga = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Masukkan jalur       : ");
        String jalur = scanner.nextLine().trim();

        System.out.println("----------------------------------------------");

        /* Posisi awal: P1 / Pos Ranu Pane = [5][11] */
        int row    = 5;
        int col    = 11;
        int energi = tenaga;

        System.out.println("Start  : " + namaSel(row, col));
        System.out.println("Tenaga : " + energi);
        System.out.println("Jalur  : " + jalur);
        System.out.println("----------------------------------------------");

        boolean gagal        = false;
        String  pesanGagal   = "";
        int     langkahGagal = -1;

        for (int i = 0; i < jalur.length(); i++) {
            char step = jalur.charAt(i);

            /* ---- R = Istirahat ---- */
            if (step == 'R') {
                if (peta[row][col] != POS) {
                    gagal        = true;
                    pesanGagal   = "Mohon maaf, istirahat hanya diperbolehkan "
                                 + "di Pos-pos yang tersedia";
                    langkahGagal = i + 1;
                    break;
                }
                energi += 10;
                System.out.printf("Step %2d [R] Istirahat di %-28s -> Tenaga +10 = %d%n",
                        i + 1, namaSel(row, col), energi);
                continue; // tidak berpindah
            }

            /* ---- Gerak (L / U / D) ---- */
            int newRow = row;
            int newCol = col;

            switch (step) {
                case 'L': newCol = col - 1; break;
                case 'U': newRow = row - 1; break;
                case 'D': newRow = row + 1; break;
                default:
                    System.out.println("Step " + (i + 1) + " [" + step + "] tidak dikenal, dilewati.");
                    continue;
            }

            /* Cek batas grid */
            if (newRow < 0 || newRow >= ROWS || newCol < 0 || newCol >= COLS) {
                gagal        = true;
                int nomor    = Math.abs(newRow * COLS + newCol);
                pesanGagal   = "Jalur anda salah, anda masuk ke jurang/blank " + nomor;
                langkahGagal = i + 1;
                break;
            }

            /* Cek jurang */
            if (peta[newRow][newCol] == JURANG) {
                gagal        = true;
                int nomor    = newRow * COLS + newCol;
                pesanGagal   = "Jalur anda salah, anda masuk ke jurang/blank " + nomor;
                langkahGagal = i + 1;
                break;
            }

            /* Kurangi energi per langkah gerak */
            energi -= 1;

            System.out.printf("Step %2d [%c] %-28s -> %-28s | Tenaga: %d%n",
                    i + 1, step, namaSel(row, col), namaSel(newRow, newCol), energi);

            row = newRow;
            col = newCol;

            /* Cek energi habis sebelum sampai puncak */
            if (energi <= 0 && !(row == 0 && col == 0)) {
                gagal        = true;
                pesanGagal   = "Jalur anda benar, tapi tenaga anda tidak akan kuat, "
                             + "coba jalur lain atau sempatkan istirahat terlebih dahulu";
                langkahGagal = i + 1;
                break;
            }

            /* Cek apakah sudah tiba di Puncak */
            if (row == 0 && col == 0) {
                System.out.println("----------------------------------------------");
                System.out.println("HASIL: Selamat Pendakian anda berhasil mencapai "
                        + "Puncak Mahameru, sisa tenaga anda " + energi);
                System.out.println("==============================================");
                scanner.close();
                return;
            }
        }

        System.out.println("----------------------------------------------");

        if (gagal) {
            System.out.println("HASIL: " + pesanGagal);
            System.out.println("       (Terjadi pada langkah ke-" + langkahGagal + ")");
        } else {
            if (row == 0 && col == 0) {
                System.out.println("HASIL: Selamat Pendakian anda berhasil mencapai "
                        + "Puncak Mahameru, sisa tenaga anda " + energi);
            } else {
                System.out.println("HASIL: Jalur anda benar, tapi tenaga anda tidak akan kuat, "
                        + "coba jalur lain atau sempatkan istirahat terlebih dahulu");
                System.out.println("       Posisi terakhir : " + namaSel(row, col));
                System.out.println("       Sisa tenaga     : " + energi);
            }
        }

        System.out.println("==============================================");
        scanner.close();
    }
}