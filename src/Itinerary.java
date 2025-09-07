import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Itinerary {

    private String travellerName;
    private String origin;
    private String destination;
    private Date startDate;
    private Date endDate;
    private double budget;
    private List<Segment> segments;

    private Itinerary(ItineraryBuilder builder) {
        validateSegments(builder.segments);
        this.segments = new ArrayList<>(segments);
        this.travellerName = builder.travellerName;
        this.origin = builder.origin;
        this.destination = builder.destination;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.budget = builder.budget;
    }

    private void validateSegments(List<Segment> segments) {
        List<Date> departureDates = segments.stream().map(Segment::departAt).toList();

        for(int i = 0; i < segments.size()-1; i++) {
            if(departureDates.get(i).after(departureDates.get(i+1))) {
                throw new IllegalArgumentException("Departure date should be in increasing order");
            }
            else if(segments.get(i).to().equals(segments.get(i+1).from())) {
                throw new IllegalArgumentException("Departure date should be in increasing order");
            }
            else if(segments.get(i).departAt().after(segments.get(i+1).arriveAt())) {
                throw new IllegalArgumentException("Cannot depart after arrival time at next segment");
            }
        }
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
        public ItineraryBuilder() {
            segments = new ArrayList<>();
        }



        private String travellerName;
        private String origin;
        private String destination;
        private Date startDate;
        private Date endDate;
        private double budget;
        private List<Segment> segments;

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

        public ItineraryBuilder addSegment(Segment... segments) {
            for(Segment seg : segments) {
                if(seg.from() != null && !seg.from().isEmpty() &&
                        seg.to() != null && !seg.to().isEmpty() &&
                        seg.arriveAt() != null && seg.departAt() != null) {

                    this.segments.add(seg);
                }
                else
                    throw new IllegalArgumentException("All fields is a travel segment are mandatory");
            }

            return this;
        }

        public Itinerary build() {
            return new Itinerary(this);
        }
    }
}
