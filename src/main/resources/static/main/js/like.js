$(document).on('click', '.red__heart', (e) => {
    let heart = $(e.currentTarget);
    const productId = heart.data("product-id");
    const idIndex = heart.data("id-index");


    if (heart.val() == '0') {
        heart.val(1);
        heart.addClass('filled');

        // 찜하기 활성화
        axios.post("/insert/like", { productId, idIndex },
            { headers: {'Content-Type': 'application/json'} })
            .then(() => getLikeCount(heart)) // 요청 성공 시 찜 개수 업데이트
            .catch(error => console.error("찜하기 실패:", error));

    } else {
        heart.val(0);
        heart.removeClass('filled');

        // 찜하기 취소
        axios.delete("/delete/like", { data: { productId, idIndex }, headers: {'Content-Type': 'application/json'} })
            .then(() => getLikeCount(heart)) // 요청 성공 시 찜 개수 업데이트
            .catch(error => console.error("찜 취소 실패:", error));
    }
});

// 공통 함수로 추출
function getLikeCount(heart) {
    const idIndex = heart.data("id-index");
    const productId = heart.data("product-id");

    // 현재 찜 개수
    if(idIndex){
    axios.get("/count/like", { params: { idIndex } })
        .then(response => {
            $("#likeCount").text(response.data);
        })
        .catch(() => {});
       }

        // 찜하기 유지
        if (idIndex) {
            axios.get("/check/like", {
                params: { productId, idIndex }
            }).then(response => {
                if (response.data === true) {
                    heart.val(1).addClass('filled'); // 빨간색으로 변경
                } else {
                    heart.val(0).removeClass('filled'); // 회색으로 변경
                }
            }).catch(() => {});
        }
}

// 페이지 로드 시 실행
$(document).ready(() => {
    $('.red__heart').each(function () {
        getLikeCount($(this));
    });
});
