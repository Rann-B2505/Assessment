package util;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;


public final class StringsProvider {

    private static final AtomicReference<Faker> faker = new AtomicReference<>(new Faker(Locale.ENGLISH));

    public static String getRandomFirstName() {
        return faker.get().name().firstName();
    }

    public static String getRandomLastName() {
        return faker.get().name().lastName();
    }

    public static String getRandomPostalCode() { return faker.get().number().digits(6);}

}