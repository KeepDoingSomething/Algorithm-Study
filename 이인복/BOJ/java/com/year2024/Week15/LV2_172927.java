package com.year2024.Week15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LV2_172927 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(
                    new int[]{1,1,0},
                    new String[] {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone","iron", "iron", "diamond","diamond"}
            )
        );
    }

    static class Solution {
        public int solution(int[] picks, String[] minerals) {
            List<Mass> masses = getSortedMasses(Arrays.stream(picks).sum(), minerals);
            int ans = 0;

            for(Mass mass : masses) {
                if(picks[0] != 0) {
                    ans += mass.getFatigue(25);
                    picks[0]--;
                } else if(picks[1] != 0) {
                    ans += mass.getFatigue(5);
                    picks[1]--;
                } else if(picks[2] != 0) {
                    ans += mass.getFatigue(1);
                    picks[2]--;
                } else {
                    break;
                }
            }

            return ans;
        }

        private static List<Mass> getSortedMasses(int totalPick, String[] minerals) {
            List<Mass> masses = new ArrayList<>();
            int cnt = 0;
            int dia = 0;
            int iro = 0;
            int sto = 0;

            for(String mineral : minerals) {
                if(totalPick == 0) break;  // 곡괭이 숫자가 부족하면 광물은 남았어도 더 이상 집계하지 않음

                switch(mineral.charAt(0)) {
                    case 'd': dia++; break;
                    case 'i': iro++; break;
                    case 's': sto++; break;
                    default: // Unreachable Code
                }

                cnt++;

                if(cnt == 5) {  // 5개를 하나로 묶음
                    masses.add(new Mass(dia, iro, sto));
                    cnt = 0;
                    dia = 0;
                    iro = 0;
                    sto = 0;
                    totalPick--;
                }
            }

            if(totalPick != 0 && (dia != 0 || iro != 0 || sto != 0)) {  // 5 개 묶음이 아닌 케이스
                masses.add(new Mass(dia, iro, sto));
            }

            return masses.stream().sorted(Mass::compareTo).collect(Collectors.toList());
        }
    }

    static class Mass implements Comparable<Mass>{
        int dia;
        int iro;
        int sto;

        public Mass(int dia, int iro, int sto) {
            this.dia = dia;
            this.iro = iro;
            this.sto = sto;
        }

        public int getFatigue(int pick) {
            return calcEachFatigue(pick, dia, 25) +
                   calcEachFatigue(pick, iro, 5) +
                   calcEachFatigue(pick, sto, 1);
        }

        public int calcEachFatigue(int pick, int mineral, int value) {
            int sum = 0;

            while(mineral-- > 0) {
                int res = value / pick;
                sum += (res == 0) ? 1 : res;
            }

            return sum;
        }

        @Override
        public int compareTo(Mass mass) {
            int diaRes = Integer.compare(mass.dia, this.dia);
            if(diaRes != 0) return diaRes;

            int ironRes = Integer.compare(mass.iro, this.iro);
            if(ironRes != 0) return ironRes;

            return Integer.compare(mass.sto, this.sto);
        }
    }
}