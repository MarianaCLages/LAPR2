import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class TEstejaapago {
    public static void main(String[] args) throws ParseException {

        String strDate = "25-04-2021";

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        Date date = df.parse(strDate);
        System.out.println(checkAge(date));

    }

    private static Period checkAge(Date birthDate) {
        LocalDate date = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Transition from object Date to object LocalDate retrieved from https://www.baeldung.com/java-date-to-localdate-and-localdatetime
        return Period.between(date, LocalDate.now()); // Inspired in https://stackoverflow.com/a/1116138
    }

    private void checkBirthDate(Date birthDate, int age) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth Date cannot be null");
        }

    }
}
