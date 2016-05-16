[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

[#import "macros/form.ftl" as formUtil]
[#import "macros/layout.ftl" as layout]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Poel invullen' /]

    <body>

    [@navigationUtil.navigation title='Poel invullen' subtitle=user.displayName back='/' /]

        <div class="grid">

            <form action="#" class="form" method="POST" enctype="application/x-www-form-urlencoded">

                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}">

                [#--[#list deadlinePassed as matchData]--]
                    [#----]
                    [#--[@formUtil.formEntry match=matchData.match /]--]
                    [#----]
                [#--[/#list]--]


                [@layout.sectionWithLayout
                    content={'layout': '100'}
                    collapsible=true
                    title='Finished'
                    backGroundColor="greybat"
                    addContainerCss=''
                    closeColorClass='bg-greybat'
                    openColorClass='bg-green'
                    ]

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


                [@layout.sectionWithLayout
                    content={'layout': '100'}
                    collapsible=true
                    title='unfinished'
                    backGroundColor="greybat"
                    addContainerCss=''
                    closeColorClass='bg-greybat'
                    openColorClass='bg-darkgreen'
                    opened=true
                ]

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


                [@layout.sectionWithLayout
                    content={'layout': '100'}
                    collapsible=true
                    title='future'
                    backGroundColor="greybat"
                    addContainerCss=''
                    closeColorClass='bg-greybat'
                    openColorClass='bg-green'
                ]

                    [#list future]
                        <ul class="collapsible-section-content">
                            [#items as futureEntry]
                                [#assign match = futureEntry.match]
                                [#assign prediction = futureEntry.prediction ! ]
                                <h3>${match.homeTeam} - ${match.awayTeam}</h3>
                                <li>Voorspelling: [#if prediction?has_content]${prediction.matchResultOrNull.toString()}[#else]geen[/#if]</li>

                                [#assign match = futureEntry.match]
                                <input type="hidden" name="prediction${futureEntry?index}.matchId" value="${match.id}"/>
                                <input type="text" name="prediction${futureEntry?index}.awayTeamGoals" value="11"/>
                                <input type="text" name="prediction${futureEntry?index}.homeTeamGoals" value="13"/>

                            [/#items]
                        </ul>
                    [#else]
                        Niks. :o(
                    [/#list]
                [/@layout.sectionWithLayout]

                <button type="submit" value="Gaan!">Gaan!</button>

            </form>

        </div>


    [@footerUtil.footer /]


    </body>
</html>

