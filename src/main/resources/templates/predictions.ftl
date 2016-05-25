<!DOCTYPE html>
<html lang="nl">

[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/message.ftl" as messageUtil]

[#import "macros/form.ftl" as formUtil]
[#import "macros/layout.ftl" as layout]

[#include "macros/countries.ftl"]

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


        [@navigationUtil.navigation title='Poel invullen' subtitle=user.realName back='/' /]

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

                            <div class="collapsible-section-content">
                                [#items as scoredPrediction]

                                    [@formUtil.showMatch status='finished' scoredPrediction=scoredPrediction parity=scoredPrediction?item_parity index=scoredPrediction?index /]

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
                                [#items as scoredPrediction]

                                       [@formUtil.showMatch status='unfinished' scoredPrediction=scoredPrediction parity=scoredPrediction?item_parity index=scoredPrediction?index /]

                                [/#items]
                            </div>
                        [#else]
                            <div>Geen wedstrijden beschikbaar</div>
                        [/#list]

                    [/@layout.sectionWithLayout]

                [/#if]

                [#if future ? has_content]

                    <form action="#" method="post">

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


                                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                                <div class="collapsible-section-content">
                                    [#items as scoredPrediction]

                                        [@formUtil.showMatch scoredPrediction=scoredPrediction status='future' parity=scoredPrediction?item_parity index=scoredPrediction?index /]

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

        <script>
            System.import( '/js/form/controllers/FormController.js' ).then( function ( formControllerModule ) {

                new formControllerModule.default( document.querySelectorAll( 'form' ), '.alert-overlay' );

            } );
        </script>

    </body>
</html>
