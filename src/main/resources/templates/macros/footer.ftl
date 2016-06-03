[#macro footer]
    [#local devMode = true]

    [#if devMode]
        <script src="/vendor/system.js"></script>
        <script src="/systemjs.config.js"></script>
        <script>

            if ( typeof System !== 'undefined' ) {
                System.import('/js/impl.js');
            }

        </script>
    [#else]
    <script src="/js/impl.sfx.js"></script>
    [/#if]
[/#macro]