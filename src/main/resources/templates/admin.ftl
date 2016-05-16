[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head /]
    <body>

        <div class="grid grid-gutter">

            <h1 class="h4">Form for: ${ user.displayName }</h1>

            [@navigationUtil.navigation /]

            Alleen admins zoals ${user.displayName} (${user.username}/${user.role}) kunnen dit zien!

            [#list users]
                <h2>Alle deelnemers</h2>
                <ul>
                    [#items as u]
                        <li>${u.displayName} (${u.username})</li>
                    [/#items]
                </ul>
            [#else]
                Er zijn geen deelnemers. :o(
            [/#list]

            <p>
                Todo: <br />
                Matches beheer (aanmaken en score)<br />
                Een melding beheren die op de invulpagina's te zien is<br />
                Een export van emailadressen kunnen doen<br />
                Gebruikersgroepen kunnen aanmaken<br />
                Gebruikers in groepen kunnen toevoegen<br />
            </p>
        </div>

    </body>
</html>
