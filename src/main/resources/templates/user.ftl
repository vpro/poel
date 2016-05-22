[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Profiel' /]

    <body>

    [@navigationUtil.navigation title='Profiel' back='/' /]

        <div class="grid grid-gutter bg-blue">
            <h1 class="h4">Hallo ${user.realName}!
                (${user.username} / ${user.role} )!
            </h1>

            <form>
                <input type="text" value="${user.gameName}"/>
                <button type="submit">sla op</button>
            </form>

        </div>

    [@footerUtil.footer /]
    </body>
</html>
