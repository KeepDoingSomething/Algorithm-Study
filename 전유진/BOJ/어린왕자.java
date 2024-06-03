import java.util.Scanner;


/**
 * Author    : 전유진
 * Date      : 2024.06.03(월)
 * Runtime   : 192ms
 * Memory    : 18008KB
 * Algorithm : 구현
 * 직접 풀지 x
 */
public class 어린왕자 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            String base = sc.nextLine();
            int x_start = Integer.parseInt(base.split(" ")[0]);
            int y_start = Integer.parseInt(base.split(" ")[1]);

            int x_end = Integer.parseInt(base.split(" ")[2]);
            int y_end = Integer.parseInt(base.split(" ")[3]);

            int through = 0;
            int count = sc.nextInt();
            sc.nextLine();
            for (int j = 0; j < count; j++) {
                String circle = sc.nextLine();

                int x = Integer.parseInt(circle.split(" ")[0]);
                int y = Integer.parseInt(circle.split(" ")[1]);
                int r = Integer.parseInt(circle.split(" ")[2]);

                boolean hasStartContain = hasContain(x_start, y_start, x, y, r);
                boolean hasEndContain = hasContain(x_end, y_end, x, y, r);

                // 해당 행성이 출발 혹은 도착점 중 하나만을 포함할 경우
                if (!(hasStartContain && hasEndContain) && (hasStartContain || hasEndContain)) {
                    through++;
                }


            }
        }
    }
    private static boolean hasContain(int xo, int yo, int x, int y, int r)
    {
        return Math.sqrt(Math.pow(xo - x, 2) + Math.pow(yo - y, 2)) < r;
    }



    }
