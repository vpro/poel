[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title="Admin" /]

<body>

<div class="home-navigation home-navigation-admin-page">
    <a href="/admin/matches" class="home-navigation-link bg-darkgreen">
        <span class='h4 c-white home-navigation-link-title'>
            Wedstrijden<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Wie tegen wie? En hoeveel is het geworden?</span>
        </span>
    </a>

    <a href="/admin/bonuses" class="home-navigation-link bg-green">
        <span class='h4 c-white home-navigation-link-title'>
            Bonusvragen<br/>
            <span class="h6 c-white home-navigation-link-subtitle">De vragen en de juiste antwoorden</span>
        </span>
    </a>

    <a href="/admin/bonuschoices" class="home-navigation-link bg-blue">
        <span class='h4 c-white home-navigation-link-title'>
            Spelers, teams en scores<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Mogelijke antwoorden op de bonusvragen</span>
        </span>
    </a>

    <a href="/admin/rounds" class="home-navigation-link bg-greybat">
        <span class='h4 c-white home-navigation-link-title'>
            Rondes<br/>
            <span class="h6 c-white home-navigation-link-subtitle">De poelfase, de kwartfinales, etc.</span>
        </span>
    </a>

    <a href="/admin/messages" class="home-navigation-link bg-green">
        <span class='h4 c-white home-navigation-link-title'>
            Meldingen<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Boodschappen voor de paginabezoeker</span>
        </span>
    </a>

    <a href="/admin/users" class="home-navigation-link bg-darkgreen">
        <span class='h4 c-white home-navigation-link-title'>
            Deelnemers<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Stop ze maar in hokjes</span>
        </span>
    </a>

    <a href="/admin/usergroups" class="home-navigation-link bg-blue">
        <span class='h4 c-white home-navigation-link-title'>
            Afdelingen<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Maak je eigen hokjes</span>
        </span>
    </a>

    <a href="/admin/bulletins" class="home-navigation-link bg-darkred">
        <span class='h4 c-white home-navigation-link-title'>
            Bulletins<br/>
            <span class="h6 c-white home-navigation-link-subtitle">De verhalen achter de strijd</span>
        </span>
    </a>

    <a href="/admin/export" class="home-navigation-link bg-greybat">
        <span class='h4 c-white home-navigation-link-title'>
            Exporteren<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Fijne CSV-bestandjes voor de mensen thuis</span>
        </span>
    </a>
 </div>

<a class="top-navigation-back" href="/"> <i class="glyph glyph-arrowleft c-white"></i></a>

    [@footerUtil.footer /]

</body>

</html>