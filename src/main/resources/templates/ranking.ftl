[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head /]
    <body>
        <div class="grid">
            <h1 class="h4">Ranking with the overall position for ${ user.displayName }</h1>

            [@navigationUtil.navigation /]

            Todo:<br />
            Standaard ranking van alle spelers met highlight van current user <br />
            Ranking van de afdelingenen / gebruikersgroepen<br />
        </div>
    </body>

</html>