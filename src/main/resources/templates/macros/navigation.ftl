[#macro navigation title='' back='' bgColor='bg-green']
<div class="grid">
    <nav class="top-navigation ${ bgColor }">
        <span class="h3 c-white top-navigation-title"> ${title}</span>

        <a class="top-navigation-back" href="${ back }"> <i class="glyph glyph-previous c-white"></i></a>
        <!--<a class="top-navigation-menu" href=""> <i class="glyph glyph-navigation c-white"></i></a>-->
    </nav>
</div>
[/#macro]