import java.util.*;

class Solution {
    List<Seeker> seekers = new ArrayList<>();
    List<Seeker> conditions = new ArrayList<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        parseInfo(info);
        parseQuery(query);
        count(answer);
        return answer;
    }

    private void parseInfo(String[] infos) {
        for (String info : infos) {
            String[] token = info.split(" ");
            Seeker seeker = new Seeker(
                    token[0],
                    token[1],
                    token[2],
                    token[3],
                    Integer.parseInt(token[4])
            );
            seekers.add(seeker);
        }
    }

    private void parseQuery(String[] queries) {
        for (String query : queries) {
            String[] token = query.replace(" and ", " ").split(" ");
            Seeker condition = new Seeker(
                    token[0],
                    token[1],
                    token[2],
                    token[3],
                    Integer.parseInt(token[4])
            );
            conditions.add(condition);
        }
    }

    private void count(int[] answer) {
        for (int i = 0; i < conditions.size(); i++) {
            int count = 0;
            Seeker condition = conditions.get(i);

            for (Seeker seeker : seekers) {
                if (seeker.checkCondition(seeker, condition)) {
                    count++;
                }
            }

            answer[i] = count;
        }
    }
}

class Seeker {
    String language;
    String position;
    String career;
    String food;
    int score;

    public Seeker(String language, String position, String career, String food, int score) {
        this.language = language;
        this.position = position;
        this.career = career;
        this.food = food;
        this.score = score;
    }

    public boolean checkCondition(Seeker seeker, Seeker condition) {
        boolean languageCheck = condition.language.equals("-") ||
                seeker.language.equals(condition.language);

        boolean positionCheck = condition.position.equals("-") ||
                seeker.position.equals(condition.position);

        boolean careerCheck = condition.career.equals("-") ||
                seeker.career.equals(condition.career);

        boolean foodCheck = condition.food.equals("-") ||
                seeker.food.equals(condition.food);

        boolean scoreCheck = seeker.score >= condition.score;

        return languageCheck && positionCheck && careerCheck &&
                foodCheck && scoreCheck;
    }
}