[#macro head title=""]
<head>
    <title>${title}</title>


    <link rel="stylesheet" href="https://files.vpro.nl/frontend/bootstrap/dist/style.css">
    <link rel="stylesheet" href="/vendor/css/flag-icon.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="icon" href="/favicon.ico" type="image/x-icon">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    [#if maxMultipliersPerUser ? has_content]
        <meta name="maxMultipliersPerUser" content="${ maxMultipliersPerUser }">
    [/#if]

</head>
[/#macro]