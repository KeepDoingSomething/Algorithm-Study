package programmers.LV3_81303;

import java.util.*;

public class Solution {

    Stack<Row> deleteHistory = new Stack<>();
    Row curRow = null;
    String[] result;

    public String solution(int n, int k, String[] cmd) {

        result = new String[n];

        for(int i = 0; i < result.length; i++){
            result[i] = "O";
        }

        Row prev = null;
        for(int i = 0; i < n; i++){
            Row row = new Row();
            row.rowNum = i;

            if(prev != null){
                prev.next = row;
            }

            row.prev = prev;

            if(i == k){
                curRow = row;
            }
            prev = row;
        }

        for(String command : cmd){
            run(command);
        }

        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < n; i ++){
            answer.append(result[i]);
        }
        return answer.toString();
    }

    private class Row{
        Row prev;
        int rowNum;
        Row next;
    }

    private void run(String command){
        StringTokenizer st = new StringTokenizer(command);
        String flag = st.nextToken();
        int value = 0;
        switch(flag){
            case "U":
                value = Integer.parseInt(st.nextToken());
                selectUp(value);
                break;
            case "D":
                value = Integer.parseInt(st.nextToken());
                selectDown(value);
                break;
            case "C":
                deleteRow();
                break;
            case "Z":
                backward();
                break;
        }
    }

    private void selectUp(int up){
        while(up-- > 0){
            curRow = curRow.prev;
        }
    }

    private void selectDown(int down){
        while(down-- > 0){
            curRow = curRow.next;
        }
    }

    private void deleteRow(){
        result[curRow.rowNum] = "X";
        deleteHistory.push(curRow);

        boolean isLastRow = curRow.next == null;

        if(curRow.next == null){
            curRow.prev.next = null;
            curRow = curRow.prev;

        }else if(curRow.prev == null){
            curRow.next.prev = null;
            curRow = curRow.next;

        }else{
            curRow.prev.next = curRow.next;
            curRow.next.prev = curRow.prev;
            curRow = curRow.next;
        }
    }

    private void backward(){
        Row restore = deleteHistory.pop();
        result[restore.rowNum] = "O";

        if(restore.prev != null){
            restore.prev.next = restore;
        }

        if(restore.next != null){
            restore.next.prev = restore;
        }
    }

}