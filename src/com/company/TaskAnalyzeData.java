package com.company;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.OptionalDouble;

public class TaskAnalyzeData {
    public void start(){
        Collection<Person> people = Arrays.asList(
                new Person("Вася", 16, Sex.MAN),
                new Person("Петя", 23, Sex.MAN),
                new Person("Елена", 42, Sex.WOMAN),
                new Person("Иван Иванович", 69, Sex.MAN)
        );

        System.out.println("Военнообязанные:");
        people.stream().filter(x -> x.getSex() == Sex.MAN).filter(x -> x.getAge() >= 18 && x.getAge() <= 27).forEach(System.out::println);

        System.out.println("Средний возраст мужчин:");
        // Если одни только женщины, исключение вылетает, потому что пустое значение хотим конвертировать в double.
        OptionalDouble avg = people.stream().filter(x -> x.getSex() == Sex.MAN).mapToInt(x -> x.getAge()).average();
        System.out.println(avg.isPresent() ? (new DecimalFormat("0.00")).format(avg.getAsDouble()) : "no man");

        System.out.println("Кол-во потенциально работоспособных людей:");
        System.out.println(people.stream().
                filter(x -> x.getAge() >= 18 && (x.getSex() == Sex.MAN ? x.getAge() < 65 : x.getAge() < 60)).
                count());

    }
}
