package programmers.LV2_17686;

import java.util.*;

public class Solution {
    public String[] solution(String[] files) {
        List<File> list = new ArrayList<>();
        for(String fileName : files){
            list.add(new File(fileName));
        }

        Collections.sort(list);

        String[] answer = new String[list.size()];
        for(int i =0; i<answer.length; i++){
            answer[i] = list.get(i).getFileName();
        }

        return answer;
    }

    private class File implements Comparable<File>{
        String head;
        String number;
        String tail;

        File(String fileName){
            char[] cArr = fileName.toCharArray();
            int numberStartIdx = cArr.length;
            int numberLength = 0;
            boolean isFindNumber = false;

            for(int i=0; i<cArr.length; i++){
                boolean isNumber = cArr[i] >= '0' && cArr[i] <= '9';
                if(isNumber){
                    numberStartIdx = Math.min(numberStartIdx, i);
                    numberLength++;
                    isFindNumber = true;
                }else if(!isNumber && isFindNumber ){
                    break;
                }
            }

            numberLength = Math.min(numberLength, 5);

            this.head = fileName.substring(0, numberStartIdx);
            this.number = fileName.substring(numberStartIdx, numberStartIdx + numberLength);
            this.tail = fileName.substring(numberStartIdx + numberLength);
        }

        public String getFileName(){
            return head + number + tail;
        }

        @Override
        public int compareTo(File f){

            int result = 0;
            int headCompare = this.head.toUpperCase().compareTo(f.head.toUpperCase());
            int numberCompare = Integer.parseInt(this.number) - Integer.parseInt(f.number);

            if(headCompare != 0){
                result = headCompare;
            }else if(numberCompare == 0){
                result = 0;
            }else{
                result = numberCompare;
            }

            return result;
        }

    }

}