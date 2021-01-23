package ru.tver.tstu;


import java.util.Arrays;

class GFG {

    static int NO_OF_CHARS = 256;

    static int getNextState(char[] pat, int M,
                            int state, int x) {

        if (state < M && x == pat[state])
            return state + 1;


        int ns, i;


        for (ns = state; ns > 0; ns--) {
            if (pat[ns - 1] == x) {
                for (i = 0; i < ns - 1; i++)
                    if (pat[i] != pat[state - ns + 1 + i])
                        break;
                if (i == ns - 1)
                    return ns;
            }
        }

        return 0;
    }

    static void computeTF(char[] pat, int M, int TF[][]) {
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < NO_OF_CHARS; ++x)
                TF[state][x] = getNextState(pat, M, state, x);
    }

    static void search(char[] pat, char[] txt) {
        int M = pat.length;
        int N = txt.length;

        int[][] TF = new int[M + 1][NO_OF_CHARS];

        computeTF(pat, M, TF);

        int i, state = 0;
        for (i = 0; i < N; i++) {
            state = TF[state][txt[i]];
            if (state == M)
                System.out.println("Pattern " + Arrays.toString(pat) + " found "
                        + "at index " + (i - M + 1));
        }
    }

    public static void main(String[] args) {
        char[] pat = "aaababca".toCharArray();
//        1. ccf
        char[] txt1 = "ccf".toCharArray();
        search(txt1, pat);
//        2. abg
        char[] txt2 = "abg".toCharArray();
        search(txt2, pat);
//        3. aac
        char[] txt3 = "aac".toCharArray();
        search(txt3, pat);
//        4. aaa
        char[] txt4 = "aaa".toCharArray();
        search(txt4, pat);
//        5. abb
        char[] txt5 = "abb".toCharArray();
        search(txt5, pat);
//        6. acc
        char[] txt6 = "acc".toCharArray();
        search(txt6, pat);
//        7. acb
        char[] txt7 = "acb".toCharArray();
        search(txt7, pat);
//        8. acc
        char[] txt8 = "acc".toCharArray();
        search(txt8, pat);
//        9. bbb
        char[] txt9 = "acc".toCharArray();
        search(txt9, pat);
//        10. bac
        char[] txt10 = "bac".toCharArray();
        search(txt10, pat);
//        11. ccc
        char[] txt11 = "ccc".toCharArray();
        search(txt11, pat);
//        12. cca
        char[] txt12 = "cca".toCharArray();
        search(txt12, pat);
//        13. ccb
        char[] txt13 = "ccb".toCharArray();
        search(txt13, pat);
//        14. bcs
        char[] txt14 = "bcs".toCharArray();
        search(txt14, pat);
//        15. asd
        char[] txt15 = "asd".toCharArray();
        search(txt15, pat);
//        16. dff
        char[] txt16 = "dff".toCharArray();
        search(txt16, pat);

    }
} 

