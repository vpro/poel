[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title="Admin" /]

<body>

    [@navigationUtil.navigation title='Admin' back='/' /]

    <div class="grid">

    [@layout.sectionWithLayout
    content={'layout': '100'}
    title='Beheer opties:'
    addCss='theme-primary'
    backGroundColor="darkgreen"
    ]
        <div class="grid-gutter">

            <ul>
                <li><a href="/admin/matches" class="theme-link">Wedstrijden</a></li>
                <li><a href="/admin/matchdays" class="theme-link">Speelrondes</a></li>
                <li><a href="/admin/messages" class="theme-link">Berichten</a></li>
                <li><a href="/admin/users" class="theme-link">Gebruikers</a></li>
                <li><a href="/admin/usergroups" class="theme-link">Groepen</a></li>
                <li><a href="/admin/bonuschoices" class="theme-link">Bonus mogelijkheden</a></li>
                <li><a href="/admin/bonuses" class="theme-link">Bonus vragen</a></li>
            </ul>

        </div>
    [/@layout.sectionWithLayout]
    </div>
    [@footerUtil.footer /]

</body>

</html>