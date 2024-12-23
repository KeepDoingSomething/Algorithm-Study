package programmers.LV2_340211;

import java.util.*;

public class Solution {
    public int solution(int[][] points, int[][] routes) {
        int robotCnt = routes.length;
        int arriveCnt = 0;

        Robot[] robots = new Robot[robotCnt];
        for(int i = 0; i < robotCnt; i++){
            int[] robotRoute = routes[i];
            robots[i] = new Robot(robotRoute, points);
        }

        boolean checkStart = false;
        int answer = 0;
        HashMap<Point, Integer> check = new HashMap<>();
        while(arriveCnt != robotCnt){
            arriveCnt = 0;
            check.clear();
            for(int i = 0; i < robots.length; i++){
                Robot robot = robots[i];
                if(robot.isArrived()){
                    arriveCnt++;
                    continue;
                }

                if(checkStart){
                    robot.move();
                }

                check.compute(robot.curPoint, (key, value) -> value == null ? 1 : value + 1);
            }

            for(Integer sameCnt : check.values()){
                if(sameCnt > 1) answer++;
            }

            if(!checkStart) checkStart = true;
        }

        return answer;
    }

    private class Point{
        int r;
        int c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode(){
            return r + (c * 1000);
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Point){
                Point other = (Point) o;
                return this.r == other.r && this.c == other.c;
            }else{
                return false;
            }
        }
    }

    private class Robot{
        Point curPoint;
        List<Point> routePoints;
        int curMovePointIdx;

        Robot(int[] robotRoute, int[][] points){
            routePoints = new ArrayList<>();
            curMovePointIdx = 0;
            int totalRoute = robotRoute.length;
            for(int i = 0; i < totalRoute; i++){
                int[] routePointInfo = points[robotRoute[i] - 1];
                Point routePoint = new Point(routePointInfo[0], routePointInfo[1]);
                if(i == 0){
                    curPoint = new Point(routePointInfo[0], routePointInfo[1]);
                }
                routePoints.add(routePoint);
            }
        }

        boolean isArrived(){
            int endMovePointIdx = routePoints.size() - 1;
            Point endPoint = routePoints.get(endMovePointIdx);
            return curMovePointIdx == endMovePointIdx && this.curPoint.equals(endPoint);
        }

        void move(){
            if(isArrived()) return;

            Point curMovePoint = routePoints.get(curMovePointIdx);
            if(curMovePoint.equals(curPoint)){
                curMovePointIdx = Math.min(curMovePointIdx + 1, routePoints.size() - 1);
                curMovePoint = routePoints.get(curMovePointIdx);
            }

            if(curPoint.r != curMovePoint.r){
                if(curPoint.r > curMovePoint.r){
                    curPoint.r--;
                }else{
                    curPoint.r++;
                }
            }else if(curPoint.c != curMovePoint.c){
                if(curPoint.c > curMovePoint.c){
                    curPoint.c--;
                }else{
                    curPoint.c++;
                }
            }
        }
    }

}