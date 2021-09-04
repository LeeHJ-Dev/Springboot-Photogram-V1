/**
	2. 스토리 페이지
	(1) 스토리 로드하기
	(2) 스토리 스크롤 페이징하기
	(3) 좋아요, 안좋아요
	(4) 댓글쓰기
	(5) 댓글삭제
*/

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
                           <button><i class="fas fa-heart active" id="storyLikeIcon-1" onclick="toggleLike()"></i></button>
                       </div>
                       <span class="like"><b id="storyLikeCount-1">3</b>likes</span>

                       <div class="sl__item__contents__content"><p>${u.caption}</p></div>

                       <!-- 댓글 리스트 -->
                       <div id="storyCommentList-1">
                           <div class="sl__item__contents__comment" id="storyCommentItem-1"">
                               <p><b>Lovely :</b> 부럽습니다.</p>
                               <button><i class="fas fa-times"></i></button>
                           </div>
                       </div>

                       <!-- 댓글 달기 -->
                       <div class="sl__item__input">
                           <input type="text" placeholder="댓글 달기..." id="storyCommentInput-1" />
                           <button type="button" onClick="addComment()">게시</button>
                       </div>

                   </div>
               </div>`;
    return item;
}

// (2) 스토리 스크롤 페이징하기
$(window).scroll(() =>{
    console.log("스크롤중");

    console.log("윈도우 scrollTop ",$(window).scrollTop());
    console.log("윈도우 문서의 높이",$(document).height());
    console.log("윈도우 높이",$(window).height());

    let checkSum = $(window).scrollTop() - ($(document).height() - $(window).height());
    console.log("계산된 값 ", checkSum);

    if(checkSum <1 && checkSum > -1){
        console.log("스크롤이벤트 발생");
        page++;
        storyLoad();
    }

});


// (3) 좋아요, 안좋아요
function toggleLike() {
	let likeIcon = $("#storyLikeIcon-1");
	if (likeIcon.hasClass("far")) {
		likeIcon.addClass("fas");
		likeIcon.addClass("active");
		likeIcon.removeClass("far");
	} else {
		likeIcon.removeClass("fas");
		likeIcon.removeClass("active");
		likeIcon.addClass("far");
	}
}

// (4) 댓글쓰기
function addComment() {

	let commentInput = $("#storyCommentInput-1");
	let commentList = $("#storyCommentList-1");

	let data = {
		content: commentInput.val()
	}

	if (data.content === "") {
		alert("댓글을 작성해주세요!");
		return;
	}

	let content = `
			  <div class="sl__item__contents__comment" id="storyCommentItem-2""> 
			    <p>
			      <b>GilDong :</b>
			      댓글 샘플입니다.
			    </p>
			    <button><i class="fas fa-times"></i></button>
			  </div>
	`;
	commentList.prepend(content);
	commentInput.val("");
}

// (5) 댓글 삭제
function deleteComment() {

}







