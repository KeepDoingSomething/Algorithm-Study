#include <iostream>
#include <vector>

using namespace std;

int N;
long long ret;
vector<int> stack;

int main()
{
    ios::sync_with_stdio(0), cin.tie(0);
    cin >> N;
    stack.reserve(N);
    for (int i = 0, height; i < N; ++i)
    {
        cin >> height;
        while (!stack.empty() && stack.back() <= height)
        {
            stack.pop_back();
        }
        ret += stack.size();
        
        stack.push_back(height);
    }
    cout << ret;
    return 0;
}