package com.year2024.Week14;

import java.util.ArrayList;
import java.util.List;

public class LV2_17686 {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(
            sol.solution(new String[] {"F-15"})
        );

        // input: "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"
        // output: "img1.png", "IMG01.GIF", "img02.png", "img2.JPG", "img10.png", "img12.png"

        // input: "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"
        // output: "A-10 Thunderbolt II", "B-50 Superfortress", "F-5 Freedom Fighter", "F-14 Tomcat"
    }

    static class Solution {
        public String[] solution(String[] files) {
            List<File> fileList = new ArrayList<>();

            for(int i = 0; i < files.length; i++) {
                fileList.add(getFile(files[i]));
            }

            fileList.sort(File::compareTo);  // 정의 해놓은 compareTo 기준으로 정렬

            return fileList.stream().map(File::getOrigin).toArray(String[]::new);  // 파일의 원본 이름 기준으로 배열 생성
        }

        public File getFile(String file) {
            int idx = 0;  // file 맨 앞 글자
            String head;
            int num;

            StringBuilder sb = new StringBuilder();

            // HEAD 추출
            while(idx < file.length() && !Character.isDigit(file.charAt(idx))) {
                sb.append(file.charAt(idx++));
            }

            head = sb.toString();
            sb.setLength(0);  // StringBuilder 초기화

            // NUM 추출
            while(idx < file.length() && Character.isDigit(file.charAt(idx))) {
                sb.append(file.charAt(idx++));
            }

            num = Integer.parseInt(sb.toString());
            sb.setLength(0);

            return new File(file, head, num);
        }

        class File implements Comparable<File> {
            String origin;
            String head;
            int num;

            public File(String origin, String head, int num) {
                this.origin = origin;
                this.head = head.toUpperCase();
                this.num = num;
            }

            @Override
            public int compareTo(File file) {
                int headRes = this.head.compareTo(file.head);

                if(headRes == 0) {
                    return this.num - file.num;
                }

                return headRes;
            }

            public String getOrigin() {
                return origin;
            }
        }
    }
}