package antigravity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductHitsRepository extends JpaRepository<ProductHits,Long> {
    Optional<ProductHits> findByProductId(Long productId);

    List<ProductHits> findAllByProductId(Long productId);
}
