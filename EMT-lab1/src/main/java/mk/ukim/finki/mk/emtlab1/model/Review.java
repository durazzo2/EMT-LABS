package mk.ukim.finki.mk.emtlab1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Double rating;



    public Review() {

    }

    @ManyToOne
    private Accomodation accommodation;

    public void setAccommodation(Accomodation accomodation){
        if (this.accommodation != null) {
            this.accommodation.getReviews().remove(this);
        }

        // Set the new accommodation
        this.accommodation = accommodation;

        // Add this review to the new accommodation's list
        if (accommodation != null && !accommodation.getReviews().contains(this)) {
            accommodation.getReviews().add(this);
        }
    }

}
