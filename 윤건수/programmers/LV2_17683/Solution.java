package programmers.LV2_17683;

public class Solution {

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int answerDuration = 0;

        for(String infos: musicinfos){
            String[] info = infos.split(",");
            int startTime = toMin(info[0]);
            int endTime = toMin(info[1]);
            int duration = endTime - startTime;
            String songName = info[2];
            String melody = info[3];
            if(isThatMelody(m, melody, duration) && duration > answerDuration){
                answerDuration = duration;
                answer = songName;
            };
        }
        return answer;
    }

    private int toMin(String time){
        String[] timeInfo = time.split(":");
        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }

    private boolean isThatMelody(String remember, String melody, int duration){

        // #제거 A#, G#, F#, D#, C#, #B
        // H   I   J   K   L  M
        melody = melodyConvert(melody);
        remember = melodyConvert(remember);

        StringBuilder radio = new StringBuilder();
        if(melody.length() >= duration){
            radio.append(melody.substring(0, duration));
        }else{
            int index = 0;
            while(radio.length() != duration){
                radio.append(melody.charAt(index % melody.length()));
                index++;
            }
        }

        return radio.indexOf(remember) >= 0;
    }

    private String melodyConvert(String melody){
        melody = melody.replace("A#", "H");
        melody = melody.replace("G#", "I");
        melody = melody.replace("F#", "J");
        melody = melody.replace("D#", "K");
        melody = melody.replace("C#", "L");
        melody = melody.replace("B#", "M");
        return melody;
    }

}