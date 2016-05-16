[#import "macros/form.ftl" as formUtil]
[#import "macros/head.ftl" as headUtil]

[@head.headUtil title='Het formulier' /]

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

    <div class="form">
        
        [#--[#list deadlinePassed as matchData]--]
            [#----]
            [#--[@formUtil.formEntry match=matchData.match /]--]
            [#----]
        [#--[/#list]--]

        <h1>Finished</h1>
        [#list finished]
            <ul>
                [#items as finishedEntry]
                    [#assign match = finishedEntry.match]
                    [#assign prediction = finishedEntry.prediction ! ]
                    <h3>${match.homeTeam} - ${match.awayTeam}</h3>
                    <li>Eindstand: ${match.matchResultOrNull.toString()}
                    <li>Voorspelling: [#if prediction?has_content]${prediction.matchResultOrNull.toString()}[#else]geen[/#if]</li>
                    <li>Score: [#if prediction?has_content]${prediction.score}[#else]0[/#if]</li>
                [/#items]
            </ul>
        [#else]
            Niks. :o(
        [/#list]

        <h1>Unfinished</h1>
        [#list unfinished]
            <ul>
                [#items as unfinishedEntry]
                    [#assign match = unfinishedEntry.match]
                    [#assign prediction = unfinishedEntry.prediction ! ]
                    <h3>${match.homeTeam} - ${match.awayTeam}</h3>
                    <li>Voorspelling: [#if prediction?has_content]${prediction.matchResultOrNull.toString()}[#else]geen[/#if]</li>
                [/#items]
            </ul>
        [#else]
            Niks. :o(
        [/#list]

        <h1>Future</h1>
        [#list future]
            <ul>
                [#items as futureEntry]
                    [#assign match = futureEntry.match]
                    [#assign prediction = futureEntry.prediction ! ]
                    <h3>${match.homeTeam} - ${match.awayTeam}</h3>
                    <li>Voorspelling: [#if prediction?has_content]${prediction.matchResultOrNull.toString()}[#else]geen[/#if]</li>
                [/#items]
            </ul>
        [#else]
            Niks. :o(
        [/#list]
    </div>
</div>


[#--<script src="/vendor/system.js"></script>--]
[#--<script src="/systemjs.config.js"></script>--]
[#--<script>--]

    [#--System.import( '/js/form/controllers/FormController.js' ).then( function ( formControllerModule ) {--]

        [#--new formControllerModule.default( document.querySelector('.form') );--]

    [#--} );--]

[#--</script>--]

</body>
</html>