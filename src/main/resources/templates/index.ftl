[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='hello' /]

    <body>

        <div class="grid grid-gutter">
            <h1 class="h4">Hello World</h1>

            [@navigationUtil.navigation /]

            <p>
                Todo:  <br />
                Logo  <br />
                Korte uitleg  <br />
                Link naar de gebruikerspagina, zodat je een account kan maken en inloggen  <br />
                Link naar het invulformulier  <br />
                Link naar de ranking  <br />
                Link naar de admin indien je ingelogd bent?<br />
            </p>
        </div>

    </body>
</html>