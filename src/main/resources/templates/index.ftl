[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='VPRO EK-poel 2016' /]

    <body class="home">

        <div class="home-navigation">
            <a href="/about" class="home-navigation-link bg-greybat">
                <span class='h2 c-white home-navigation-link-title'>
                    VPRO EK-poel 2016<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">hoe werkt het?</span>
                </span>
            </a>
            <a href="/predictions" class="home-navigation-link bg-green">
                <span class='h2 c-white home-navigation-link-title' >
                    poel invullen<br/>
                    [#if ranking ? has_content]
                        <span class="h6 c-white home-navigation-link-subtitle">Je hebt ${ ranking.score ! '?' } punten</span>
                    [/#if]
                </span>
            </a>
            <a href="/ranking" class="home-navigation-link bg-darkgreen">
                <span class='h2 c-white home-navigation-link-title' >
                    poelstand<br/>
                    [#if ranking ? has_content]
                        <span class="h6 c-white home-navigation-link-subtitle">je staat op positie ${ ranking.rank ! '?' }</span>
                    [/#if]
                </span>
            </a>
            <a href="/user" class="home-navigation-link bg-blue">
                <span class='h2 c-white home-navigation-link-title' >
                    mijn poelprofiel<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">voetbalnaam en email</span>
                </span>
            </a>
        </div>

        [@footerUtil.footer /]
    </body>
</html>