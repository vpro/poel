<!DOCTYPE html>
<html lang="nl">
<head>
    <link rel="stylesheet" href="https://files.vpro.nl/frontend/bootstrap/dist/style.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>

<div class="grid grid-gutter">

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
