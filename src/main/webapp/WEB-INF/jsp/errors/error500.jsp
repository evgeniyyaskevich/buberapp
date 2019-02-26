<%@ page errorPage="error500.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <style type="text/css">
        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
        }

        body {
            font-family: "Whitney SSm A", Arial, serif;
            background-color: #2D72D9;
            color: #fff;
        }

        .error-container {
            text-align: center;
            height: 100%;
        }

        .error-container h1 {
            margin: 0;
            font-size: 100px;
            font-weight: 300;
            position: relative;
            top: 50%;
            -webkit-transform: translateY(-50%);
            -ms-transform: translateY(-50%);
            transform: translateY(-50%);
        }

        .not-found {
            color: rgba(255, 255, 255, 0.6);
            font-weight: 400;
            letter-spacing: -0.04em;
            margin: 0;
            position: absolute;
            width:100%;
            bottom: 170px;
        }

    </style>
</head>

<body>
<div class="error-container">
    <h1>Ooops...</h1>
    <h2 class="not-found">We are already working on this bug! <br> Please accept our apologies.</h2>
</div>
</body>
</html>
