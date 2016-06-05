[#import "macros/head.ftl" as headUtil]
[#import "macros/footer.ftl" as footerUtil]

[#import "macros/navigation.ftl" as navigationUtil]
[#import "macros/layout.ftl" as layout]

[#import "macros/message.ftl" as messageUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Profiel' /]

    <body>


    [#if flash ? has_content]
    <div class="alert-overlay">
        <div class="alert-overlay__content">
            ${flash} <br/> <br/>
            <button class="h5 button alert-overlay__close-button">Ok!</button>
        </div>
    </div>
    [/#if]


    [@navigationUtil.navigation title='Profiel' back='/' /]

    [#if message ? has_content]
        [@messageUtil.outputMessage message=message ! /]
    [/#if]

    [#assign salutation = user.gameName!user.realName!]


    [#if user?has_content]
    <form action="/logout" method="post" class="user-logout bg-greybat grid">
        <div class="col col-12-3"></div>
        <div class="col col-12-6">
            <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>
            <input type="submit" class="user-logout-button bg-blue" value="log uit"/>
        </div>
        <div class="col col-12-3"></div>

    </form>
    [/#if]


    [@layout.sectionWithLayout
    content={"layout":"100"}
    title='Profielgegevens'
    collapsible=false
    opened=true
    addCss='user-section'
    addContainerCss='bg-blue' ]

        <div class="grid c-white grid-gutter">

            <div class="grid row">
                <span class="h6 col col-12-2">Email:</span>
                <span class="col col-12-2">${user.username}</span>
            </div>

            <div class="grid row">
                <span class="h6 col col-12-2">Afdeling:</span>
                <span class="col col-12-2">
                    [#if user.userGroup ? has_content]
                        ${ user.userGroup.name }
                    [#else]
                        Nog niet ingedeeld door de poelleiding
                    [/#if]
                </span>
            </div>


            <form class="form" action="#" method="post">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                <div class="grid row">
                    <span class="h6 col col-12-2">Echte naam:</span>
                    <input class="col col-12-4 " type="text" name="realName" value="${user.realName!}"/>
                </div>

                <div class="grid row">
                    <span class="h6 col col-12-2">Voetbalnaam:</span>
                    <input class="col col-12-4 " type="text" name="gameName" value="${user.gameName!}"/>
                </div>

                <div class="form-footer bg-blue">
                    <button class="h5 button submit-button" type="submit">Opslaan</button>
                </div>
            </form>



        </div>

    [/@layout.sectionWithLayout]

    [@footerUtil.footer /]

    </body>
</html>
