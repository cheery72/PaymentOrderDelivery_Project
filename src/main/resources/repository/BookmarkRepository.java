package antigravity.repository;

import antigravity.entity.Bookmark;
import antigravity.entity.User;
import antigravity.repository.querydsl.BookmarkRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long>, BookmarkRepositoryCustom {
    List<Bookmark> findByUser(User user);
}
