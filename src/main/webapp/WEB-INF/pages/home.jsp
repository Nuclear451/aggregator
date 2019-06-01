<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<!-- Navbar -->
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
            </ul>
        </div>
    </nav>
</div>
<!-- Form -->
<div class="container">
    <form method="post">
        <div class="form-group col-sm-4">
            <label for="url">URL</label>
            <input type="text" class="form-control" id="url" name="url"  placeholder="https://www.example.com">
        </div>
        <div class="form-group col-sm-4">
            <label for="elementTag">Element tag</label>
            <input type="text" class="form-control" id="elementTag"
                   name="elementTag">
        </div>
        <div class="form-group col-sm-4">
            <label for="elementClass">Element class</label>
            <input type="text" class="form-control" id="elementClass"
                   name="elementClass">
        </div>
        <div class="form-group col-sm-4">
            <label for="titleTag">Title tag</label>
            <input type="text" class="form-control" id="titleTag"
                   name="titleTag">
        </div>
        <div class="form-group col-sm-4">
            <label for="titleClass">Title class</label>
            <input type="text" class="form-control" id="titleClass"
                   name="titleClass">
        </div>
        <div class="form-group col-sm-4">
            <label for="textTag">Text tag</label>
            <input type="text" class="form-control" id="textTag"
                   name="textTag">
        </div>
        <div class="form-group col-sm-4">
            <label for="textClass">Text class</label>
            <input type="text" class="form-control" id="textClass"
                   name="textClass">
        </div>
        <div class="form-group col-sm-4">
            <label for="refTag">Reference tag</label>
            <input type="text" class="form-control" id="refTag"
                   name="refTag">
        </div>
        <div class="form-group col-sm-4">
            <label for="refClass">Reference class</label>
            <input type="text" class="form-control" id="refClass"
                   name="refClass">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>


</body>
</html>