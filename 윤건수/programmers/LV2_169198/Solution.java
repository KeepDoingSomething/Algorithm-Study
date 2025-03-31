package programmers.LV2_169198;

public class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        Point start = new Point(startX, startY);
        int[] answer = new int[balls.length];
        for(int i = 0; i < balls.length; i++){
            Point ball = new Point(balls[i][0], balls[i][1]);
            int minDist = Integer.MAX_VALUE;

            for(int j = 4; j < 8; j++){
                Point point = convertPoint(ball, j, m, n);
                if(isValid(j, start, point, ball, m, n)){
                    int dist = start.calcDist(point);
                    minDist = Math.min(dist, minDist);
                };
            }

            answer[i] = minDist;
        }

        return answer;
    }

    private boolean isValid(int direction, Point point1, Point point2, Point ball, int m, int n){
        boolean isBetweenX = ball.y == point1.y
                && (ball.x < Math.max(point1.x, point2.x))
                && (ball.x > Math.min(point1.x, point2.x));
        boolean isBetweenY = ball.x == point1.x
                && (ball.y < Math.max(point1.y, point2.y))
                && (ball.y > Math.min(point1.y, point2.y));
        return !isBetweenX && !isBetweenY;

    }

    private Point convertPoint(Point point, int line, int m, int n){
        int x = 0;
        int y = 0;

        switch(line){
            case 4: // 좌
                x = 0 - point.x;
                y = point.y;
                break;
            case 5: // 위
                x = point.x;
                y = n + (n - point.y);
                break;
            case 6: // 우
                x = m + (m - point.x);
                y = point.y;
                break;
            case 7: // 하
                x = point.x;
                y = 0 - point.y;
                break;
        }

        return new Point(x, y);
    }

    public static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int calcDist(Point o){
            return (int)Math.pow((this.x - o.x), 2) + (int)Math.pow((this.y - o.y), 2);
        }
    }
}