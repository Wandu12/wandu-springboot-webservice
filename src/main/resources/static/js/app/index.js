var main = {
    init : function () { //중복된 함수 이름이 발생하면 공용 공간인 브라우저의 스코프가 덮어쓰기될 수 있으므로
                        //객체를 따로 만들어 해당 객체 안에서만 function이 유효하도록 function을 선언하는 것
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('등록되었습니다.');
            window.location.href = '/'; //글 등록이 성공하면 메인페이지('/')로 이동함
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();