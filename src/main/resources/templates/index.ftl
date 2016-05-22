[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='VPRO Poel' /]

    <body class="home">

        <div class="home-navigation">
            <a href="/about" class="home-navigation-link bg-greybat">
                <span class='h2 c-white home-navigation-link-title'>
                    VPRO poel<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">van voetballers en dingen die voorbij gaan</span>
                </span>
            </a>
            <a href="/user" class="home-navigation-link bg-blue">
                <span class='h2 c-white home-navigation-link-title' >
                    jouw profiel<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">voetbalnaam en email</span>
                </span>
            </a>
            <a href="/predictions" class="home-navigation-link bg-green">
                <span class='h2 c-white home-navigation-link-title' >
                    poel invullen<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">je hebt xxxx punten</span>
                </span>
            </a>
            <a href="/ranking" class="home-navigation-link bg-darkgreen">
                <span class='h2 c-white home-navigation-link-title' >
                    poelstand<br/>
                    <span class="h6 c-white home-navigation-link-subtitle">je staat op positie xxxxx</span>
                </span>
            </a>

        </div>

        [@footerUtil.footer /]
    </body>
</html>