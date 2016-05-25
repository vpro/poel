[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/layout.ftl" as layout]

[#import "macros/message.ftl" as messageUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Poelstand' /]

    <body>

    [@navigationUtil.navigation title='Poelstand' back='/' /]

    [@messageUtil.outputMessage message=message ! /]

    <div class="grid bg-darkgreen">
    [@layout.sectionWithLayout
    content={"layout":"100"}
    title='afdelingen'
    collapsible=true
    opened=false
    addCss='ranking-section'
    backGroundColor='greybat'
    closeColorClass='bg-greybat'
    openColorClass='bg-darkgreen'
    ]

        [#list userGroupRanking]
        <table class="ranking">
        <tbody>
            [#items as rankingEntry]
            [#assign rankedUserGroup = rankingEntry.subject]
        <tr class="ranking__row ranking__row-${ rankingEntry ? item_parity } [#if user.userGroup ? has_content && rankedUserGroup.getId() == user.userGroup.getId() ]ranking__current-user[/#if]">
            <td class="ranking__rank"><span>${ rankingEntry.rank }</span></td>
            <td class="ranking__name">
                <h2 class="h6 ranking__display-name">
                ${ rankedUserGroup.name }
                </h2>
            </td>
            <td class="ranking__score">${ rankingEntry.score }</td>
        </tr>
        [/#items]
    </tbody>
    </table>
    [#else]
        Er zijn geen afdelingen. :o(
    [/#list]

    [/@layout.sectionWithLayout]
    </div>

    <div class="grid bg-darkgreen">
        [@layout.sectionWithLayout
            content={"layout":"100"}
            title='spelers'
            collapsible=true
            opened=true
            addCss='ranking-section'
            backGroundColor='greybat'
            closeColorClass='bg-greybat'
            openColorClass='bg-darkgreen'
        ]

            [#list userRanking]
            <table class="ranking">
            <tbody>
                [#items as rankingEntry]
                [#assign rankedUser = rankingEntry.subject]
                <tr class="ranking__row ranking__row-${ rankingEntry ? item_parity } [#if rankedUser.getId() == user.getId() ]ranking__current-user[/#if]">
                    <td class="ranking__rank"><span>${ rankingEntry.rank }</span></td>
                    <td class="ranking__name">
                        <h2 class="h6 ranking__display-name">
                            [#if rankedUser.gameName ? has_content]
                            ${ rankedUser.gameName }
                            [#elseif rankedUser.realName ? has_content]
                            ${ rankedUser.realName }
                            [#elseif rankedUser.getId() == user.getId() ]
                                <a href="/user"><i> vul je voetbalnaam in op je profielpagina</i></a>
                            [/#if]
                        </h2>
                        <div class="ranking__meta">
                            <span class="ranking__full-name">
                                [#if rankedUser.realName ? has_content ]
                                ${ rankedUser.realName }
                                [#elseif rankedUser.getId() == user.getId() ]
                                <a href="/user"><i> vul je naam in op je profielpagina</i></a>
                                [/#if]
                            </span>
                            <span class="ranking__department">
                                [#if rankedUser.userGroup ? has_content]
                                    ${ rankedUser.userGroup.name }
                                [/#if]
                            </span>
                        </div>
                    </td>
                    <td class="ranking__score">${ rankingEntry.score }</td>
                </tr>
                [/#items]
            </tbody>
            </table>
            [#else]
            Er zijn geen deelnemers. :o(
            [/#list]

        [/@layout.sectionWithLayout]
    </div>

    [@footerUtil.footer /]
    </body>

</html>