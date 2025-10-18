package lv1calculator;

import java.util.Scanner;

/**

Lv 1.클래스 없이 자바의 기본 문법만을 사용하여 구현한 계산기

[요약]
a. 계산기는 2개의 숫자를 받을 수 있고 사칙연산 될 문자를 받을 수 있다.
b. 계산기는 exit을 입력할 때까지 계속해서 값을 받고 연산 결과를 변환한다.

 1) 양의 정수(0 포함) 입력받기
 2) 사칙연산(+ , - , * , /) 입력받기
 3) 위에서 입력 받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력하기
 4) 반복문을 사용하되, 반복의 종료를 알려주는 "exit" 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 수정하기

 */
public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) { // 전체 계산 반복
            System.out.println("====== 사칙연산 계산기 ======");

            // 입력 받은 수 저장할 변수 선언
            int num1, num2;
            // 연산자 저장할 변수 선언
            char operator;
            // 연산 결과 저장할 변수 선언
            int result;

            // 첫 번째 숫자 입력
            while (true) {
                System.out.print("첫 번째 숫자를 입력하세요 (0 이상): ");
                num1 = sc.nextInt();
                // 양의 정수일때만 반복문 탈출
                if (num1 >= 0) {
                    break;
                }
                System.out.println(" 0 이상의 양의 정수를 입력해야 합니다.");
            }

            // 두 번째 숫자 입력
            for (;;) {
                System.out.print("두 번째 숫자를 입력하세요 (0 이상): ");
                num2 = sc.nextInt();
                if (num2 >= 0) {
                    break;
                }
                System.out.println(" 0 이상의 양의 정수를 입력해야 합니다.");
            }

            System.out.print("사칙연산 기호를 입력하세요 : ");
            operator = sc.next().charAt(0);

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                        continue; // while문 건너뛰어서 다시 숫자 및 연산자 입력
                    }
                    result = num1 / num2;
                    break;
                default:
                    System.out.println("잘못된 연산 기호 입니다. (+, -, *, / 중 입력하세요)");
                    continue; // 잘못된 연산자 입력시에도 재입력
            }

            System.out.println("결과: " + result);

            // 반복 여부 확인 (exit 입력해야만 계산기 종료)
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료) : ");
            String exit = sc.next(); // 종료할 문자열 입력

            if(exit.equalsIgnoreCase("exit")) {
                System.out.println("==== 사칙연산 계산기 종료 ====");
                break;
            }
        }
        sc.close(); // 입력 닫기
    }
}