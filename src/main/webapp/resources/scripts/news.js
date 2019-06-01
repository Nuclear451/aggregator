const newsContainer = document.getElementById('news_container');
const pageContainer = document.getElementById('pagination');

function createNewsElement(news) {
    return '<div class="media">' +
                '<div class="media-body">' +
                    '<h5 class="mt-0">' + news.title + '</h5>' +
                    news.text +

                '</div>' +
            '</div>' +
            '<hr/>'
}

function getAllNews(page = 0){
    $.ajax({
        url: '/news/list?page=' + page,
        dataType : "json",
        success: function (data) {
            while (newsContainer.firstChild) {
                newsContainer.removeChild(newsContainer.firstChild);
            }

            console.log(data);
            for (const news of data.content) {
                newsContainer.insertAdjacentHTML('beforeend', createNewsElement(news));
            }

            createPagination(data.totalPages, page);
        }
    });
}

function createPagination(totalPages, activePage) {
    while (pageContainer.firstChild) {
        pageContainer.removeChild(pageContainer.firstChild);
    }

    for (i = 1; i <= totalPages; i++){
        pageContainer.insertAdjacentHTML('beforeend', createPage(i, activePage + 1));
    }
}

function createPage(pageNumber, activePage) {
    let active = '';
    if (activePage === pageNumber) active = 'active';
    return '<li class="page-item ' + active +  '">' +
        '<a class="page-link" onclick="getAllNews(' + (pageNumber - 1) + ')">' + pageNumber + '</a></li>'
}

getAllNews();