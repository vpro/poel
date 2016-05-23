[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Berichten beheren' /]
    <body>
        [@navigationUtil.navigation title='Berichten beheren' back='/admin' /]

        <div class="grid bg-green">
            <div class="grid-gutter">
                [#list messages]
                <form action="/admin/messages" class="form" method="post">
                    <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                    [#items as message]
                    <input type="hidden" name="messages[${ message ? index }].id" value="${message.id}"/>
                    <input type="hidden" name="messages[${ message ? index }].key" value="${message.key}"/>
                    <div class="grid">

                        <div class="col col-3-1">
                            <h2 class="h5 message-admin__key">${ message.key }</h2>
                        </div>
                        <div class="col col-3-2">
                            <textarea name="messages[${ message ? index}].text" class="message-admin__text">${ message.text }</textarea>
                        </div>
                    </div>
                    [/#items]

                    <div class="message-admin__form-buttons bg-green">
                        <button class="h5 button submit-button" type="submit">Opslaan</button>
                        <div class=""
                    </div>
                </form>
                [#else]
                    No messages at this time, sorry!
                [/#list]
            </div>
        </div>

        [@footerUtil.footer /]
    </body>
</html>