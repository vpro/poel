[#macro bonusCategorySelection categories formPath='' selectedCategory='' addCss='']
<select class="bonus-choice-admin__category-selection ${ addCss }" name="${ formPath }">
    <option valuu="">Kies een categorie...</option>
    [#list categories as category]
        <option value="${ category }" [#if category == selectedCategory]selected="selected"[/#if]>${ category }</option>
    [/#list]
</select>
[/#macro]