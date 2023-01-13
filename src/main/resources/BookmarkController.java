package antigravity.controller;


import antigravity.service.BookmarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    // TODO 찜 상품 등록 API

    @PostMapping("/liked/{userId}/{productId}")
    public ResponseEntity<Object> createdUserBookmark(
            @PathVariable Long userId,
            @PathVariable Long productId){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookmarkService.createUserBookmark(productId,userId));
    }

    // TODO 찜 상품 조회 API
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserBookmark(
            @PathVariable Long userId,
            @RequestParam(required = false) Boolean liked,
            Pageable pageable){

        return ResponseEntity
                .ok(bookmarkService.getUserBookmark(liked,pageable,userId));
    }

}
