<!DOCTYPE html>
<html lang="nl">

[#import "macros/head.ftl" as headUtil]
    [#import "macros/footer.ftl" as footerUtil]

    [#import "macros/navigation.ftl" as navigationUtil]

    [#import "macros/form.ftl" as formUtil]
    [#import "macros/layout.ftl" as layout]

    [@headUtil.head title='Poel invullen: ${ user.displayName }' /]

    <body>

        [@navigationUtil.navigation title='Poel invullen' subtitle=user.displayName back='/' /]

            <div class="grid prediction-form-container">

                [#if finished?has_content]

                    [@layout.sectionWithLayout
                        content={'layout': '100'}
                        collapsible=true
                        title='wedstrijden: eerder gespeeld'
                        backGroundColor="greybat"
                        addContainerCss=''
                        closeColorClass='bg-greybat'
                        openColorClass='bg-green'
                    ]

                        [#list finished]

                            <div class="col-gutter collapsible-section-content">
                                [#items as matchEntry]

                                    [#assign match = matchEntry.match]
                                    [#assign result = match.matchResult]
                                    [#assign prediction = matchEntry.prediction ! ]
                                    [#assign hasPrediction = prediction ? has_content]
                                    [#assign score = matchEntry.score ! ]

                                    <div>
                                        <span class="section-with-layout-title h5">${match.homeTeam} - ${match.awayTeam}</span>

                                        <input type="number" [#if hasPrediction]value="${prediction.homeTeamGoals}"[/#if] disabled />
                                        -
                                        <input type="number" [#if hasPrediction]value="${prediction.awayTeamGoals}"[/#if] disabled />

                                        <span>Score: ${score}</span>

                                    </div>

                                    <div>
                                        Uitslag:
                                        <input type="number" value="${result.homeTeamGoals}" disabled />
                                        -
                                        <input type="number" value="${result.awayTeamGoals}" disabled />
                                    </div>

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
                        closeColorClass='bg-greybat'
                        openColorClass='bg-darkgreen'
                        opened=false
                    ]

                        [#list unfinished]
                            <div class="col-gutter collapsible-section-content">
                                [#items as matchEntry]
                                    [#assign match = matchEntry.match]
                                    [#assign prediction = matchEntry.prediction ! ]
                                    [#assign hasPrediction = prediction ? has_content]
                                    [#assign score = matchEntry.score ! ]
                                    <div>
                                        <span class="section-with-layout-title h5">${match.homeTeam} - ${match.awayTeam}</span>

                                        <input type="number" name="predictions[${matchEntry?index}].homeTeamGoals" [#if hasPrediction]value="${prediction.homeTeamGoals}"[/#if] disabled />
                                        -
                                        <input type="number" name="predictions[${matchEntry?index}].awayTeamGoals" [#if hasPrediction]value="${prediction.awayTeamGoals}"[/#if] disabled />
                                        <span>Score: ${score}</span>
                                    </div>

                                    <div>
                                        Uitslag:
                                        <input type="number" value="${result.homeTeamGoals}" disabled />
                                        -
                                        <input type="number" value="${result.awayTeamGoals}" disabled />
                                    </div>

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
                        closeColorClass='bg-greybat'
                        openColorClass='bg-green'
                        opened=true
                    ]

                        [#list future]

                            <form action="#" class="form" method="post">

                                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                                <div class="col-gutter collapsible-section-content">
                                    [#items as matchEntry]
                                        [#assign match = matchEntry.match]
                                        [#assign prediction = matchEntry.prediction ! ]
                                        [#assign hasPrediction = prediction ? has_content]

                                        <div>
                                            <span class="section-with-layout-title h5">${match.homeTeam} - ${match.awayTeam}</span>
                                            <input type="hidden" name="predictions[${matchEntry?index}].matchId" value="${match.id}"/>

                                            <input type="number" name="predictions[${matchEntry?index}].homeTeamGoals" [#if hasPrediction]value="${prediction.homeTeamGoals}" [/#if] />
                                            <input type="number" name="predictions[${matchEntry?index}].awayTeamGoals" [#if hasPrediction]value="${prediction.awayTeamGoals}" [/#if] />

                                        </div>

                                    [/#items]
                                </div>

                                <div class="prediction-form-button-container">
                                    <button class="h5 button submit-button" type="submit">Opslaan</button>
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

    </body>
</html>
