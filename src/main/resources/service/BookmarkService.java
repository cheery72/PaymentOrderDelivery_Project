package antigravity.service;

import antigravity.entity.Bookmark;
import antigravity.entity.Product;
import antigravity.entity.User;
import antigravity.payload.ProductResponse;
import antigravity.repository.BookmarkRepository;
import antigravity.repository.ProductHitsRepository;
import antigravity.repository.ProductRepository;
import antigravity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductHitsRepository productHitsRepository;

    //        {user}가 존재하지 않거나 잘못된 {productId}로 요청을 했거나 이미 찜한 상품일 경우 400 Bad Request 로 응답합니다.
    //    user}가 {productId}를 찜 했다는 정보를 저장합니다.
//        {user}가 찜을 할 때마다 상품 조회 수도 1 증가합니다.
//        정상적으로 등록이 완료되면 201 Created 로 응답하며, 응답 본문은 자유롭게 구현할 수 있습니다.
    // Todo: user가 productId 정보 저장
    @Transactional
    public long createUserBookmark(Long productId, Long userId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("상품 정보가 없습니다."));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("유저 정보를 찾을 수 없습니다."));

        Bookmark bookmark = Bookmark.bookmarkBuilder(productId, user);

        bookmarkRepository.save(bookmark);
        Optional<ProductHits> optionalProductHits = productHitsRepository.findByProductId(productId);

        if(optionalProductHits.isPresent()){
            ProductHits productHits = optionalProductHits.get();
            return productHits.increaseHits(productHits.getHits());
        }

        ProductHits producthits = productHitsRepository.save(ProductHits.productHitsBuilder(productId));
        return producthits.getHits();

    }

//    [GET] /products?liked?liked={boolean}&page={integer}&size={integer}
//    페이징 된 상품 목록을 조회합니다.
//    liked 파라미터가 없으면 모든 상품을 조회하되 {user}가 찜한 상품에 liked: true를 추가하고
//    liked=false 이면 찜하지 않은 상품만 조회
//            liked=true 이면 {user}가 찜한 상품만 조회합니다.
//    잘못된 파라미터가 들어오면 400 Bad Request 로 응답합니다.
//    정상인 경우 200 OK 로 응답하며, 응답 본문은 antigravity.payload.ProductResponse 를 참고하여 작성합니다.
//    한 상품의 응답 json 명세는 기본적으로 다음과 같습니다. 아래 명세를 바탕으로 페이징 된 전체 응답 객체를 구성 해주세요.
    public Page<ProductResponse> getUserBookmark(Boolean liked, Pageable pageable, Long userId){
        return bookmarkRepository.findByss(liked,pageable,userId);

//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new NoSuchElementException("ss"));
//        List<Bookmark> byUserIn = bookmarkRepository.findByUser(user);
//
//        // liked=true 이면 {user}가 찜한 상품만 조회합니다.
//        if(liked){
//            return new PageImpl<>(byUserIn,pageable, pageable.getOffset()));
//        // liked=false 이면 찜하지 않은 상품만 조회
//        }else if (!liked){
//            List<Long> notProduct = new ArrayList<>();
//            for(Bookmark b : byUserIn){
//                notProduct.add(b.getProductId());
//            }
//            List<Product> allByIdNotIn = productRepository.findAllByIdNotIn(notProduct);
//            return new PageImpl<>(allByIdNotIn,pageable, pageable.getOffset()));
//
//    // liked 파라미터가 없으면 모든 상품을 조회하되 {user}가 찜한 상품에 liked: true를 추가하고
//        }else{
//            List<Product> all = productRepository.findAll();
//            List<Long> collect = byUserIn.stream().map(Bookmark::getProductId).collect(Collectors.toList());
//            List<ProductResponse> productResponses = new ArrayList<>();
//            for(Product p: all){
//                ProductResponse build;
//                if(collect.contains(p)){
//                     build = ProductResponse.builder()
//                            .id(p.getId())
//                            .sku(p.getSku())
//                            .name(p.getName())
//                            .price(p.getPrice())
//                            .quantity(p.getQuantity())
//                            .liked(true)
//                            .totalLiked((int) bookmarkRepository.findById(p.getId()).stream().count())
//                            .viewed((int) productHitsRepository.findAllByProductId(p.getId()).stream().count())
//                            .createdAt(p.getCreateAt())
//                            .updatedAt(p.getUpdatedAt())
//                            .build();
//                }else{
//                    build = ProductResponse.builder()
//                            .id(p.getId())
//                            .sku(p.getSku())
//                            .name(p.getName())
//                            .price(p.getPrice())
//                            .quantity(p.getQuantity())
//                            .liked(false)
//                            .totalLiked((int) bookmarkRepository.findById(p.getId()).stream().count())
//                            .viewed((int) productHitsRepository.findAllByProductId(p.getId()).stream().count())
//                            .createdAt(p.getCreateAt())
//                            .updatedAt(p.getUpdatedAt())
//                            .build();
//                }
//                productResponses.add(build);
//            }
//            return new PageImpl<>(productResponses,pageable, pageable.getOffset()));
//
//        }
    }
}
