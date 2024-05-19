import programmers.test.Solution;

public class TestMain {

    public static void main(String[] args) throws Exception {

        BaekJoon baekJoon = new BaekJoon();

        // 퇴사 (Silver 3)
        // https://www.acmicpc.net/problem/14501
        // baekJoon.setAnswer(new baekjoon.S3_14501.Main()).test();

        // 요세푸스 (Silver 4)
        // https://www.acmicpc.net/problem/1158
        // baekJoon.setAnswer(new baekjoon.S4_1158.Main()).test();

        // 암기왕 (Silver 4)
        // https://www.acmicpc.net/problem/2776
        // baekJoon.setAnswer(new baekjoon.S4_2776.Main()).test();

        // 좋은단어 (Silver 4)
        // https://www.acmicpc.net/problem/3986
        // baekJoon.setAnswer(new baekjoon.S4_3986.Main()).test();

        new Programmers<Integer>().setAnswer(new Solution()).test();
    }

}
