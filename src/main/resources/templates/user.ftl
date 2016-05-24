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

    [#if user.gameName ? has_content]
        [#assign salutation = user.gameName]
    [#else]
        [#assign salutation = user.realName]
    [/#if]

    [@layout.sectionWithLayout
    content={"layout":"100"}
    title='Gegevens voor: ${ salutation }'
    collapsible=false
    opened=true
    addCss='user-section'
    addContainerCss='bg-blue'
    backGroundColor='blue' ]

        <div class="grid c-white grid-gutter">

            [#if user.role == 'ADMIN']
            <div class="grid row">
                <a href="/admin/" class="c-white"> Ga naar het Admin gedeelte </a>
            </div>
            [/#if]

            <div class="grid row">
                <span class="h6 col col-12-2">Naam:</span>
                <span class="col col-12-2"> ${user.realName}</span>
            </div>

            <div class="grid row">
                <span class="h6 col col-12-2">Email:</span>
                <span class="col col-12-2"> ${user.username}</span>
            </div>

            <div class="grid row">
                <span class="h6 col col-12-2">Afdeling:</span>
                <span class="col col-12-2">
                    [#if user.userGroup ? has_content]
                        ${ user.userGroup.name }
                    [#else]
                        Nog niet ingedeeld door de poelleiding.
                    [/#if]
                </span>
            </div>

            <div class="grid row">
                <form class="form" action="/user" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
                    <input type="hidden" name="updateUser.username" value="${updateUser.username}"/>

                    <span class="h6 col col-12-2">Voetbalnaam:</span>

                    <input class="col col-12-2 " type="text" name="updateUser.gameName" value="${updateUser.gameName}"/>

                    <div class="form-footer bg-blue">
                        <button class="h5 button submit-button" type="submit">Opslaan</button>
                    </div>
                </form>
            </div>

        </div>

    [/@layout.sectionWithLayout]

    [@footerUtil.footer /]
    </body>
</html>
