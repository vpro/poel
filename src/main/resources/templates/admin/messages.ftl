[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Berichten beheren' /]
    <body>
        [@navigationUtil.navigation title='Admin' subtitle='berichten' back='/admin' /]

        <div class="grid">

        [@layout.sectionWithLayout
        content={'layout': '100'}
        title='berichten'
        backGroundColor="bg-darkgreen"
        ]
            <div class="grid-gutter">
                [#list messages]
                    <form action="/admin/messages" class="form message-admin" method="post">
                        <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                        [#items as message]
                        <input type="hidden" name="messages[${ message ? index }].key" value="${message.key}"/>
                        <div class="grid">

                            <div class="col col-3-1">
                                <h2 class="h6 message-admin__key">${ message.key }</h2>
                            </div>
                            <div class="col col-3-2">
                                <textarea name="messages[${ message ? index}].text" class="message-admin__text">${ message.text }</textarea>
                            </div>
                        </div>
                    [/#items]

                    <div class="form-footer bg-green">
                        <button class="h5 button submit-button" type="submit">Opslaan</button>
                    </div>
                </form>
                [#else]
                    No messages at this time, sorry!
                [/#list]
            </div>
        [/@layout.sectionWithLayout]

        </div>

        [@footerUtil.footer /]
    </body>
</html>