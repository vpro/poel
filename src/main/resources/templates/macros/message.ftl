[#import "layout.ftl" as layout]


[#macro outputMessage message=""]
    [#if message ? has_content ]
    <div class="grid component-theme bg-yellow">
        [@layout.sectionWithLayout
        content={'layout': '100'}
        title=''
        addCss='message'
        ]
            <p class="message__text theme-text">${ message }</p>
        [/@layout.sectionWithLayout]
    </div>
    [/#if]
[/#macro]