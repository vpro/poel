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

    [#if message ? has_content]
        [@messageUtil.outputMessage message=message ! /]
    [/#if]


    <div class="grid bg-darkgreen">
        [@layout.sectionWithLayout
            content={"layout":"100"}
            title='spelers'
            collapsible=true
            opened=true
            addCss='ranking-section'
            addContainerCss='bg-darkgreen'
            backGroundColor='darkgreen' ]

            [#list ranking]
            <table class="ranking">
            <tbody>
                [#items as rankingEntry]
                <tr class="ranking__row ranking__row-${ rankingEntry ? item_parity } [#if rankingEntry.user.getId() == user.getId() ]ranking__current-user[/#if]">
                    <td class="ranking__rank"><span>${ rankingEntry.rank }</span></td>
                    <td class="ranking__name">
                        <h2 class="h6 ranking__display-name">${ rankingEntry.user.realName }</h2>
                        <div class="ranking__meta">
                            <span class="ranking__full-name">${ rankingEntry.user.username }</span>
                            <span class="ranking__department">afdeling</span>
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