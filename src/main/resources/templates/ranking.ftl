[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/layout.ftl" as layout]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Poelstand' /]

    <body>

    [@navigationUtil.navigation title='Poelstand' back='/' /]


    [@layout.sectionWithLayout
        content={"layout":"100"}
        title='spelers'
        collapsible=true
        addCss='ranking-section'
        addContainerCss='bg-darkgreen'
        backGroundColor='darkgreen' ]

    Todo:<br/>
    Standaard ranking van alle spelers met highlight van current user <br/>
    Ranking van de afdelingenen / gebruikersgroepen<br/>

        [#list users]
        <table class="ranking">
        <tbody>
            [#items as u]
            <tr class="ranking__row ranking__row-${ u ? item_parity } [#if u.getId() == user.getId() ]ranking__current-user[/#if]">
                <td class="ranking__rank"><span>${ u ? index + 1 }</span></td>
                <td class="ranking__name">
                    <h2 class="h6 ranking__display-name">${ u.displayName }</h2>
                    <div class="ranking__meta">
                        <span class="ranking__full-name">${ u.username }</span>
                        <span class="ranking__department">afdeling</span>
                    </div>
                </td>
                <td class="ranking__score">0</td>
            </tr>
            [/#items]
        </tbody>
        </table>
        [#else]
        Er zijn geen deelnemers. :o(
        [/#list]

    [/@layout.sectionWithLayout]


    </body>

</html>