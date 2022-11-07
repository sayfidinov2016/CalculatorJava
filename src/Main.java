import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Opertion opertion =new Opertion();
        System.out.println("Введите числа:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        opertion.oper(input);
        System.out.println(calculator.cals(input));
    }

    static class Opertion{
        public static String oper(String input){
            char[] element =input.toCharArray();
            char counter =0;
            for(int i=0; i<input.length(); i++){
                if(element[i] == '+'){
                    counter = (char) (counter+1);
                }
                if(element[i] == '-'){
                    counter = (char) (counter+1);
                }
                if(element[i] == '*'){
                    counter = (char) (counter+1);
                }
                if(element[i] == '/'){
                    counter = (char) (counter+1);
                }
            }
            if(counter>1){
                try {
                    throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return input;
        }
    }

    static class Calculator{//
        public static String cals(String input){
            Convert convert = new Convert();
            String[] operator= { "+","-","/","*"};
            String[] sing ={"\\+","-","/","\\*"};
            int index = -1;
            for(int i=0; i<operator.length; i++){
                if (input.contains(operator[i])){
                    index = i;
                    break;
                }
            }
            if (index == -1){
                throw new RuntimeException("Неправильный оператор!");
            }
            String[] numbers = input.split(sing[index]);
            if(convert.roman(numbers[0]) == convert.roman(numbers[1])) {
                int a, b;
                boolean romam = convert.roman(numbers[0]);
                if(romam){
                    a=convert.romanToInt(numbers[0]);
                    b=convert.romanToInt(numbers[1]);
                } else {
                    a = Integer.parseInt(numbers[0]);
                    b = Integer.parseInt(numbers[1]);
                }
                CheckMoreLess checkMoreLess = new CheckMoreLess();
                checkMoreLess.check(a);
                checkMoreLess.check(b);
                int s = 0;
                switch (operator[index]) {
                    case "+":
                        s = a + b;
                        break;
                    case "-":
                        s = a - b;
                        break;
                    case "/":
                        s = a / b;
                        break;
                    case "*":
                        s = a * b;
                        break;
                    default:
                        System.out.println("Ошибка!");
                }

                if(romam){
                    checkMoreLess.zero(s);
                    input = "" + convert.arabicToRoman(s);
                } else {
                    input = "" + s;
                }
            } else {
                try {
                    throw new Exception("Используются одновременно разные системы счисления!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return input;
        }
    }

    static class CheckMoreLess {
        public static int check(int i) {
            if (i < 1 || i > 10) {
                try {
                    throw new Exception("Только числа 1 до 10!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return i;
        }

        public static int zero(int i) {
            if (i <= 0) {
                try {
                    throw new Exception("В римской системе нет отрицательных чисел!");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            return i;
        }
    }
}