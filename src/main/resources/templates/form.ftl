[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

[#import "macros/form.ftl" as formUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Het formulier' /]

    <body>

    [@navigationUtil.navigation title='Het formulier' back='/' /]

        <div class="grid grid-gutter">
            <h1 class="h4">Form for: ${ user.displayName }</h1>

            <ul>
                <li><a href="/user">Profiel</a></li>
            </ul>

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

                <div class="section collapsible-section">
                    <h1 class="collapsible-section-title">Finished</h1>
                    [#list finished]
                        <ul class="collapsible-section-content">
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
                </div>

                <div class="section collapsible-section open">
                    <h1 class="collapsible-section-title">Unfinished</h1>
                    [#list unfinished]
                        <ul class="collapsible-section-content">
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

                </div>

                <div class="section collapsible-section">

                    <h1 class="collapsible-section-title">Future</h1>
                    [#list future]
                        <ul class="collapsible-section-content">
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
        </div>


        <script src="/vendor/system.js"></script>
        <script src="/systemjs.config.js"></script>
        <script>

            System.import( '/js/form/CollapseController.js' ).then( function ( collapseControllerModule ) {

                new collapseControllerModule.default( document.querySelectorAll( '.collapsible-section') );

            } );

        </script>

    </body>
</html>