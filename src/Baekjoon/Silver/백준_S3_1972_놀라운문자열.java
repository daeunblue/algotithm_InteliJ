package Baekjoon.Silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 백준_S3_1972_놀라운문자열 {
    static String input;
    static HashSet<String> hset = new HashSet<>(); // HashSet : 동일한 문자열을 넣으려고하면 하나만들어간다!!!!!!
    static List<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean flag = false;

            while((input =br.readLine())!=null){
                if(input.equals("*")) break; // * 입력 종료

                list.clear();
                hset.clear();

                for(int i=0; i<input.length()-1; i++){
                    for(int k=0; k<input.length()-i-1; k++){
                        String dPair = Character.toString(input.charAt(k)) + Character.toString(input.charAt(k+i-1));
                        list.add(dPair);
                        hset.add(dPair);
                    }
                    if(list.size() != hset.size()){ // 놀라운 문자열 X
                        flag = false;
                        break;
                    }
                }

                if(flag) System.out.println(input+"is surprising.");
                else System.out.println(input +"is NOT surprising.");
        }
    }
}
