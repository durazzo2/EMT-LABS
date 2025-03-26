package mk.ukim.finki.mk.emtlab1.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import mk.ukim.finki.mk.emtlab1.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
