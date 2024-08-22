package com.Week14;

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

            for(String file : files) {
                int idx = 0;  // file 맨 앞 글자
                String head;
                int num;
                String tail;

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

                // TAIL 추출
                for(int i = idx; i < file.length() - 1; i++) {
                    sb.append(file.charAt(i));
                }

                tail = sb.toString();

                fileList.add(new File(file, head, num, tail));
            }

            fileList.sort(File::compareTo);  // 정의 해놓은 compareTo 기준으로 정렬

            return fileList.stream().map(File::getOrigin).toArray(String[]::new);  // 파일의 원본 이름 기준으로 배열 생성
        }

        class File implements Comparable<File> {
            String origin;
            Head head;
            Num num;
            Tail tail;

            public File(String origin, String head, int num, String tail) {
                this.origin = origin;
                // 인스턴스 생성은 과한거 같음 그냥 String / int 로 충분히 해결 가능한 문제 였는데
                this.head = new Head(head);
                this.num = new Num(num);
                this.tail = new Tail(tail);
            }

            @Override
            public int compareTo(File file) {
                int headRes = this.head.compareTo(file.head);

                if(headRes == 0) {
                    return this.num.compareTo(file.num);
                }

                return headRes;
            }

            public String getOrigin() {
                return origin;
            }

            class Tail {
                String tail;

                public Tail(String tail) {
                    this.tail = tail;
                }
            }

            class Num implements Comparable<Num> {
                int num;

                public Num(int num) {
                    this.num = num;
                }

                @Override
                public int compareTo(Num num) {
                    return this.num - num.num;
                }
            }

            class Head implements Comparable<Head> {
                String name;

                public Head(String name) {
                    this.name = name.toUpperCase();
                }

                @Override
                public int compareTo(Head head) {
                    return this.name.compareTo(head.name);
                }
            }
        }
    }
}