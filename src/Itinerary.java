import java.util.Date;

public class Itinerary {

    private String travellerName;
    private String origin;
    private String destination;
    private Date startDate;
    private Date endDate;
    private double budget;

    private Itinerary(ItineraryBuilder builder) {
        this.travellerName = builder.travellerName;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.budget = builder.budget;
    }
    public static ItineraryBuilder builder() {
        return new ItineraryBuilder();
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "travellerName='" + travellerName + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budget=" + budget +
                '}';
    }

    static class ItineraryBuilder {
        public ItineraryBuilder() {}



        private String travellerName;
        private String origin;
        private String destination;
        private Date startDate;
        private Date endDate;
        private double budget;

        public ItineraryBuilder travellerName(String name) {
            if(name != null && !name.isEmpty())
                this.travellerName = name;
            else
                throw new IllegalArgumentException("Traveller Name cannot be empty");

            return this;
        }

        public ItineraryBuilder origin(String org) {
            if(org != null && !org.isEmpty()) {
                if (org.length() != 3)
                    throw new IllegalArgumentException("Invalid Origin");
                else
                    this.origin = org;
            }
            else
                throw new IllegalArgumentException("Origin cannot be empty");

            return this;
        }

        public ItineraryBuilder destination(String dest) {
            if(dest != null && !dest.isEmpty()) {
                if (dest.length() != 3)
                    throw new IllegalArgumentException("Invalid Origin");
                else
                    this.destination = dest;
            }
            else
                throw new IllegalArgumentException("Destination cannot be empty");

            return this;
        }

        public ItineraryBuilder startDate(Date date) {
            this.startDate = date;
            return this;
        }

        public ItineraryBuilder endDate(Date date) {
            this.endDate = date;
            return this;
        }

        public ItineraryBuilder budget(double budget) {
            if(budget != 0.0D) {
                this.budget = budget;
            }
            else
                throw new IllegalArgumentException("Budget cannot be zero");
            return this;
        }

        public Itinerary build() {
            return new Itinerary(this);
        }
    }
}
