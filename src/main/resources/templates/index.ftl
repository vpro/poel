[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title=title!'Poel' /]

    <body class="home">

        <div class="home-navigation [#if user?has_content && user.role == 'ADMIN']home-navigation-admin[/#if]">
            <a href="/about" class="home-navigation-link bg-greybat">
                <span class='h2 c-white home-navigation-link-title'>
                    ${ title ! 'Poel' }<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">Hoe werkt het?</span>
                </span>
            </a>
            <a href="/predictions" class="home-navigation-link bg-green">
                <span class='h2 c-white home-navigation-link-title' >
                    Poel invullen<br/>
                    [#if ranking ? has_content]
                        [#assign score = ranking.score ! 0]
                        <span class="h6 c-white home-navigation-link-subtitle">Je hebt ${ score } punt[#if score != 1]en[/#if]</span>
                    [/#if]
                </span>
            </a>
            <a href="/ranking" class="home-navigation-link bg-darkgreen">
                <span class='h2 c-white home-navigation-link-title' >
                    Poelstand<br/>
                    [#if ranking ? has_content]
                        <span class="h6 c-white home-navigation-link-subtitle">Je staat op positie ${ ranking.rank }</span>
                    [/#if]
                </span>
            </a>
            <a href="/user" class="home-navigation-link bg-blue">
                <span class='h2 c-white home-navigation-link-title' >
                    Mijn poelprofiel<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">Echte naam en voetbalnaam</span>
                </span>
            </a>

            [#if user?has_content && user.role == 'ADMIN']
            <a href="/admin" class="home-navigation-link bg-darkred">
                <span class='h2 c-white home-navigation-link-title' >
                    Admin<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">Zaken regelen</span>
                </span>
            </a>
            [/#if]

        </div>

        [@footerUtil.footer /]
    </body>
</html>