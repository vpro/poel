<!DOCTYPE html>
<html>
<head>
    <title>Hello Form</title>
    <link rel="stylesheet" href="http://files.vpro.nl/frontend/bootstrap/dist/style.css">
    <link rel="stylesheet" href="/css/form.css">
</head>
<body>

<div class="grid grid-gutter">
    <h1>Form for: ${ user.displayName }</h1>
    <div class="form"></div>
</div>


<script src="/vendor/system.js"></script>
<script src="/systemjs.config.js"></script>
<script>

    System.import( '/js/form/controllers/FormController.js' ).then( function ( formControllerModule ) {

        new formControllerModule.default( document.querySelector('.form') );

    } );

</script>

</body>
</html>