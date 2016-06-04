[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

[@headUtil.head title="Export" /]

<body>

<div class="home-navigation home-navigation-admin">
    <a href="/admin/export/users" class="home-navigation-link bg-greybat">
        <span class='h2 c-white home-navigation-link-title'>
            Gebruikers<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Als je ze wil mailen bijvoorbeeld</span>
        </span>
    </a>

    <a href="/admin/export/ranking" class="home-navigation-link bg-greybat">
        <span class='h2 c-white home-navigation-link-title'>
            Klassement<br/>
            <span class="h6 c-white home-navigation-link-subtitle">Voor het historisch besef</span>
        </span>
    </a>
</div>

<a class="top-navigation-back" href="/admin"> <i class="glyph glyph-arrowleft c-white"></i></a>

[@footerUtil.footer /]

</body>

</html>