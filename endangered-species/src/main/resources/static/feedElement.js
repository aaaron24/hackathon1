Slim.tag(
    "post-feed",
    `<div s:id = "container">

    </div>`,
    class PostFeed extends Slim {
        constructor() {
            super();
            this.posts = [];
            this.status = false;
        }

        onRender() {
            let roomId = this.getAttribute("data-roomid");
            this.container.innerHTML = "";
            fetch(`/posts/${roomId}`)
                .then(res => res.json())
                .then(data => {
                    for (var post of data) {
                        if (post.imageUrl.trim()!=="") {
                            this.container.innerHTML += `    <div class="post-container">
                            <div class="card" style="width: 18rem; min-height: 12rem">
                                <img class="card-img-top" alt="..." src="${post.imageUrl}"></img>
                                <div class="card-body">
                                <h5 class="card-title">${post.title}</h5>
                                <p class="card-text">
                                ${post.description}
                                <p><i style="float: left;" class="mdi mdi-thumb-up"></i> <span style="float: right">${post.likes} Likes</span></p>
                                </p>
                            </div>
                        </div>`
                        }
                        else {
                            this.container.innerHTML += `    <div class="post-container">
                            <div class="card" style="width: 18rem;">
                                <div class="card-body">
                                <h5 class="card-title">${post.title}</h5>
                                <p class="card-text">${post.description}</p>
                                <p><i style="float: left; font-size: 40px" class="mdi mdi-thumb-up"></i> <span style="float: right" class="likes-amount">${post.likes} Likes</span></p>
                            </div>
                        </div>`
                        let icon = this.container.querySelectorAll(".mdi-thumb-up")[this.container.querySelectorAll(".mdi-thumb-up").length-1];
                        let likesAmount = this.container.querySelectorAll(".likes-amount")[this.container.querySelectorAll(".likes-amount").length-1];

                        console.log(likes, likesAmount);
                        
                        icon.onclick = function(event) {
                            if (icon.style.color!="blue") { 
                                icon.style.color="blue";
                                likesAmount.innerText = parseInt(likesAmount.innerText)+1;
                            }
                            else {
                                icon.style.color = "black";
                                likesAmount.innerText = parseInt(likesAmount.innerText)-1;
                            }

                        }
                        }
                    }
                });
        }

    });

    function like() {

    }