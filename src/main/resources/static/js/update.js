// (1) 회원정보 수정
function update(userId,event) {
    event.preventDefault(); //폼태그 액션을 막기

    let data = $("#profileUpdate").serialize();
    console.log(data);

    $.ajax({
        type: "put",
        url: "/api/user/" + userId,
        data: data,
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        dataType: "json"
    })
    //HttpStatus 상태코드 200번대
    .done(res=>{
        console.log("성공",res);
        location.href="/user/" + userId;
    })
    //HttpStatus 상태코드 200번대가 아닐때
    .fail(error=>{
        console.log("실폐",error);
        if(error.data == null){
            alert(error.responseJSON.message);
        }else{
            alert(JSON.stringify(error.responseJSON.data));
        }
    });
}