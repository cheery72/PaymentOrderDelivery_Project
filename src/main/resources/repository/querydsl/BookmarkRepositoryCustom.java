package antigravity.repository.querydsl;

import antigravity.payload.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookmarkRepositoryCustom {
    Page<ProductResponse> findByss(Boolean liked, Pageable pageable, Long userId);
}
    public static Bookmark bookmarkBuilder(Long productId, User user){
        return Bookmark.builder()
                .productId(productId)
                .user(user)
                .build();
    }