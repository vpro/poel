<!DOCTYPE html>
<html>
<head>
    <title>Hello Form</title>
    <link rel="stylesheet" href="https://files.vpro.nl/frontend/bootstrap/dist/style.css">
    <link rel="stylesheet" href="/css/form.css">
</head>
<body>

<div class="grid grid-gutter">
    <h1>Form for: ${ user.displayName }</h1>

    <p>
        Todo:  <br />
        Matches tonen <br />
        Wel/niet ingevulde prediction tonen <br />
        Disabled status wanneer uiterste invuldatum bereikt is <br />
        Outcome tonen indien aanwezig <br />
        Score tonen wanneer de outcome bekend is <br />
    </p>

    <div class="form"></div>
</div>


<#--<script src="/vendor/system.js"></script>-->
<#--<script src="/systemjs.config.js"></script>-->
<#--<script>-->

    <#--System.import( '/js/form/controllers/FormController.js' ).then( function ( formControllerModule ) {-->

        <#--new formControllerModule.default( document.querySelector('.form') );-->

    <#--} );-->

<#--</script>-->

</body>
</html>