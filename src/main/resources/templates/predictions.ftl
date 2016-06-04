<!DOCTYPE html>
<html lang="nl">

[#import 'macros/head.ftl' as headUtil]
[#import 'macros/footer.ftl' as footerUtil]
[#import 'macros/form.ftl' as formUtil]
[#import 'macros/layout.ftl' as layout]
[#import "macros/matchdays.ftl" as matchDaysUtil]
[#import 'macros/message.ftl' as messageUtil]
[#import 'macros/navigation.ftl' as navigationUtil]

[#include 'macros/countries.ftl']

    [@headUtil.head title='Poel invullen: ${ user.realName }' /]

    <body>

        [#if flash ? has_content]
        <div class="alert-overlay">
            <div class="alert-overlay__content">
                ${flash} <br/> <br/>
                <button class="h5 button alert-overlay__close-button">Ok!</button>
            </div>
        </div>
        [/#if]


        [@navigationUtil.navigation title='Poel invullen' subtitle=user.gameName!user.realName!user.username back='/' /]

            [@messageUtil.outputMessage message=message ! /]

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
                            [@matchDaysUtil.reset /]

                            <div class="collapsible-section-content">
                                [#items as scoredPrediction]
                                    [#assign match = scoredPrediction.prediction.match ]
                                    [@matchDaysUtil.showOptionalMatchDayLabel match.start /]
                        
                                    [@formUtil.showMatch status=formUtil.STATUS_FINISHED scoredPrediction=scoredPrediction parity=scoredPrediction?item_parity index=scoredPrediction?index /]

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
                            [@matchDaysUtil.reset /]
                            <div class="collapsible-section-content">
                                [#items as scoredPrediction]

                                    [#assign match = scoredPrediction.prediction.match ]
                                    [@matchDaysUtil.showOptionalMatchDayLabel match.start /]

                                    [@formUtil.showMatch status=formUtil.STATUS_UNFINISHED scoredPrediction=scoredPrediction parity=scoredPrediction?item_parity index=scoredPrediction?index /]

                                [/#items]
                            </div>
                        [#else]
                            <div>Geen wedstrijden beschikbaar</div>
                        [/#list]

                    [/@layout.sectionWithLayout]

                [/#if]

                [#if future ? has_content]

                    <form class="prediction-form" action="#" method="post">

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

                            [@matchDaysUtil.reset /]

                            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                            <div class="collapsible-section-content">
                                [#items as scoredPrediction]

                                    [#assign match = scoredPrediction.prediction.match ]
                                    [@matchDaysUtil.showOptionalMatchDayLabel match.start /]

                                    [@formUtil.showMatch scoredPrediction=scoredPrediction status=formUtil.STATUS_FUTURE parity=scoredPrediction?item_parity index=scoredPrediction?index /]

                                [/#items]
                            </div>

                        [#else]
                            <div>Geen wedstrijden beschikbaar</div>
                        [/#list]

                    [/@layout.sectionWithLayout]

                        <div class="prediction-form-button-container">
                            <button class="h5 button submit-button" type="submit" disabled>Opslaan</button>
                            <button class="h5 button reset-button" type="reset">Annuleren</button>
                        </div>

                    </form>
                [/#if]

            </div>

        [@footerUtil.footer /]

    </body>
</html>
