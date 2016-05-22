[#import "layout.ftl" as layout]


[#macro outputMessage message]
    <div class="grid component-theme bg-yellow">
        [@layout.sectionWithLayout
        content={'layout': '100'}
        title=''
        ]
            <p>${ message.value }</p>
        [/@layout.sectionWithLayout]
    </div>
[/#macro]