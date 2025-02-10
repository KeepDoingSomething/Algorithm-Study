package programmers.LV2_17687;

public class Solution {

    private String[] answerList;
    private int maxSize;

    public String solution(int n, int answerSize, int peopleSize, int seq) {
        maxSize = (answerSize-1)*peopleSize + seq;
        answerList = new String[maxSize];
        initAnswer(n);
        StringBuilder result = new StringBuilder();
        for(int i=0; i<answerSize; i++){
            result.append(answerList[i*peopleSize + seq - 1]);
        }
        return result.toString();
    }

    private void initAnswer(int n){
        int index = 0;
        loop : for(int i=0; i<maxSize; i++){
            String integerN = Integer.toString(i, n);
            String[] answerArr = integerN.split("");
            for(String answer: answerArr){
                answerList[index++] = answer.toUpperCase();
                if(index == maxSize) break loop;
            }
        }
    }

}