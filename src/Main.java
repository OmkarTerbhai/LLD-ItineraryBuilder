import java.util.Date;

public class Main {
    public static void main(String[] args) {
       Itinerary itinerary = Itinerary.builder()
               .travellerName("Omkar Terbhai")
               .origin("PUN")
               .destination("GUW")
               .startDate(new Date())
               .endDate(new Date())
               .budget(40000)
               .build();

        System.out.println(itinerary);
    }
}