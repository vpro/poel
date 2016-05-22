<!DOCTYPE html>
<html lang="nl">

[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

[#import "macros/form.ftl" as formUtil]
[#import "macros/layout.ftl" as layout]

[#include "macros/countries.ftl"]

    [@headUtil.head title='Poel invullen: ${ user.displayName }' /]

    <body>

        [#-- TODO: Make this less intrusive and prettier --]
        [#if flash ? has_content]
            <script type="application/javascript">
                window.alert("${flash}");
            </script>
        [/#if]

        [@navigationUtil.navigation title='Poel invullen' subtitle=user.displayName back='/' /]

            <div class="grid prediction-form-container">

                [#if finished?has_content]

                    [@layout.sectionWithLayout
                        content={'layout': '100'}
                        collapsible=true
                        title='wedstrijden: eerder gespeeld'
                        backGroundColor="greybat"
                        addContainerCss=''
                        addCss='prediction-section'
                        closeColorClass='bg-greybat'
                        openColorClass='bg-darkgreen'
                    ]

                        [#list finished]

                            <div class="collapsible-section-content">
                                [#items as matchEntry]

                                    [#assign match = matchEntry.match]
                                    [#assign result = match.matchResult]
                                    [#assign prediction = matchEntry.prediction ! ]
                                    [#assign hasPrediction = prediction ? has_content]
                                    [#assign score = matchEntry.score ! ]

                                <table class="predictions">
                                    <tbody>
                                    <tr class="prediction__row prediction__row-${ matchEntry ? item_parity }">
                                        <td class="prediction__game">
                                            <span class=" ">
                                                <span class="flag-icon flag-icon-${match.homeTeam}"></span>
                                                ${countryCodes[match.homeTeam] ! } - ${countryCodes[match.awayTeam] !}
                                                <span class="flag-icon flag-icon-${match.awayTeam}"></span>
                                            </span>
                                        </td>
                                        <td class="prediction__predicted">
                                            <input type="number" [#if hasPrediction]value="${prediction.homeTeamGoals}"[/#if] disabled />
                                            -
                                            <input type="number" [#if hasPrediction]value="${prediction.awayTeamGoals}"[/#if] disabled />
                                        </td>
                                        <td class="prediction__score">
                                            <span class='prediction__points prediction__points-${score} '>${score}</span>
                                        </td>

                                    </tr>
                                    <tr class="prediction__row prediction__row-result prediction__row-${ matchEntry ? item_parity }">
                                        <td class="prediction__result-title" >Uitslag:</td>
                                        <td class="prediction__result">
                                            <input type="number" value="${result.homeTeamGoals}" disabled />
                                            -
                                            <input type="number" value="${result.awayTeamGoals}" disabled />
                                        </td>
                                        <td class="prediction__score">
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>

                                [/#items]
                            </div>
                        [#else]
                            <div>Geen wedstrijden beschikbaar</div>
                        [/#list]

                    [/@layout.sectionWithLayout]

                [/#if]

                [#if unfinished ? has_content]

                    [@layout.sectionWithLayout
                        content={'layout': '100'}
                        collapsible=true
                        title='wedstrijden: nu'
                        backGroundColor="greybat"
                        addContainerCss=''
                        addCss='prediction-section'
                        closeColorClass='bg-greybat'
                        openColorClass='bg-darkgreen'
                        opened=false
                    ]

                        [#list unfinished]
                            <div class="collapsible-section-content">
                                [#items as matchEntry]
                                    [#assign match = matchEntry.match]
                                    [#assign prediction = matchEntry.prediction ! ]
                                    [#assign hasPrediction = prediction ? has_content]
                                    [#assign score = matchEntry.score ! ]

                                <table class="predictions match-prediction">
                                    <tbody>
                                    <tr class="prediction__row prediction__row-${ matchEntry ? item_parity }">
                                        <td class="prediction__game">
                                            <span class="flag-icon flag-icon-${match.homeTeam}"></span>
                                            ${countryCodes[match.homeTeam] ! } - ${countryCodes[match.awayTeam] !}
                                            <span class="flag-icon flag-icon-${match.awayTeam}"></span>
                                        </td>

                                        <td class="prediction__predicted">
                                            <input type="number" name="predictions[${matchEntry?index}].homeTeamGoals" [#if hasPrediction]value="${prediction.homeTeamGoals}"[/#if] disabled />
                                            -
                                            <input type="number" name="predictions[${matchEntry?index}].awayTeamGoals" [#if hasPrediction]value="${prediction.awayTeamGoals}"[/#if] disabled />
                                        </td>

                                        <!--<td class="prediction__score">-->
                                            <!--<span class='prediction__points prediction__points-${score} '>${score}</span>-->
                                        <!--</td>-->


                                    </tr>
<!--
                                    <tr class="prediction__row prediction__row-result prediction__row-${ matchEntry ? item_parity }">
                                        <td class="prediction__result-title" >Uitslag:</td>
                                        <td class="prediction__result" >

                                            <input type="number" value="${result.homeTeamGoals}" disabled />
                                            -
                                            <input type="number" value="${result.awayTeamGoals}" disabled />
                                        </td>
                                        <td class="prediction__score">
                                        </td>
                                    </tr>
-->
                                    </tbody>
                                    </table>
                                [/#items]
                            </div>
                        [#else]
                            <div>Geen wedstrijden beschikbaar</div>
                        [/#list]

                    [/@layout.sectionWithLayout]

                [/#if]

                [#if future ? has_content]

                    [@layout.sectionWithLayout
                        content={'layout': '100'}
                        collapsible=true
                        title='wedstrijden: later'
                        backGroundColor="greybat"
                        addContainerCss=''
                        addCss='prediction-section'
                        closeColorClass='bg-greybat'
                        openColorClass='bg-darkgreen'
                        opened=true
                    ]

                        [#list future]

                            <form action="#" class="form" method="post">

                                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                                <div class="collapsible-section-content">
                                    [#items as matchEntry]
                                        [#assign match = matchEntry.match]
                                        [#assign prediction = matchEntry.prediction ! ]
                                        [#assign hasPrediction = prediction ? has_content]


                                    <table class="predictions match-prediction">
                                        <tbody>
                                        <tr class="prediction__row prediction__row-${ matchEntry ? item_parity }">
                                            <td class="prediction__game">
                                                <span class="flag-icon flag-icon-${match.homeTeam}"></span>
                                                ${countryCodes[match.homeTeam] ! } - ${countryCodes[match.awayTeam] !}
                                                <span class="flag-icon flag-icon-${match.awayTeam}"></span>
                                                <input type="hidden" name="predictions[${matchEntry?index}].matchId" value="${match.id}"/>
                                            </td>

                                            <td class="prediction__predicted">
                                                <input class="home-prediction" type="number" name="predictions[${matchEntry?index}].homeTeamGoals" [#if hasPrediction]value="${prediction.homeTeamGoals}" [/#if] />
                                                <input class="away-prediction" type="number" name="predictions[${matchEntry?index}].awayTeamGoals" [#if hasPrediction]value="${prediction.awayTeamGoals}" [/#if] />

                                            </td>

                                        </tr>

                                        </tbody>
                                    </table>


                                    [/#items]
                                </div>

                                <div class="prediction-form-button-container">
                                    <button class="h5 button submit-button" type="submit" disabled>Opslaan</button>
                                    <button class="h5 button reset-button" type="reset">Annuleren</button>
                                </div>

                            </form>

                        [#else]
                            <div>Geen wedstrijden beschikbaar</div>
                        [/#list]

                    [/@layout.sectionWithLayout]

                [/#if]

            </div>

        [@footerUtil.footer /]

        <script>
            System.import( '/js/form/controllers/FormController.js' ).then( function ( formControllerModule ) {

                new formControllerModule.default( document.querySelectorAll( 'form' ) );

            } );
        </script>

    </body>
</html>
