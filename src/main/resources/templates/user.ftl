[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Profiel' /]

    <body>

    [@navigationUtil.navigation title='Profiel' back='/' /]

        <div class="grid grid-gutter bg-white">
            <h1 class="h4">Hallo gebruiker ${user.displayName} (${user.username}/${user.role})!</h1>

            <p>
                Todo: <br />

                Een gebruiker kan hier z'n voetbalnaam invullen en opslaan  <br />
                Een gebruiker ziet hier eventueel in welke afdeling hij geplaatst is? <br />

            </p>

        </div>

    [@footerUtil.footer /]
    </body>
</html>
