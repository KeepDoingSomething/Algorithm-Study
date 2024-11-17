/**
 * Author    : 이상경
 * Date      : 2024.11.17
 * Runtime   : 0.73 ms
 * Memory    : 83.9 MB
 * Algorithm : 백트래킹
 */

public class ArcheryCompetition {
    int[] answer = {-1};
    int maxDiff = -1;

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[11];
        dfs(0, n, info, ryan);
        return answer;
    }

    private void dfs(int depth, int n, int[] apeach, int[] ryan) {
        if (depth == n) {
            int diff = calculateDiff(apeach, ryan);
            if (diff > 0) {
                if (diff > maxDiff) {
                    maxDiff = diff;
                    answer = ryan.clone();
                } else if (diff == maxDiff) {
                    if (choiceMoreLowerCase(answer, ryan)) {
                        answer = ryan.clone();
                    }
                }
            }
            return;
        }

        for (int i = 0; i <= 10 && ryan[i] <= apeach[i]; i++) {
            if (depth + apeach[i] + 1 <= n) {
                ryan[i] += apeach[i] + 1;
                dfs(depth + apeach[i] + 1, n, apeach, ryan);
                ryan[i] -= apeach[i] + 1;
            }
        }

        if (depth < n) {
            ryan[10] += n - depth;
            dfs(n, n, apeach, ryan);
            ryan[10] -= n - depth;
        }
    }

    private int calculateDiff(int[] apeach, int[] ryan) {
        int apeachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i <= 10; i++) {
            if (apeach[i] == 0 && ryan[i] == 0) {
                continue;
            }

            if (apeach[i] >= ryan[i]) {
                apeachScore += 10 - i;
            } else {
                ryanScore += 10 - i;
            }
        }

        return ryanScore - apeachScore;
    }

    private boolean choiceMoreLowerCase(int[] arr1, int[] arr2) {
        for (int i = 10; i >= 0; i--) {
            if (arr1[i] != arr2[i]) {
                return arr2[i] > arr1[i];
            }
        }
        return false;
    }
}
