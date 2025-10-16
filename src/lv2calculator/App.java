package lv2calculator;

import java.util.Scanner;

/**

Lv.2 클래스를 적용해 기본적인 연산을 수행할 수 있는 계산기 만들기

 [요약]
 a. 계산된 결과 값들을 기록하는 컬렉션을 만든다.
 b. 컬렉션의 가장 먼저 저장된 데이터를 삭제하는 기능을 만든다.

 1) 사칙연산을 수행 후, 결과값 반환 메서드 구현 & 연산 결과를 저장하는 컬렉션 타입 필드를 가진 Calculator 생성
 2) Lv.1에서 구현한 App 클래스의 main 메서드에 Calculator 클래스가 활용될 수 있도록 수정
 */
public class App {
    public static void main(String[] args) {
        // Calculator 객체 생성
        Calculator calculator = new Calculator();
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

            /**
             * Calculator 객체를 이용해서 연산수행
             *  - 잘못된 나눗셈 및 잘못된 연산자 오류 발생할 수 있어서 try-catch 구문 사용
             */
            try {
                // 연산 정상 실행
                result = calculator.calculate(num1, num2, operator);
                System.out.println("결과: " + result);
            } catch (Exception e) {
                // 잘못된 나눗셈 및 잘못된 연산자 사용시 오류 문구 출력
                System.out.println(e.getMessage());
                // 무한 루프를 위해 continue; 적용
                continue;
            }

            // 반복 여부 확인 (exit 입력해야만 계산기 종료)
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료) : ");
            String exit = sc.next(); // 종료할 문자열 입력

            if(exit.equals("exit")) {
                System.out.println("==== 사칙연산 계산기 종료 ====");
                break;
            }
        }
        sc.close(); // 입력 닫기
    }
}