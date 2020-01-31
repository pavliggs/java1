package ru.progwards.java1.lessons.io2;

public class Translator {
    private String[] inLang;
    private String[] outLang;

    Translator(String[] inLang, String[] outLang) {
        this.inLang = inLang;
        this.outLang = outLang;
    }

    //метод фильтрации слова (убираются все ненужные символы, кроме букв и происходит перевод в нижний регистр)
    public static String getFilterWord(String word) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch: word.toCharArray()) {
            if (Character.isAlphabetic(ch))
                stringBuilder.append(Character.toLowerCase(ch));
        }
        return stringBuilder.toString();
    }

    public String translate(String sentence) {
        StringBuilder stringBuilderMain = new StringBuilder();
        //разбиваем строку на массив слов
        String[] strArr = sentence.split(" ");
        //будем сверять слова в массиве strArr со словами в массиве inLang на равенство, используя вложенные циклы
        for (int i = 0; i < strArr.length; i++) {
            for (int j = 0; j < inLang.length; j++) {
                /*если одно из слов в массиве strArr совпадает с одним из слов в массиве inLang, то завершаем
                * внутренний цикл и переходим к следующей итерации внешнего цикла*/
                if (getFilterWord(strArr[i]).equals(inLang[j])) {
                    //присваиваем переменной первый символ слова из массива strArr
                    char firstSymbolWord = strArr[i].toCharArray()[0];
                    //присваиваем переменной последний символ слова из массива strArr
                    char lastSymbolWord = strArr[i].toCharArray()[strArr[i].length() - 1];
                    //проверяем, находится ли первый символ в нижнем регистре
                    if (Character.isLowerCase(firstSymbolWord)) {
                        //дополнительно проверяем, является ли последний символ буквой
                        if (Character.isAlphabetic(lastSymbolWord))
                            //записываем в StringBuilder слово из выходного массива
                            stringBuilderMain.append(outLang[j] + " ");
                        else {
                            /*если последний символ не является буквой, то записываем в StringBuilder слово из
                            * выходного массива + этот символ*/
                            stringBuilderMain.append(outLang[j] + lastSymbolWord + " ");
                        }
                    }
                    //проводим те же самые проверки, но только уже если первый символ находится в верхнем регистре
                    else {
                        if (Character.isAlphabetic(lastSymbolWord))
                            /*записываем в StringBuilder слово из выходного массива, но уже разбиваем его на подстроки
                            * получаем первый символ и переводим его в верхний регистр и прибавляем оставшиеся символы*/
                            stringBuilderMain.append(outLang[j].substring(0, 1).toUpperCase() +
                                    outLang[j].substring(1) + " ");
                        else {
                            //тоже самое, но только в конце добавляем последний символ из слова массива strArr
                            stringBuilderMain.append(outLang[j].substring(0, 1).toUpperCase() +
                                    outLang[j].substring(1) + lastSymbolWord + " ");
                        }
                    }
                    break;
                }
                /*если равных слов в массивах не найдено и все итерации внутреннего цикла закончены, то просто
                * добавляем в StringBuilder слово из массива strArr (будет не переведено)*/
                if (j == inLang.length - 1) {
                    stringBuilderMain.append(strArr[i] + " ");
                }
            }
        }
        //выводим строку убрав все лишние пробелы
        return stringBuilderMain.toString().trim();
    }

    public static void main(String[] args) {
        Translator trans = new Translator(new String[]{"привет", "я", "тебя", "мир", "люблю"},
                new String[]{"hello", "i", "you", "world", "love"});
        System.out.println(trans.translate("Привет, мир!"));
    }
}
