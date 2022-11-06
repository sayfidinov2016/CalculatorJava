import java.util.TreeMap;

public class Convert {
    TreeMap<Character, Integer> romainArabicMAp = new TreeMap<>();
    TreeMap<Integer, String> arabicRomanMap = new TreeMap<>();
    public Convert(){
        romainArabicMAp.put('I', 1);
        romainArabicMAp.put('V', 5);
        romainArabicMAp.put('X', 10);
        romainArabicMAp.put('L', 50);
        romainArabicMAp.put('C', 100);
        romainArabicMAp.put('D', 500);
        romainArabicMAp.put('M', 10000);

        arabicRomanMap.put(1000, "M");
        arabicRomanMap.put(900, "CM");
        arabicRomanMap.put(500, "D");
        arabicRomanMap.put(400, "CD");
        arabicRomanMap.put(100, "C");
        arabicRomanMap.put(90, "LC");
        arabicRomanMap.put(50, "L");
        arabicRomanMap.put(40, "XL");
        arabicRomanMap.put(10, "X");
        arabicRomanMap.put(5, "V");
        arabicRomanMap.put(4, "IV");
        arabicRomanMap.put(1, "I");
    }

    public boolean roman(String numbers){
        return romainArabicMAp.containsKey(numbers.charAt(0));
    }

    public int romanToInt(String numbers){
        int index = numbers.length()-1;// XII длина 2
        char[] elements = numbers.toCharArray();//на масив X I I
        int arabic;
        int result = romainArabicMAp.get(elements[index]);//последний элемент I - 1
        for(int i = index-1; i>=0; i--){ // index 0
            arabic = romainArabicMAp.get(elements[i]); //итерации I - 1, X-10
            if(arabic < romainArabicMAp.get(elements[i + 1])){ //условие равенства 1<1, 10<1
                result =result - arabic;
            }else{
                result = result + arabic; // 2, 12
            }
        }
        return result;// 12
    }

    public String arabicToRoman(int number){// 12
        String result = "";
        int arabic;
        do {
            arabic = arabicRomanMap.floorKey(number);// поиск близкого значения 10,      1,             1
            result = result + arabicRomanMap.get(arabic); // в стору + пару 10 - X,      +I,            +I
            number = number - arabic;//              остаток 12-10=2,                    2-1=1,         1-1=0
        } while (number != 0 );//                            2=0 прохом еще итерацию,    1=0 еще ит.,   0=0 цикл завершон
        return result;//XII
    }
}
