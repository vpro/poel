[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Admin'/]
    <body>

    [@navigationUtil.navigation title='Admin' subtitle='deelnemers' back='/admin' /]
    <div class="grid">

    [@layout.sectionWithLayout
    content={'layout': '100'}
    title='Alle deelnemers'
    addCss='theme-primary'
    backGroundColor="bg-darkgreen"
    ]
        <div class="grid-gutter theme-text">
            [#list users]
                <ul>
                    [#items as u]
                        <li>${u.realName} (${u.username})</li>
                    [/#items]
                </ul>
            [#else]
                Er zijn geen deelnemers. :o(
            [/#list]

            <p>
                Todo: <br />
                Een export van emailadressen kunnen doen<br />
                Gebruikersgroepen kunnen aanmaken<br />
                Gebruikers in groepen kunnen toevoegen<br />
            </p>
        </div>
    [/@layout.sectionWithLayout]

    [@footerUtil.footer /]
    </body>
</html>
