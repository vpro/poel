[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

[#import "macros/form.ftl" as formUtil]
[#import "macros/layout.ftl" as layout]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Poel invullen' /]

    <body>

    [@navigationUtil.navigation title='Poel invullen' back='/' /]

        <div class="grid grid-gutter bg-white">
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

            <form action="#" class="form" method="POST">

                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">

                [#--[#list deadlinePassed as matchData]--]
                    [#----]
                    [#--[@formUtil.formEntry match=matchData.match /]--]
                    [#----]
                [#--[/#list]--]


                [@layout.sectionWithLayout content={'layout': '100'} collapsible=true title='Finished' backGroundColor="greymouse" addContainerCss="bg-greymouse"]
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
                [/@layout.sectionWithLayout]


                [@layout.sectionWithLayout content={'layout': '100'} collapsible=true title='unfinished' backGroundColor="greymouse" addContainerCss="bg-greymouse"]
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
                [/@layout.sectionWithLayout]


                [@layout.sectionWithLayout content={'layout': '100'} collapsible=true title='future' backGroundColor="greymouse" addContainerCss="bg-greymouse"]
                    [#list future]
                        <ul class="collapsible-section-content">
                            [#items as futureEntry]
                                [#assign match = futureEntry.match]
                                [#assign prediction = futureEntry.prediction ! ]
                                <h3>${match.homeTeam} - ${match.awayTeam}</h3>
                                <li>Voorspelling: [#if prediction?has_content]${prediction.matchResultOrNull.toString()}[#else]geen[/#if]</li>

                                [#assign match = futureEntry.match]
                                <input type="text" name="awayTeamGoals" value="11"/>
                                <input type="text" name="homeTeamGoals" value="13"/>

                            [/#items]
                        </ul>
                    [#else]
                        Niks. :o(
                    [/#list]
                [/@layout.sectionWithLayout]

                <button type="submit" value="Gaan!">Gaan!</button>

            </form>
        </div>


        <script src="/vendor/system.js"></script>
        <script src="/systemjs.config.js"></script>
        <script>

            System.import( '/js/form/CollapseController.js' ).then( function ( collapseControllerModule ) {

console.log('controller');
                new collapseControllerModule.default( document.querySelectorAll( '.collapsible-section') );

            } );

        </script>

    </body>
</html>

