[#macro navigation title='' subtitle='' back='' bgColor='bg-green']
<div class="grid">
    <nav class="top-navigation ${ bgColor }">
        <span class="h3 c-white top-navigation-title"> ${title}
        [#if subtitle ? has_content]
            <br><span class="h6 top-navigation-subtitle">${ subtitle }</span>
            [/#if]
        </span>

        <a class="top-navigation-back" href="${ back }"> <i class="glyph glyph-arrowleft c-white"></i></a>
    </nav>
</div>
[/#macro]