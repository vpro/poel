<!DOCTYPE html>
<html lang="nl">

[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/message.ftl" as messageUtil]

[#import "macros/form.ftl" as formUtil]
[#import "macros/layout.ftl" as layout]

[#include "macros/countries.ftl"]

    [@headUtil.head title='Poel invullen: ${ user.displayName }' /]

    <body>

        [#if flash ? has_content]
        <div class="alert-overlay">
            <div class="alert-overlay__content">
                ${flash} <br/> <br/>
                <button class="h5 button alert-overlay__close-button">Ok!</button>
            </div>
        </div>
        [/#if]


        [@navigationUtil.navigation title='Poel invullen' subtitle=user.displayName back='/' /]

            [#if message ? has_content]
                [@messageUtil.outputMessage message=message ! /]
            [/#if]

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
                                [#items as prediction]

                                    [#assign match = prediction.match]
                                    [#assign result = match.matchResult]
                                    [#assign predictedResult = prediction.matchResult !]
                                    [#assign hasPrediction = predictedResult ? has_content]
                                    [#assign score = prediction.score]

                                <table class="predictions">
                                    <tbody>
                                    <tr class="prediction__row prediction__row-${ prediction ? item_parity }">
                                        <td class="prediction__game">
                                            <span class="prediction__game-home">
                                                <span class="flag-icon flag-icon-${ countryCodes[match.homeTeam] !}"></span>
                                                ${ match.homeTeam }
                                            </span>
                                            <span class="prediction__game-divider">
                                                -
                                            </span>
                                            <span class="prediction__game-away">
                                                <span class="flag-icon flag-icon-${ countryCodes[match.awayTeam] !}"></span>
                                                ${ match.awayTeam }
                                            </span>
                                        </td>
                                        <td class="prediction__predicted">
                                            <input type="number" [#if hasPrediction]value="${predictedResult.homeTeamGoals}"[/#if] disabled />
                                            <input type="number" [#if hasPrediction]value="${predictedResult.awayTeamGoals}"[/#if] disabled />
                                        </td>
                                        <td class="prediction__score">
                                            <span class='prediction__points prediction__points-${score} '>${score}</span>
                                        </td>

                                    </tr>
                                    <tr class="prediction__row prediction__row-result prediction__row-${ prediction ? item_parity }">
                                        <td class="prediction__result-title" >Uitslag:</td>
                                        <td class="prediction__result">
                                            <input type="number" value="${result.homeTeamGoals}" disabled />
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
                                [#items as prediction]
                                    [#assign match = prediction.match]
                                    [#assign predictedResult = prediction.matchResult !]
                                    [#assign hasPrediction = predictedResult ? has_content]

                                <table class="predictions match-prediction">
                                    <tbody>
                                    <tr class="prediction__row prediction__row-${ prediction ? item_parity }">
                                        <td class="prediction__game">
                                            <span class="prediction__game-home">
                                                <span class="flag-icon flag-icon-${ countryCodes[match.homeTeam] !}"></span>
                                                ${ match.homeTeam }
                                            </span>
                                            <span class="prediction__game-divider">
                                                -
                                            </span>
                                            <span class="prediction__game-away">
                                                <span class="flag-icon flag-icon-${ countryCodes[match.awayTeam] !}"></span>
                                                ${ match.awayTeam }
                                            </span>
                                        </td>

                                        <td class="prediction__predicted">
                                            <input type="number" name="predictions[${prediction?index}].homeTeamGoals" [#if hasPrediction]value="${predictedResult.homeTeamGoals}"[/#if] disabled />
                                            <input type="number" name="predictions[${prediction?index}].awayTeamGoals" [#if hasPrediction]value="${predictedResult.awayTeamGoals}"[/#if] disabled />
                                        </td>
                                    </tr>

                                    <tr class="prediction__row prediction__row-result prediction__row-${ prediction ? item_parity }">
                                        <td class="prediction__result-title" >
                                            Wedstrijd gestart op: ${match.start?string["dd-MM, HH:mm"]}
                                        </td>
                                        <td class="prediction__result" >
                                            <input type="number" value="${result.homeTeamGoals}" disabled />
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
                                    [#items as prediction]
                                        [#assign predictionId = prediction.id !]
                                        [#assign match = prediction.match]
                                        [#assign predictedResult = prediction.matchResult !]
                                        [#assign hasPrediction = predictedResult ? has_content]


                                    <table class="predictions match-prediction">
                                        <tbody>
                                        <tr class="prediction__row prediction__row-${ prediction ? item_parity }">
                                            <td class="prediction__game">
                                                <span class="prediction__game-home">
                                                    <span class="flag-icon flag-icon-${ countryCodes[match.homeTeam] !}"></span>
                                                    ${ match.homeTeam }
                                                </span>
                                                <span class="prediction__game-divider">
                                                    -
                                                </span>
                                                <span class="prediction__game-away">
                                                    <span class="flag-icon flag-icon-${ countryCodes[match.awayTeam] !}"></span>
                                                    ${ match.awayTeam }
                                                </span>
                                                <input type="hidden" name="predictions[${prediction?index}].matchId" value="${match.id}"/>
                                                [#if predictionId?has_content]
                                                    <input type="hidden" name="predictions[${prediction?index}].predictionId" value="${predictionId}"/>
                                                [/#if]
                                            </td>

                                            <td class="prediction__predicted">
                                                <input class="prediction home-prediction" type="number" min="0" name="predictions[${prediction?index}].homeTeamGoals" [#if hasPrediction]value="${predictedResult.homeTeamGoals}" [/#if] />
                                                <input class="prediction away-prediction" type="number" min="0" name="predictions[${prediction?index}].awayTeamGoals" [#if hasPrediction]value="${predictedResult.awayTeamGoals}" [/#if] />
                                            </td>
                                        </tr>

                                        <tr class="prediction__row prediction__row-result prediction__row-${ prediction ? item_parity }">
                                            <td class="prediction__result-title" >
                                                Wedstrijd op: ${match.start?string["dd-MM, HH:mm"]}

                                                [#if match.start?long - .now?long < 10800000 && !hasPrediction ]
                                                    <br><span class="prediction-deadline c-white bg-red"><i class="glyph glyph-alert c-white"></i> Let op: wedstrijd begint bijna! </span>
                                                [/#if]

                                            </td>
                                            <td class="prediction__result" >
                                            </td>
                                            <td class="prediction__score">
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

                new formControllerModule.default( document.querySelectorAll( 'form' ), '.alert-overlay' );

            } );
        </script>

    </body>
</html>
