[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]

[#import "macros/message.ftl" as messageUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Profiel' /]

    <body>

    [@navigationUtil.navigation title='Profiel' back='/' /]

    [#if message ? has_content]
        [@messageUtil.outputMessage message=message ! /]
    [/#if]

        <div class="grid grid-gutter bg-blue">
            <h1 class="h4">Hallo ${user.realName}!
                (${user.username} / ${user.role} )!
            </h1>

            <form action="/user" method="post">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                <input type="hidden" name="updateUser.username" value="${updateUser.username}"/>
                <input type="text" name="updateUser.gameName" value="${updateUser.gameName}"/>
                <button type="submit">sla op</button>
            </form>

            [#if user.role == 'ADMIN']
                <a href="/admin/"> Admin gedeelte </a>
            [/#if]

        </div>

    [@footerUtil.footer /]
    </body>
</html>
