package programmers.level2.파일명정렬;

import java.util.Arrays;

class FileObj implements Comparable<FileObj> {

    String head;
    String number;
    int idx;
    String original;

    public FileObj(String head, String number, int idx, String original) {
        this.head = head;
        this.number = number;
        this.idx = idx;
        this.original = original;
    }


    @Override
    public int compareTo(FileObj o) {
        if (this.head.equalsIgnoreCase(o.head)) {
            int thisNumber = Integer.parseInt(this.number);
            int objNumber = Integer.parseInt(o.number);
            if (thisNumber == objNumber) {
                return this.idx - o.idx;
            }
            return thisNumber - objNumber;
        }
        return this.head.compareToIgnoreCase(o.head);
    }

    @Override
    public String toString() {
        return original;
    }
}

public class Solution {

    public String[] solution(String[] files) {
        // file
        FileObj[] fileObjs = new FileObj[files.length];
        for (int i = 0; i < files.length; i++) {
            String[] parsed = parseFile(files[i]);

            fileObjs[i] = new FileObj(parsed[0], parsed[1], i, files[i]);
        }

        Arrays.sort(fileObjs);
        String[] answer = new String[files.length];
        for (int i = 0; i < fileObjs.length; i++) {
            answer[i] = fileObjs[i].toString();
        }
        return answer;
    }

    private String[] parseFile(String file) {
        StringBuilder head = new StringBuilder();
        StringBuilder number = new StringBuilder();
        int idx = 0;
        while (idx < file.length() && !Character.isDigit(file.charAt(idx))) {
            head.append(file.charAt(idx++));
        }
        while (idx < file.length() && Character.isDigit(file.charAt(idx))) {
            number.append(file.charAt(idx++));
        }

        return new String[]{head.toString(), number.toString()};
    }

}
