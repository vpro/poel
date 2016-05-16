[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head /]
    <body>

        <div class="grid grid-gutter">
            <h1 class="h4">Current Matches</h1>

            [@navigationUtil.navigation /]

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

    </body>
</html>