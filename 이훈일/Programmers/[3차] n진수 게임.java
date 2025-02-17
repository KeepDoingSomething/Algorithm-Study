class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while(sb.length() < t*m) {
            sb.append(Integer.toString(num, n));
            num++;
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i = p-1; i < t*m; i += m) {
            answer.append(sb.charAt(i));
        }
        
        return answer.toString().toUpperCase();
    }
}
