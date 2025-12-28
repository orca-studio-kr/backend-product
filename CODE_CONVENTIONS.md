# 코드 컨벤션 (CODE CONVENTIONS)
이 문서는 프로젝트의 코드 일관성을 유지하기 위한 규칙들을 정의합니다.

## 1. 네이밍 컨벤션 (Naming Convention)

- **패키지 (Package)**: 소문자, `.`으로 구분. `xyz.product.orca_studio.module.{domain}.{sub}`
- **클래스 (Class)**: 파스칼 케이스 (PascalCase).
  - **Controller**: `...Controller` (예: `ProductController`)
  - **Service**: `...Service` (예: `ProductQueryService`, `ProductCommandService`)
  - **Repository**: `...Repository` (예: `ProductRepository`)
  - **DTO**: `...Dto`, `...Request`, `...Response` (예: `ProductDetailResponse`)
  - **Entity**: `...` (예: `Product`)
  - **Exception**: `...Exception` (예: `ProductNotFoundException`)
- **메서드 (Method)**: 카멜 케이스 (camelCase).
- **변수 (Variable)**: 카멜 케이스 (camelCase).
- **상수 (Constant)**: 대문자 스네이크 케이스 (UPPER_SNAKE_CASE).

## 2. 패키지 구조 (Package Structure)

- `xyz.product.orca_studio`
  - `common`: 공통 모듈 (DTO, Exception, Util 등)
  - `config`: 설정 관련 클래스
  - `module`: 도메인별 모듈
    - `product`: '상품' 도메인
      - `api`: 외부와 통신하는 Controller, DTO
      - `application`: 비즈니스 로직 (Service)
        - `command`: CUD(Create, Update, Delete) 관련 로직
        - `query`: R(Read) 관련 로직
      - `domain`: 도메인 핵심 객체 (Entity, Repository, Domain Service)
      - `global`: 해당 모듈 내에서만 사용되는 공통 요소

## 3. 코드 스타일 (Code Style)

- **들여쓰기**: 4칸 스페이스.
- **최대 줄 길이**: 120자.
- **괄호**: `if`, `for`, `while` 문은 항상 중괄호 `{}`를 사용합니다.
- **주석**:
  - `//`: 한 줄 주석
  - `/** ... */`: Javadoc 주석. public 메서드와 클래스에는 설명을 추가하는 것을 권장합니다.
- **어노테이션 순서**:
  1.  JPA 관련 어노테이션 (`@Entity`, `@Id`, `@GeneratedValue`, ...)
  2.  Lombok 어노테이션 (`@Getter`, `@NoArgsConstructor`, ...)
  3.  Spring 관련 어노테이션 (`@RestController`, `@Service`, `@Autowired`, ...)
- **의존성 주입**: `@Autowired` 필드 주입보다는 생성자 주입을 사용합니다.
  ```java
  @RestController
  public class ProductController {
      private final ProductService productService;

      public ProductController(ProductService productService) {
          this.productService = productService;
      }
  }
  ```
- **Lombok 사용**: `@Data` 어노테이션 사용을 지양하고, 필요한 `@Getter`, `@Setter`, `@ToString` 등을 명시적으로 사용합니다. `@Setter`는 꼭 필요한 경우에만 최소한으로 사용합니다.

## 4. 기타

- **API 응답**: 모든 API 응답은 `CommRespDto`로 감싸서 반환하여 일관된 형식을 유지합니다.
- **Exception 처리**: `try-catch` 보다는 `@ControllerAdvice`를 이용한 전역 예외 처리를 지향합니다. 비즈니스 예외는 `RuntimeException`을 상속받은 커스텀 예외를 사용합니다.
