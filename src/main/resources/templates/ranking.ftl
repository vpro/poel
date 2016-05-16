[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='De Stand' /]

    <body>

    [@navigationUtil.navigation title='De Stand' back='/' /]


<section>
    <div class="grid bg-darkgreen">

        Todo:<br/>
        Standaard ranking van alle spelers met highlight van current user <br/>
        Ranking van de afdelingenen / gebruikersgroepen<br/>

        <h1 class="h5 component-gutter">spelers</h1>

    [#list users]
        <table class="ranking">
            <tbody>
            [#items as u]
                <tr class="ranking__row ranking__row-${ u ? item_parity } [#if u.getId() == user.getId() ]ranking__current-user[/#if]">
                    <td class="ranking__rank">${ u ? index + 1 }</td>
                    <td class="ranking__name">
                        <h2 class="h6 ranking__display-name">${ u.displayName }</h2>
                        <span class="ranking__full-name">${ u.username }</span>
                    </td>
                    <td class="ranking__department">
                        <span class="ranking__department-display-name">afdeling</span>
                    </td>
                    <td class="ranking__score">0</td>
                </tr>
            [/#items]
            </tbody>
        </table>
    [#else]
        Er zijn geen deelnemers. :o(
    [/#list]

            Todo:<br />
            Standaard ranking van alle spelers met highlight van current user <br />
            Ranking van de afdelingenen / gebruikersgroepen<br />
        </div>
    </body>

</html>