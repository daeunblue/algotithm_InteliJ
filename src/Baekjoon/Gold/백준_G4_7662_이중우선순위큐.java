package Baekjoon.Gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 백준_G4_7662_이중우선순위큐 {
    static int T, Q;
    static TreeMap<Integer,Integer> map = new TreeMap<>();
    static boolean isEmpty;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int test_case=0; test_case<T; test_case++){
            Q = Integer.parseInt(br.readLine());
            isEmpty = false;
            for(int i=0; i<Q; i++){
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                int input = Integer.parseInt(st.nextToken());


                if(str.equals("I")){
                    // getOrDefault(val, defaultval)  -> val이 map에 있다면, 해당 숫자의 갯수를 반환해주고, 없으면 defaultValue 반환
                    // -> 즉, map에 존재하는 val의 수만큼 반환됨-> +1을 함으로써 해당 key값의 갯수 저장해두기
                    map.put(input, map.getOrDefault(input,0)+1);
                }
                else{
                    if(map.isEmpty())continue;
                    // 제거 하기전에 체크해야할 것 -> 동일 숫자가 2개 이상인 경우 1개만 제거해야한다(주의)
                    int removeVal = ( input==1 ? map.lastKey() : map.firstKey() ); // 제거해야할 값

                    if(map.get(removeVal)==1){
                        map.remove(removeVal);
                    } else {
                        map.put(removeVal, map.get(removeVal)-1); // 제거할 값이 두개 이상인 경우 -> 1개만 삭제하기
                    }
                }
            }
            if(map.isEmpty()) sb.append("EMPTY\n");
            else{
                sb.append(map.lastKey() +" "+ map.firstKey()+"\n");
            }
            map.clear();
        }
        System.out.println(sb.toString());
    }
}
