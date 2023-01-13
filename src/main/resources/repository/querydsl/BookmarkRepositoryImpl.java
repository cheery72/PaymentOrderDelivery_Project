package antigravity.repository.querydsl;

import antigravity.entity.QBookmark;
import antigravity.entity.QProduct;
import antigravity.entity.QProductHits;
import antigravity.entity.QUser;
import antigravity.payload.ProductResponse;
import antigravity.payload.QProductResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class BookmarkRepositoryImpl implements BookmarkRepositoryCustom{

    private static final QProduct qProduct = QProduct.product;
    private static final QUser qUser = QUser.user;
    private static final QBookmark qBookmark = QBookmark.bookmark;
    private static final QProductHits qProductHits = QProductHits.productHits;
    private final JPAQueryFactory jpaQueryFactory;

    public BookmarkRepositoryImpl(EntityManager entityManager){
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

//    페이징 된 상품 목록을 조회합니다.
//    liked 파라미터가 없으면 모든 상품을 조회하되 {user}가 찜한 상품에 liked: true를 추가하고
//    liked=false 이면 찜하지 않은 상품만 조회
//    liked=true 이면 {user}가 찜한 상품만 조회합니다.
//    잘못된 파라미터가 들어오면 400 Bad Request 로 응답합니다.
//    정상인 경우 200 OK 로 응답하며, 응답 본문은 antigravity.payload.ProductResponse 를 참고하여 작성합니다.
//    한 상품의 응답 json 명세는 기본적으로 다음과 같습니다. 아래 명세를 바탕으로 페이징 된 전체 응답 객체를 구성 해주세요.

//    private Long id; // 상품아이디
//    private String sku; // 상품 고유값
//    private String name; // 상품명
//    private BigDecimal price; // 가격
//    private Integer quantity; // 재고수량
//    private Boolean liked; // 필요한 경우 찜한 상품임을 표시 (찜 여부)
//    private Integer totalLiked; // 상품이 받은 모든 찜 개수
//    private Integer viewed; // 상품 조회 수
//    private LocalDateTime createdAt; // 상품 생성일시
//    private LocalDateTime updatedAt; // 상품 수정일시
    @Override
    public Page<ProductResponse> findByss(Boolean liked, Pageable pageable, Long userId) {
        BooleanExpression booleanExpression = eqLiked(liked, userId);
        System.out.println();
        List<ProductResponse> productResponses = jpaQueryFactory
                .select(new QProductResponse(
                        qProduct.id,
                        qProduct.sku,
                        qProduct.name,
                        qProduct.price,
                        qProduct.quantity,
//                        eqLiked(liked,userId).isNotNull(),
                        qProduct.id.count().intValue(),
                        qProductHits.hits.intValue(),
                        qProduct.createAt,
                        qProduct.updatedAt
                ))
                .from(qProduct)
                .leftJoin(qBookmark)
                .on(qBookmark.productId.eq(qProduct.id))
                .leftJoin(qProductHits)
                .on(qProductHits.productId.eq(qProduct.id))
                .where(eqLiked(liked,userId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(qProduct.id,
                        qProduct.sku,
                        qProduct.name,
                        qProduct.price,
                        qProduct.quantity,
//                        eqLiked(liked,userId).isNotNull(),
                        qProductHits.hits.intValue(),
                        qProduct.createAt,
                        qProduct.updatedAt)
                .fetch();

        return new PageImpl<>(productResponses,pageable,pageable.getOffset());
    }

    private BooleanExpression eqLiked(Boolean liked, Long userId){
        return liked == null ? null: liked ? qUser.id.eq(userId): qUser.id.notIn(userId);
    }

}
