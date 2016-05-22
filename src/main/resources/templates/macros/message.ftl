[#import "layout.ftl" as layout]


[#macro outputMessage message]
    <div class="grid component-theme bg-yellow">
        [@layout.sectionWithLayout
        content={'layout': '100'}
        title=''
        addCss='message'
        ]
            <p class="message-text">${ message }</p>
        [/@layout.sectionWithLayout]
    </div>
[/#macro]