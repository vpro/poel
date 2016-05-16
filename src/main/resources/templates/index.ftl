[#import "macros/head.ftl" as headUtil]
[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='hello' /]

    <body>

        <div class="grid grid-gutter">

            <!--<p>-->
                <!--Todo:  <br />-->
                <!--Logo  <br />-->
                <!--Korte uitleg  <br />-->
            <!--</p>-->

            <ul>
                <li><a href="/about">Het Spel</a></li>
                <li><a href="/form">Spelen</a></li>
                <li><a href="/ranking">De Spelers</a></li>
            </ul>

        </div>

    </body>
</html>