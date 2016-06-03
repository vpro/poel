[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]
[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>
<html lang="nl">

    [@headUtil.head title='Berichten beheren' /]
    <body>

        [#if flash ? has_content]
        <div class="alert-overlay">
            <div class="alert-overlay__content">
            ${flash} <br/> <br/>
                <button class="h5 button alert-overlay__close-button">Ok!</button>
            </div>
        </div>
        [/#if]

        [@navigationUtil.navigation title='Admin' subtitle='berichten' bgColor='bg-darkred' back='/admin' /]

        <div class="grid">

        [@layout.sectionWithLayout
        content={'layout': '100'}
        title='berichten'
        backGroundColor="darkgreen"
        ]
            <form action="/admin/messages" class="form messages-form" method="post">
                <input name="${_csrf.parameterName}" type="hidden" value="${_csrf.token}"/>

                <div class="grid-gutter messages">
                    [#list messages]
                        [#items as message]
                        <div class="form-row message-admin row">
                            <input type="hidden" name="messages[${ message ? index }].id" value="${message.id}"/>
                            <div class="grid">

                                <div class="col col-12-5">
                                    <input type="text" name="messages[${ message ? index }].key" class="message-admin__key" value="${message.key}" placeholder="message key*"/>
                                </div>
                                <div class="col col-12-6">
                                    <textarea name="messages[${ message ? index}].text" class="message-admin__text">${ message.text }</textarea>
                                </div>
                                <div class="delete delete-message col-12-1">
                                    <i class="glyph glyph-close c-gold"></i>
                                </div>
                            </div>
                        </div>
                        [/#items]
                    [/#list]
                </div>

                <div class="grid-gutter form-button-container messages-form-button-container">
                    <input type="button" class="add-message" value="Toevoegen"/>
                    <input type="submit" value="Opslaan" />
                </div>
            </form>


        [/@layout.sectionWithLayout]

        </div>

        [@footerUtil.footer /]
    </body>
</html>