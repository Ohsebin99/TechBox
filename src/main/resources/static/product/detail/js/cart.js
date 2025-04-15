// 장바구니 코드
$(document).on('click', '.cart-btn', (e) => {
    let cart = $(e.currentTarget);
    const sumPrice = $(".sumPrice");
    let sumPriceValue = sumPrice.data("sum-price") || cart.data("product-price");
    let cleanedPrice = typeof sumPriceValue === 'string' ? sumPriceValue.replace(/[^0-9]/g, "") : sumPriceValue;
    const product = {
            productId: cart.data("product-id"),
            productName: cart.data("product-name"),
            price: parseInt(cleanedPrice),
            productImage: cart.data("product-image"),
            quantity: parseInt($(".quantity").val())
        };
    const idIndex = cart.data("id-index");

    console.log("아이디 : ", idIndex, ", ", "상품 : ", product);

        // 장바구니 추가
        axios.post("/insert/cart", {
            productId: product.productId,
            productName: product.productName,
            price: product.price,
            productImage: product.productImage,
            quantity: product.quantity,
            idIndex: idIndex
        }, {
            headers: {'Content-Type': 'application/json'}
        })
        .then(response => {
            console.log("정상 작동:", response.data);
            if(response.data === "success"){
                alert("장바구니에 추가 되었습니다.");
            }else{
                alert("이미 장바구니에 존재합니다.");}

            // 장바구니 개수 갱신 (응답을 받은 후 호출)
            getCartCount(cart);
        })
        .catch(error => {
            console.error("에러 발생:");
        });
});

// 공통 함수로 추출
function getCartCount(cart) {
    const idIndex = cart.data("id-index");

    // 현재 장바구니 개수
    if(idIndex){
    axios.get("/count/cart", { params: { idIndex } })
        .then(response => {
            $("#cartCount").text(response.data);
        })
        .catch(() => {});
       }

}

// 페이지 로드 시 실행
$(document).ready(() => {

     $('.cart-btn').each(function () {
        getCartCount($(this));
    });
});



// 🔹 뒤로 가기 시 최신화 적용 (pageshow 이벤트 추가)
window.addEventListener("pageshow", function(event) {
    if (event.persisted) {
        $('.cart-btn').each(function () {
            getCartCount($(this)); // 뒤로 가기 시 좋아요 상태 및 개수 갱신
        });
    }
});

// 수량 코드 ===

// 수량 감소
$(document).on('click', '.decreaseQuantity', (e) => {
    const cnt = $(".quantity");
    let decreaseBtn = $(e.currentTarget);
    let value = parseInt(cnt.val());
    if (parseInt(cnt.val()) > 1) {
        cnt.val(value - 1);
        }
    const sumPrice = $(".sumPrice");
    let price = (parseInt(sumPrice.data("product-price")) * cnt.val()).toLocaleString();
    sumPrice.text(price + "원");
    sumPrice.data("sum-price", price); // 수량 증,감소에 따른 가격을 저장해 상단 코드로 보내기
});

// 수량 증가
$(document).on('click', '.increaseQuantity', (e) => {
    const cnt = $(".quantity");
    let decreaseBtn = $(e.currentTarget);
    let value = parseInt(cnt.val());

    if (parseInt(cnt.val()) < 30) {
       cnt.val(value + 1);
    }
    const sumPrice = $(".sumPrice");
    let price = (parseInt(sumPrice.data("product-price")) * cnt.val()).toLocaleString();
    sumPrice.text(price + "원");
    sumPrice.data("sum-price", price); // 수량 증,감소에 따른 가격을 저장해 상단 코드로 보내기
});



