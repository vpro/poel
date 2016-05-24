[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/layout.ftl" as layout]

[#import "macros/message.ftl" as messageUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Profiel' /]

    <body>

    [@navigationUtil.navigation title='Profiel' back='/' /]

    [#if message ? has_content]
        [@messageUtil.outputMessage message=message ! /]
    [/#if]

    [@layout.sectionWithLayout
    content={"layout":"100"}
    title=''
    collapsible=false
    opened=true
    addCss='user-section'
    addContainerCss='bg-blue'
    backGroundColor='blue' ]

        <div class="grid c-white grid-gutter">
            <h1 class="h5"> Gegevens voor [#if user.gameName ? has_content]${ user.gameName }[#else]${ user.realName }[/#if]: </h1>

            <div class="grid row">
                <span class="h6 col col-12-2">Naam:</span>
                <span class="col col-12-2"> ${user.realName}</span>
            </div>

            <div class="grid row">
                <span class="h6 col col-12-2">Email:</span>
                <span class="col col-12-2"> ${user.username}</span>
            </div>

            <div class="grid row">
                <form class="form" action="/user" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                    <input type="hidden" name="updateUser.username" value="${updateUser.username}"/>

                    <span class="h6 col col-12-2">Voetbalnaam:</span>

                    <input class="col col-12-2 " type="text" name="updateUser.gameName" value="${updateUser.gameName}"/>
                    <button class="col col-12-1" type="submit">sla op</button>
                </form>
            </div>

            [#if user.role == 'ADMIN']
                <a href="/admin/" class="c-white"> Ga naar het Admin gedeelte </a>
            [/#if]

        </div>

    [/@layout.sectionWithLayout]

    [@footerUtil.footer /]
    </body>
</html>
