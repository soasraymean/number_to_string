package app;

import processor.AbstractProcessor;
import processor.DefaultProcessor;

import java.util.Scanner;
/*
    Перевод числа в цифровой записи в строковую. Например 134345 будет "сто
тридцать четыре тысячи триста сорок пять". * Учесть склонения - разница
в окончаниях (к примеру, две и два).
    Алгоритм должен работать для сколько угодно большого числа, соответственно,
значения степеней - миллион, тысяча, миллиад и т.д. - должны браться их
справочника, к примеру, текстового файла.
    Обязательно создать Data Driven Test (я, как пользователь, должен иметь
возможность ввести множество наборов 1.число 2.правильный эталонный результат,
 тест самостоятельно проверяет все наборы и говорит, что неверное), который
 доказывает, что Ваш алгоритм работает правильно. Использовать JUnit.
    По возможности, применить ООП.
 */

public class Application {

    public static void main(String[] args) {

        AbstractProcessor processor = new DefaultProcessor();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Введите число: ");
//            long num = sc.nextLong();
            String num = sc.nextLine();

            System.out.println(num + " = " + processor.getName(num));
        }
    }
}