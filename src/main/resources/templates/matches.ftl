<!DOCTYPE html>
<html>
<head>
    <title>Current Matches</title>
    <link rel="stylesheet" href="https://files.vpro.nl/frontend/bootstrap/dist/style.css">
</head>
<body>
<h1>Current Matches</h1>
<#list matches>
    <ul>
        <#items as match>
            <li>${match.homeTeam.name} - ${match.awayTeam.name}</li>
        </#items>
    </ul>
<#else>
    No matches at this time, sorry!
</#list>
</body>
</html>