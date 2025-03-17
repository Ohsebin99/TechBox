$(document).on('click', '.red__heart', (e) => {
    let heart = $(e.currentTarget);
    const productId = heart.data("product-id");
    const idIndex = heart.data("id-index");


    if (heart.val() == '0') {
        heart.val(1);
        heart.addClass('filled');

        // 좋아요 활성화
        axios.post("/insert/like", { productId, idIndex },
            { headers: {'Content-Type': 'application/json'} })
            .then(() => getLikeCountCheck(heart)) // 요청 성공 시 찜 개수 업데이트
            .catch(error => console.error("좋아요 실패:", error));

    } else {
        heart.val(0);
        heart.removeClass('filled');

        // 좋아요 취소
        axios.delete("/delete/like", { data: { productId, idIndex }, headers: {'Content-Type': 'application/json'} })
            .then(() => getLikeCountCheck(heart)) // 요청 성공 시 찜 개수 업데이트
            .catch(error => console.error("찜 취소 실패:", error));
    }
});

// 공통 함수로 추출
function getLikeCountCheck(heart) {
    const idIndex = heart.data("id-index");
    const productId = heart.data("product-id");

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
        getLikeCountCheck($(this));
    });
});

// 🔹 뒤로 가기 시 최신화 적용 (pageshow 이벤트 추가)
window.addEventListener("pageshow", function(event) {
    if (event.persisted) {
        $('.red__heart').each(function () {
            getLikeCountCheck($(this)); // 뒤로 가기 시 좋아요 상태 및 개수 갱신
        });
    }
});
