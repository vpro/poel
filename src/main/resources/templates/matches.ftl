[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head /]
    <body>
    [@navigationUtil.navigation /]

        <div class="grid grid-gutter">
            <h1 class="h4">Current Matches</h1>

        [#list matches]
        <form>
        <ul>
            [#items as match]
            <li>${match.homeTeam.name} - ${match.awayTeam.name}</li>
            [/#items]
        </ul>
        </form>
        [#else]
            No matches at this time, sorry!
        [/#list]
        </div>

    [@footerUtil.footer /]
    </body>
</html>