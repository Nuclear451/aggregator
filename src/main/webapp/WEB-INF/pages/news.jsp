<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>News</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand">Aggregator</a>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/news">News</a>
                </li>
                <li class="nav-item">
                    <input class="form-control mr-sm-2" id="news_seach" type="search" placeholder="Search" aria-label="Search">
                </li>
                <li class="nav-item">
                    <button class="btn btn-outline-success my-2 my-sm-0" onclick="searchNews()">Search</button>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="container">
    <ul class="list-unstyled" id="news_container">

    </ul>
    <ul class="pagination" id="pagination"></ul>
</div>

</body>
<script src="/resources/scripts/news.js"></script>
</html>