import java.util.Scanner;

class Main {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return a / b;
    }

    public static boolean isRomanNumeral(String str) {
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!(c == 'I' || c == 'X' || c == 'V')) {
                return false;
            }
        }
        return true;
    }

    public static int findArithmeticOperatorIndex(String str) {
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '*' || c == '+' || c == '-' || c == '/') {
                index = i;
                break;
            }
        }
        return index;
    }

    public static int convertToArabic(String num) {
        int result = 0;
        int prevValue = 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            char c = num.charAt(i);
            int value;
            switch (c) {
                case 'I':
                    value = 1;
                    break;
                case 'V':
                    value = 5;
                    break;
                case 'X':
                    value = 10;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Roman numeral");
            }
            if (value < prevValue) {
                result -= value;
            } else {
                result += value;
            }
            prevValue = value;
        }
        return result;
    }

    public static String convertToRoman(int result) {
        if (result < 2 || result > 100) {
            throw new IllegalArgumentException("Результат выходит за пределы диапазона (от 2 до 100)");
        }

        StringBuilder romanNumeral = new StringBuilder();
        int[] arabicValues = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        int i = 0;
        while (result > 0) {
            if (result - arabicValues[i] >= 0) {
                romanNumeral.append(romanSymbols[i]);
                result -= arabicValues[i];
            } else {
                i++;
            }
        }

        return romanNumeral.toString();
    }

    public static void calc(String input) {
        int index = findArithmeticOperatorIndex(input);
        String firstNum = input.substring(0, index).trim();
        String secondNum = input.substring(index + 1).trim();
        boolean isRoman = isRomanNumeral(firstNum) && isRomanNumeral(secondNum);
        int result = 0;

        if (!isRoman && (Integer.parseInt(firstNum) < 1 || Integer.parseInt(firstNum) > 10 || Integer.parseInt(secondNum) < 1 || Integer.parseInt(secondNum) > 10)) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }

        if (isRoman) {
            int first = convertToArabic(firstNum);
            int second = convertToArabic(secondNum);

            switch (input.charAt(index)) {
                case '+':
                    result = add(first, second);
                    break;
                case '-':
                    result = subtract(first, second);
                    break;
                case '*':
                    result = multiply(first, second);
                    break;
                case '/':
                    result = divide(first, second);
                    break;
                default:
                    throw new IllegalArgumentException("Недопустимый арифметический оператор");
            }

            if (result < 2 || result > 100) {
                throw new IllegalArgumentException("Результат выходит за пределы диапазона (от 2 до 100)");
            }
            System.out.println(convertToRoman(result));
        } else {
            int first = Integer.parseInt(firstNum);
            int second = Integer.parseInt(secondNum);

            switch (input.charAt(index)) {
                case '+':
                    result = add(first, second);
                    break;
                case '-':
                    result = subtract(first, second);
                    break;
                case '*':
                    result = multiply(first, second);
                    break;
                case '/':
                    result = divide(first, second);
                    break;
                default:
                    throw new IllegalArgumentException("Недопустимый арифметический оператор");
            }

            if (result < 2 || result > 100) {
                throw new IllegalArgumentException("Результат выходит за пределы диапазона (от 2 до 100)");
            }
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String expression = input.nextLine();
        calc(expression);
    }
}
