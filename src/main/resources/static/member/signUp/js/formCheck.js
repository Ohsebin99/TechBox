// 아이디 중복 검사
const idCheck = () => {
    const id = $('#userId').val();
    console.log(id);
    $.ajax({
        url: '/member/id-check', // 서버의 엔드포인트 URL을 설정합니다.
        type: 'POST',     // POST 요청을 사용하여 데이터를 서버로 전송합니다.
        data: { userId: id },  // 서버로 보낼 데이터, 이 경우 userId를 전달합니다.
        success: function(res) {
          // 서버에서 응답을 받았을 때 처리하는 부분
          console.log("아작스 : ", res)

          if (res === "success") {
                  // 서버에서 ID가 유효하다고 응답한 경우
                  $('#idCheckMessage2').text('');
                  $('#idCheckMessage1').text('사용가능한 아이디 입니다.').css('color', 'green');
                  $('#idFormCheck').val('YES');
                } else {
                  // 서버에서 ID가 유효하지 않다고 응답한 경우
                  $('#idCheckMessage1').text('');
                  $('#idCheckMessage2').text('아이디가 이미 존재합니다.').css('color', 'red');
                  $('#idFormCheck').val('NO');
                }
        },
        error: function(err) {
          // AJAX 요청에 오류가 발생한 경우 처리하는 부분
          console.log("오류 발생: " + err);
        }
      });
}
// 닉네임 중복 검사
const nicknameCheck = () => {
    const nickname = $('#nickname').val();
//    const idFormCheck = $('#idFormCheck').val();
    $.ajax({
        url: '/member/nickname-check', // 서버의 엔드포인트 URL을 설정합니다.
        type: 'POST',     // POST 요청을 사용하여 데이터를 서버로 전송합니다.
        data: { nickname: nickname },  // 서버로 보낼 데이터, 이 경우 userId를 전달합니다.
        success: function(res) {
          // 서버에서 응답을 받았을 때 처리하는 부분
        console.log("아작스 서버 응답: ",res)

          if (res === "success") {
                  // 서버에서 ID가 유효하다고 응답한 경우
                  $('#nicknameCheckMessage2').text('');
                  $('#nicknameCheckMessage1').text('사용가능한 닉네임 입니다.').css('color', 'green');
                  $('#nicknameFormCheck').val('YES');
                } else {
                  // 서버에서 ID가 유효하지 않다고 응답한 경우
                  $('#nicknameCheckMessage1').text('');
                  $('#nicknameCheckMessage2').text('닉네임이 이미 존재합니다.').css('color', 'red');
                  $('#nicknameFormCheck').val('NO');
                }
        },
        error: function(err) {
          // AJAX 요청에 오류가 발생한 경우 처리하는 부분
          console.log("오류 발생: " + err);
        }
      });
}

// 다음 주소 API
function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
        	// 주소창 초기화
        	document.getElementById("address").value = '';
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                    // 조합된 참고항목을 주소변수에 넣는다.
                    addr += extraAddr;
                }
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("detailAddress").value = '';
            document.getElementById("detailAddress").focus();
        }
    }).open();
}

// formCheck
function formCheck(){
    const id = $('#idFormCheck').val();
    const nickname = $('#nicknameFormCheck').val();

    if(id === 'YES' && nickname === 'YES'){
        return true;
    }else{
    alert('내용을 다시 확인해주시기바랍니다.');
        return false;
    }
}

