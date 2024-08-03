#include <string>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {
	long long answer = 0;
	
	stack<int> st1, st2;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < deliveries[i]; j++) {
			st1.push(i + 1);
		}
		for (int k = 0; k < pickups[i]; k++) {
			st2.push(i + 1);
		}
	}

	int top1, top2;

	while (!st1.empty() || !st2.empty()) {
		top1 = top2 = 0;
        for (int i = 0; i < cap; i++) {
			if (!st1.empty()) {
				if (i == 0) {
					top1 = st1.top();
				}
				st1.pop();
			}

			if (!st2.empty()) {
				if (i == 0) {
					top2 = st2.top();
				}
				st2.pop();
			}
		}
		answer += max(top1, top2) * 2;
		
	}
	
	return answer;
}