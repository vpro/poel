[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Profiel' /]

    <body>

    [@navigationUtil.navigation /]

        <div class="grid grid-gutter">
            <h1 class="h4">Hallo gebruiker ${user.displayName} (${user.username}/${user.role})!</h1>

            <ul>
                <li><a href="/form">the form</a></li>
            </ul>

            <p>
                Todo: <br />

                Een gebruiker kan hier z'n voetbalnaam invullen en opslaan  <br />
                Een gebruiker ziet hier eventueel in welke afdeling hij geplaatst is? <br />
                Er is een link naar de admin pagina indien de gebruiker admin is  <br />
                Er is een link naar het formulier  <br />
                Er is een link naar de ranking  <br />
            </p>

        </div>
    </body>
</html>
