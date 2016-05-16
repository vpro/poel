[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='hello' /]

    <body class="home">

        <!--<div class="grid grid-gutter">-->

            <!--<p>-->
                <!--Todo:  <br />-->
                <!--Logo  <br />-->
                <!--Korte uitleg  <br />-->
            <!--</p>-->

            <div class="home-navigation">
                <a href="/about" class="home-navigation-link bg-greybat">
                    <span class='h2 c-white home-navigation-link-title' href="/about">Het Spel</span>
                </a>
                <a href="/form" class="home-navigation-link bg-green">
                    <span class='h2 c-white home-navigation-link-title' >Spelen</span>
                </a>
                <a href="/ranking" class="home-navigation-link bg-darkgreen">
                    <span class='h2 c-white home-navigation-link-title' >De Spelers</span>
                </a>
            </div>

        <!--</div>-->

    </body>
</html>