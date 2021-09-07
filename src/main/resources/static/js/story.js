/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
*/

let principalId = $("#principalId").val();
let page = 0;

// (1) 스토리 로드하기
function storyLoad() {
    $.ajax({
        type: "get",
        url: `/api/image?page=${page}`,
        dataType: "json"
    }).done(res=>{
        console.log("성공", res);
        res.data.content
            .forEach(u=>{
                let item = getStoryItem(u);
                $("#storyList").append(item);
        })
    }).fail(error=>{
        console.log("실패", error);
    });
}
storyLoad();

function getStoryItem(u) {
    let item =`<!--전체 리스트 아이템-->
               <div class="story-list__item">
                   <!-- 헤더 -->
                   <div class="sl__item__header">
                       <div><img class="profile-image" src="#" onerror="this.src='/images/person.jpeg'" /></div>
                       <div>${u.user.name}</div>
                   </div>

                   <!-- 바디 -->
                   <div class="sl__item__img">
                       <img src="/upload/${u.postImageUrl}" />
                   </div>

                   <div class="sl__item__contents">
                       <div class="sl__item__contents__icon">

                           <button>`;
                                if(u.likeState){
                                    item += `<i class="fas fa-heart active" id="storyLikeIcon-${u.id}" onclick="toggleLike(${u.id})"></i>`
                                }else{
                                    item += `<i class="far fa-heart" id="storyLikeIcon-${u.id}" onclick="toggleLike(${u.id})"></i>`
                                }
                   item+= `</button>
                       </div>
                       <span class="like"><b id="storyLikeCount-${u.id}">${u.likeCount}</b>likes</span>

                       <div class="sl__item__contents__content"><p>${u.caption}</p></div>

                       <!-- 댓글 리스트 -->
                       <div id="storyCommentList-${u.id}">`;

                            u.comments.forEach((comment)=>{
                                item+=`<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
                                          <p><b>${comment.user.username} :</b> ${comment.content}</p>`;
                                          if(comment.user.id == principalId){
                                            item+=`<button onclick="deleteComment(${comment.id})"><i class="fas fa-times"></i></button>`
                                          }

                                item+=`</div>`;
                            });

                       item+= `</div>

                       <!-- 댓글 달기 -->
                       <div class="sl__item__input">
                           <input type="text" placeholder="댓글 달기..." id="storyCommentInput-${u.id}" />
                           <button type="button" onClick="addComment(${u.id})">게시</button>
                       </div>

                   </div>
               </div>`;
    return item;
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() =>{
    //console.log("윈도우 scrollTop ",$(window).scrollTop());
    //console.log("윈도우 문서의 높이",$(document).height());
    //console.log("윈도우 높이",$(window).height());

    let checkSum = $(window).scrollTop() - ($(document).height() - $(window).height());
    //console.log("계산된 값 ", checkSum);
    if(checkSum <1 && checkSum > -1){
        console.log("스크롤이벤트 발생");
        page++;
        storyLoad();
    }

});


// (3) 좋아요, 안좋아요
function toggleLike(imageId) {
	let likeIcon = $(`#storyLikeIcon-${imageId}`);
	if (likeIcon.hasClass("far")) {
	    //좋아요
	    $.ajax({
	        type: "post",
	        url: `/api/image/${imageId}/like`,
	        dataType: "json"
	    }).done(res=>{
	        console.log("좋아요 성공",res);

	        //좋아요 건수 체크
	        let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
	        let likeCount = Number(likeCountStr)+1;
	        $(`#storyLikeCount-${imageId}`).text(likeCount);


	        //하트 변경
            likeIcon.addClass("fas");
            likeIcon.addClass("active");
            likeIcon.removeClass("far");
	    }).fail(error=>{
	        console.log("좋아요 실패",error);
	    });
	} else {
	    //좋아요취소
	    $.ajax({
	        type: "delete",
	        url: `/api/image/${imageId}/like`,
	        dataType: "json"
	    }).done(res=>{
	        console.log("좋아요취소 성공",res);

            //좋아요 건수 체크
            let likeCountStr = $(`#storyLikeCount-${imageId}`).text();
            let likeCount = Number(likeCountStr)-1;
            $(`#storyLikeCount-${imageId}`).text(likeCount);

            likeIcon.removeClass("fas");
            likeIcon.removeClass("active");
            likeIcon.addClass("far");
	    }).fail(error=>{
	        console.log("좋아요취소 실패",error);
	    });
	}
}

// (4) 댓글쓰기
function addComment(imageId) {

	let commentInput = $(`#storyCommentInput-${imageId}`);
	let commentList = $(`#storyCommentList-${imageId}`);

    //전송할 데이터
	let data = {
	    imageId: imageId,
		content: commentInput.val()
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

    // 댓글 등록
    $.ajax({
        type: "post",
        url: "/api/comment",
        data: JSON.stringify(data),
        contentType: "application/json;charset=utf-8",
        dataType: "json"
    }).done(res=>{
        console.log("성공",res);

        //댓글 등록 시 댓글 item 추가
        let comment = res.data;
	    let content =  `<div class="sl__item__contents__comment" id="storyCommentItem-${comment.id}">
			                <p><b>${comment.user.username} :</b>${comment.content}</p>
			                <button onclick="deleteComment(${comment.id})"><i class="fas fa-times"></i></button>
			            </div>`;
	    commentList.prepend(content);
    }).fail(error=>{
        console.log("실패",error);
    });
	commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment(commentId) {
    $.ajax({
        type: "delete",
        url: `/api/comment/${commentId}`,
	    dataType: "json"

    }).done(res=>{
        console.log("댓글삭제성공",res);
        $(`#storyCommentItem-${commentId}`).remove();
    }).fail(error=>{
        console.log("댓글삭제실패",error);
    });
}







