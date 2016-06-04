[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title="Admin" /]

<body>

<div class="home-navigation home-navigation-admin">
    <a href="/admin/matches" class="home-navigation-link bg-greybat">
        <span class='h2 c-white home-navigation-link-title'>
            wedstrijden<br/>
            <span class="h6 c-white home-navigation-link-subtitle">aanmaken en uitslagen invoeren</span>
        </span>
    </a>

    <a href="/admin/bonuses" class="home-navigation-link bg-greybat">
        <span class='h2 c-white home-navigation-link-title'>
            bonusvragen<br/>
            <span class="h6 c-white home-navigation-link-subtitle">aanmaken en antwoorden invoeren</span>
        </span>
    </a>

    <a href="/admin/bonuschoices" class="home-navigation-link bg-greybat">
        <span class='h2 c-white home-navigation-link-title'>
            bonusvraag antwoorden<br/>
            <span class="h6 c-white home-navigation-link-subtitle">aanmaken</span>
        </span>
    </a>

    <a href="/admin/rounds" class="home-navigation-link bg-greybat">
        <span class='h2 c-white home-navigation-link-title'>
            speelrondes<br/>
            <span class="h6 c-white home-navigation-link-subtitle">aanmaken</span>
        </span>
    </a>

    <a href="/admin/messages" class="home-navigation-link bg-green">
        <span class='h2 c-white home-navigation-link-title'>
            berichten<br/>
           <span class="h6 c-white home-navigation-link-subtitle">bovenaan alle pagina's</span>
        </span>
    </a>

    <a href="/admin/users" class="home-navigation-link bg-darkgreen">
        <span class='h2 c-white home-navigation-link-title'>
            Gebruikers<br/>
            <span class="h6 c-white home-navigation-link-subtitle">wie is wie</span>
        </span>
    </a>

    <a href="/admin/usergroups" class="home-navigation-link bg-blue">
        <span class='h2 c-white home-navigation-link-title'>
            groepen<br/>
            <span class="h6 c-white home-navigation-link-subtitle">aanmaken en hernoemen</span>
        </span>
    </a>

    <a href="/admin/bulletins" class="home-navigation-link bg-darkred">
        <span class='h2 c-white home-navigation-link-title'>
            bulletins<br/>
            <span class="h6 c-white home-navigation-link-subtitle">mailings invoeren</span>
        </span>
    </a>

    <a href="/admin/export" class="home-navigation-link bg-greybat">
        <span class='h2 c-white home-navigation-link-title'>
            Exporteren<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Fijne CSV-bestandjes</span>
        </span>
    </a>
 </div>

<a class="top-navigation-back" href="/"> <i class="glyph glyph-arrowleft c-white"></i></a>

    [@footerUtil.footer /]

</body>

</html>