// 수량 감소
$(document).on('click', '.decreaseQuantity', (e) => {
    const row = $(e.currentTarget).closest("tr"); // 클릭된 버튼의 tr
    const cnt = row.find(".quantity"); // 해당 tr 내 수량 input
    let decreaseBtn = $(e.currentTarget);
    let idIndex = decreaseBtn.data("id-index");
    let productId = decreaseBtn.data("product-id");
    let value = parseInt(cnt.val());
    if (parseInt(cnt.val()) > 1) {
        cnt.val(value - 1);
        }
//  변경된 수량 화면에 보이기
    const sumPrice = $(e.currentTarget).closest('tr').find(".sumPrice");
    let price = (parseInt(sumPrice.data("product-price")) * cnt.val()).toLocaleString();
    sumPrice.text(price + "원");
    updateTotalPrice()
//  변경된 수량을 DB에 업데이트
    const quantity = cnt.val();
    let lastPrice = price.replace(/[^0-9]/g, "");
    console.log("뱅뱅", idIndex, productId, quantity);

    axios.post("/insert/cart", {
        productId: productId,
        idIndex: idIndex,
        quantity: quantity,
        sumPrice: parseInt(lastPrice)
    }, {
        headers: {'Content-Type': 'application/json'}
    })
    .then(response => {
        console.log("정상 작동:", response.data);
    })
    .catch(error => {
        console.error("에러 발생:");
    });

});

// 수량 증가
$(document).on('click', '.increaseQuantity', (e) => {
    const row = $(e.currentTarget).closest("tr"); // 클릭된 버튼의 tr
    const cnt = row.find(".quantity"); // 해당 tr 내 수량 input
    let increaseBtn = $(e.currentTarget);
    const idIndex = increaseBtn.data("id-index");
    const productId = increaseBtn.data("product-id");
    let value = parseInt(cnt.val());
    if (parseInt(cnt.val()) < 30) {
       cnt.val(value + 1);
    }
    // 변경된 수량을 화면에 보이게하기
    const sumPrice = $(e.currentTarget).closest('tr').find(".sumPrice");
    let price = (parseInt(sumPrice.data("product-price")) * cnt.val()).toLocaleString();
    sumPrice.text(price + "원");
    updateTotalPrice()
    // 변경된 수량을 DB에 업데이트
    const quantity = cnt.val();
    let lastPrice = price.replace(/[^0-9]/g, "");
    console.log("뱅뱅", idIndex, productId, quantity,price);
    // 장바구니 추가
    axios.post("/insert/cart", {
        productId: productId,
        idIndex: idIndex,
        quantity: quantity,
        sumPrice: parseInt(lastPrice)
    }, {
        headers: {'Content-Type': 'application/json'}
    })
    .then(response => {
        console.log("정상 작동:", response.data);
    })
    .catch(error => {
        console.error("에러 발생:");
    });

});

// 체크박스 상태 변경 시 총 합계 변경
$(document).ready(function () {
    // 체크박스 상태 변경 시 총합 다시 계산
    $(document).on('change', '.product-checkbox', updateTotalPrice);

    // 처음부터 총합을 보여주고 싶다면 여기도 호출 가능
    updateTotalPrice();
});


// 체크박스로 인한 총 합계 변경
function updateTotalPrice() {
    let total = 0;

    $('.product-checkbox:checked').each(function () {
            const row = $(this).closest('tr'); // 체크된 상품의 tr
            const price = parseInt(row.find('.sumPrice').data('product-price')); // 개당 가격
            const quantity = parseInt(row.find('.quantity').val()); // 수량
            total += price * quantity;
        });

    $('#totalPrice').text(total.toLocaleString() + "원");
}

$(document).on('change', '.all-checkbox', function () {
    if ($(this).is(':checked')){
        $('.product-checkbox').prop('checked', true).trigger('change');
    } else {
        $('.product-checkbox').prop('checked', false).trigger('change');
    }
});
