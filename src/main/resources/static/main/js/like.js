$(document).on('click', '.red__heart', (e) => {
    let heart = $(e.currentTarget);
    const productId = heart.data("product-id");
    const idIndex = heart.data("id-index");

    console.log("클릭 : ", heart.val());

    console.log("상품 아이디 : ", productId, " 유저아이디 : ", idIndex);

    if(heart.val() == '0') {
        heart.val(1);
        heart.addClass('filled'); // filled 클래스 추가
    } else {
        heart.val(0);
        heart.removeClass('filled'); // filled 클래스 제거
    }

    // 좋아요 취소
    if (heart.val() == 0) {
        axios({
            method: "delete",
            url: "/delete/like",
            data: {
                productId: productId,
                idIndex: idIndex
            },
            dataType: "json",
            headers: {'Content-Type': 'application/json'}
        })
    }

    // 좋아요 활성화
    if (heart.val() == 1) {
        axios({
            method: "post",
            url: "/insert/like",
            data: {
                productId: productId,
                idIndex: idIndex
            },
            dataType: "json",
            headers: {'Content-Type': 'application/json'}
        })
    }
});

// 찜 여부 확인
$(document).ready(() => {
    $('.red__heart').each(function() {
        const heart = $(this);
        const productId = heart.data("product-id");
        const idIndex = heart.data("id-index");

        axios({
            method: "get",
            url: "/check/like",
            params: {
                productId: productId,
                idIndex: idIndex
            }
        }).then(response => {
            if (response.data === true) {
                heart.val(1).addClass('filled');  // 빨간색으로 변경
            } else {
                heart.val(0).removeClass('filled');  // 회색으로 변경
            }
        });
    });
});
