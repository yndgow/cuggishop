/**
 * 날짜 : 2023년 10월 6일
 * 이름 : 김지홍
 * 내용 : 상품 상세정보 페이지 함수
 */
  $(function () {
	// 상품 이미지 슬라이드 (slick 라이브러리)
    $('.variable-width').slick({
      infinite: true, // 무한 반복옵션
      speed: 500, // 다음버튼 클릭시 다음 화면 뜨는데 걸리는시간(ms)
      variableWidth: true,
      arrows: true,
      prevArrow: "<button type='button' class='slick-prev slick-arrow'>Previous</button>",
      nextArrow: "<button type='button' class='slick-next slick-arrow'>Next</button>",
    });
    
    // TODO 장바구니 기능
    $('.add-cart').on('click', function () {
		let isLogin = $('#is-login').val();
		if(!isLogin){
			alert("로그인을 하셔야 합니다.");
			location.href='/user/signIn';
			return false;
		}
		
       	let productId = $('.add-cart').attr('id');
		let url = "/order/addProduct/"+productId;

           $.ajax(url, {
               type: "get",
               headers: {
                   "Content-Type": "application/json"
               }
           }).done((res) => {
				   let moveBasket = confirm("장바구니로 이동하시겠습니까?");
				   if(moveBasket){
					   window.location.href="/order/basket";
				   }
           });
	});

	// 상품 일반 정보 클릭시 슬라이드 함수
    $('.extend-info').on('click', function (e) {
      let slideNum = $(this).data('target');
      let curSlide = $('#' + slideNum);
      let iconElement = $(this).find('i'); // 클릭한 요소 내의 아이콘

      iconElement.removeClass('fa-plus').addClass('fa-minus');

      curSlide.slideToggle(400, function () {
        if (curSlide.is(':visible')) {
          iconElement.removeClass('fa-plus').addClass('fa-minus');
        } else {
          iconElement.removeClass('fa-minus').addClass('fa-plus');
        }
      });

      $('.extend-info')
        .not(this)
        .each(function () {
          let otherIconElement = $(this).find('i');
          otherIconElement.removeClass('fa-minus').addClass('fa-plus');
        });
      $('.info-more').not(curSlide).slideUp(400);
    });
    
  });