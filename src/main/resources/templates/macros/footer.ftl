[#macro footer ]
<script src="/vendor/system.js"></script>
<script src="/systemjs.config.js"></script>
<script>

    System.import( '/js/util/CollapseController.js' ).then( function ( collapseControllerModule ) {

        new collapseControllerModule.default( document.querySelectorAll( '.collapsible-section') );

    } );

</script>

[/#macro]