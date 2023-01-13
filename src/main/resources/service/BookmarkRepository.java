package antigravity.repository;

import antigravity.entity.Bookmark;
import antigravity.repository.querydsl.BookmarkRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long>, BookmarkRepositoryCustom {
}
