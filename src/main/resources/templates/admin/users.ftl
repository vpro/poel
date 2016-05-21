[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Admin'/]
    <body>

    [@navigationUtil.navigation title='Admin' back='user' /]

    <div class="grid grid-gutter">

            <h1 class="h4">Form for: ${ user.displayName }</h1>

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
                Een melding beheren die op de invulpagina's te zien is<br />
                Een export van emailadressen kunnen doen<br />
                Gebruikersgroepen kunnen aanmaken<br />
                Gebruikers in groepen kunnen toevoegen<br />
            </p>
        </div>

    [@footerUtil.footer /]
    </body>
</html>
