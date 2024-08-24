/**
 * Author    : HojipKim
 * Date      : 2024.08.24(Sat)
 * Runtime   : 27.73 ms
 * Memory    : 83.4 MB
 * Algorithm : 정렬
 */

class Main{

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(new String[]{"!F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"});
    }
}

class Node{
    public int index;
    public String str;

    public Node(int index, String str) {
        this.index = index;
        this.str = str;
    }
}

class Solution {
    public String[] solution(String[] files) {
        String[] answer;

        List<String[]> arr = new ArrayList<>();

        for(String file : files) {
            Node fileNode = new Node(0, file);

            Node headNode = findHead(fileNode);
            Node numberNode = findNumber(new Node(headNode.index, file));
            Node tailNode = findTail(new Node(numberNode.index, file));

            String[] nodeArray = new String[3];

            nodeArray[0] = headNode.str;
            nodeArray[1] = numberNode.str;
            nodeArray[2] = tailNode.str;

            arr.add(nodeArray);
        }

        arr.sort(Comparator.comparing((String[] array) -> array[0].toLowerCase()).thenComparing(array -> Integer.parseInt(array[1])));

        answer = new String[arr.size()];
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < arr.size(); i++) {
            for (int j = 0; j < arr.get(i).length; j++) {
                str.append(arr.get(i)[j]);
            }
            answer[i] = str.toString();
            str.setLength(0);
        }

        return answer;
    }

    public static Node findHead(Node file){
        StringBuffer head = new StringBuffer("");

        Node headNode = null;

        int i = 0;
        for ( i = file.index; i < file.str.length(); i++) {
            // file[i]가 숫자가 아니라면
            if(48 > file.str.charAt(i) || 57 < file.str.charAt(i) ){
                head.append(file.str.charAt(i));
            }
            else{
                break;
            }
        }
        return new Node(i, head.toString());
    }

    public static Node findNumber(Node node) {
        StringBuffer number = new StringBuffer("");

        int i = 0;
        for (i = node.index; i < node.str.length(); i++) {
            // node의 string이 number라면
            if (48 <= node.str.charAt(i) && 57 >= node.str.charAt(i)) {
                number.append(node.str.charAt(i));
            } else {
                break;
            }
        }
        return new Node(i, number.toString());
    }


    public static Node findTail(Node node){
        StringBuffer tail = new StringBuffer("");

        for (int i = node.index; i < node.str.length(); i++) {
            tail.append(node.str.charAt(i));
        }

        return new Node(0, tail.toString());
    }

}

