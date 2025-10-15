package lv1calculator;

import java.util.Scanner;

/**

Lv 1.클래스 없이 자바의 기본 문법만을 사용하여 구현한 계산기

[요약]
a. 계산기는 2개의 숫자를 받을 수 있고 사칙연산 될 문자를 받을 수 있다.
b. 계산기는 exit을 입력할 때까지 계속해서 값을 받고 연산 결과를 변환한다.

1) master -> main 브랜치 변경 및 클래스명 App으로 수정
2) 양의 정수(0 포함) 입력받기

 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num1, num2;

        // 첫 번째 숫자 입력
        while(true){
            System.out.print("첫 번째 숫자를 입력하세요 (0 이상): ");
            num1 = sc.nextInt();
            // 양의 정수일때만 반복문 탈출
            if(num1 >= 0){
                break;
            }
            System.out.println(" 0 이상의 양의 정수를 입력해야 합니다.");
        }

        // 두 번째 숫자 입력
        for(;;){
            System.out.print("두 번째 숫자를 입력하세요 (0 이상): ");
            num2 = sc.nextInt();
            if(num2 >= 0){
                break;
            }
            System.out.println(" 0 이상의 양의 정수를 입력해야 합니다.");
        }

        System.out.println("입력된 첫 번쨰 숫자 : " + num1);
        System.out.println("입력된 두 번째 숫자 : " + num2);

        sc.close(); // 입력 자원 해제
    }
}