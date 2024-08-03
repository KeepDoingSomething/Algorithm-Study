#include <iostream>
#include <algorithm>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
    string s, boom, tmp = "";

    cin >> s;
    cin >> boom;

    for(int i=0; i<s.size(); i++){
        tmp+=s[i];
        if(tmp.back() == boom.back()){
            bool ch = true;
            if(tmp.size() < boom.size()){
                continue;
                }
            for(int j =0; j<boom.size(); j++){
                if(tmp[tmp.size()-boom.size() + j] != boom[j]){
                    ch = false;
                    break;
                }
            }
            if(ch) {
                for (int j=0; j<boom.size(); j++){
                    tmp.pop_back();
                }
            }
        }
    }
    if(tmp.empty()){
        cout << "FRULA" << "\n";
    }else{
        cout << tmp << "\n";
    }
    return 0;
}