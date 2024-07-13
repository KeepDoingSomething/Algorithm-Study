#include <string>
#include <vector>

using namespace std;

void hanoi(int n, int start, int target, vector<vector<int>>& answer){
    if (n == 1){
        answer.push_back({start, target});
        return;
    }
    hanoi(n-1, start, 6-start-target, answer);
    hanoi(1, start, target, answer);
    hanoi(n-1, 6-start-target, target, answer);
    
}

vector<vector<int>> solution(int n) {
    vector<vector<int>> answer;
    hanoi(n, 1, 3, answer);
    
    return answer;
}