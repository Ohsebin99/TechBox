$(document).on('click', '.cart-btn', (e) => {
    let cart = $(e.currentTarget);
    const productId = cart.data("product-id");
    const idIndex = cart.data("id-index");

    console.log("아이디 : ", idIndex, ", ", "상품 : ", productId);

        // 장바구니 추가
        axios({
            method: "post",
            url: "/insert/cart",
            data: {
                productId: productId,
                idIndex: idIndex
            },
            dataType: "json",
            headers: {'Content-Type': 'application/json'}
        })

});

// 공통 함수로 추출
function getLikeCountCheck(cart) {
    const idIndex = cart.data("id-index");
    const productId = cart.data("product-id");

    // 현재 좋아요 개수
    if(idIndex){
    axios.get("/count/like", { params: { idIndex } })
        .then(response => {
            $("#likeCount").text(response.data);
        })
        .catch(() => {});
       }

        // 좋아요 유지
        if (idIndex) {
            axios.get("/check/like", {
                params: { productId, idIndex }
            }).then(response => {
                if (response.data === true) {
                    cart.val(1).addClass('filled'); // 빨간색으로 변경
                } else {
                    cart.val(0).removeClass('filled'); // 회색으로 변경
                }
            }).catch(() => {});
        }
}

// 페이지 로드 시 실행
$(document).ready(() => {
    $('.red__cart ').each(function () {
        getLikeCountCheck($(this));
    });
});

// 🔹 뒤로 가기 시 최신화 적용 (pageshow 이벤트 추가)
window.addEventListener("pageshow", function(event) {
    if (event.persisted) {
        $('.red__cart').each(function () {
            getLikeCountCheck($(this)); // 뒤로 가기 시 좋아요 상태 및 개수 갱신
        });
    }
});
