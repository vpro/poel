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

    <div class="main">

    [@messageUtil.outputMessage message=message ! /]

    <div class="grid bg-darkgreen">

    [#assign previousRanking = 0]
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
        <table class="ranking group-ranking">
        <tbody>
            [#items as userGroupRankingEntry]
            [#assign rankedUserGroup = userGroupRankingEntry.userGroup]
        <tr class="ranking__row ranking__row-${ userGroupRankingEntry ? item_parity } [#if user.userGroup ? has_content && rankedUserGroup.getId() == user.userGroup.getId() ]ranking__current-user[/#if]">
            <td class="ranking__rank">
                <span[#if previousRanking == userGroupRankingEntry.rank] class="ranking__rank-tohide hidden"[/#if]>${ userGroupRankingEntry.rank }</span>
            </td>
            <td class="ranking__name">
                <h2 class="h6 ranking__display-name">
                ${ rankedUserGroup.name }
                </h2>
            </td>
            <td class="ranking__score">${ userGroupRankingEntry.averageScore?string["0.00"] }</td>
        </tr>
        [#assign previousRanking =  userGroupRankingEntry.rank]
        [/#items]
    </tbody>
    </table>
    [#else]
        Er zijn geen afdelingen.
    [/#list]




    [#assign previousRanking = 0]

    [/@layout.sectionWithLayout]
    </div>

    <div class="grid bg-darkgreen">
        [@layout.sectionWithLayout
            content={"layout":"100"}
            title='Deelnemers'
            collapsible=true
            opened=true
            addCss='ranking-section'
            backGroundColor='greybat'
            closeColorClass='bg-greybat'
            openColorClass='bg-darkgreen'
        ]

        [#if user.userGroup ? has_content]
            <div class="grid grid-gutter c-white h6">
                <input id="ranking__groupfilter" [#if user.userGroup ? has_content]data-id="${ user.userGroup.id }"[/#if] type="checkbox" name="groupfilter" value="false"  />
                <label for="ranking__groupfilter">Filter op jouw afdeling: ${user.userGroup.name}</label>
            </div>
        [/#if]

        [#list userRanking]

        <table class="ranking user-ranking">
            <tbody>
                [#items as userRankingEntry]
                [#assign rankedUser = userRankingEntry.user]

                <tr [#if rankedUser.userGroup ? has_content]data-group="${rankedUser.userGroup.id}"[/#if]
                        class="ranking__row [#if userRankingEntry ? item_parity == 'odd']ranking__row-odd [/#if][#if rankedUser.getId() == user.getId() ]ranking__current-user[/#if]" >
                    <td class="ranking__rank">
                        <span[#if previousRanking == userRankingEntry.rank] class="ranking__rank-tohide hidden"[/#if]>${ userRankingEntry.rank }</span>
                    </td>
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
                    <td class="ranking__score">${ userRankingEntry.score }</td>
                </tr>

                [#assign previousRanking =  userRankingEntry.rank ]
                [/#items]
            </tbody>
            </table>
            [#else]
            Er zijn geen deelnemers.
            [/#list]

        [/@layout.sectionWithLayout]
    </div>

    [@footerUtil.footer /]
    </div>
    </body>

</html>