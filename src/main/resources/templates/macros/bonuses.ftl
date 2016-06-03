[#macro bonusCategorySelection categories formPath='' selectedCategory='' addCss='']
<select class="bonus-choice-admin__category-selection ${ addCss }" name="${ formPath }">
    <option value="">Kies een categorie...</option>
    [#list categories as category]
        <option value="${ category }" [#if category == selectedCategory]selected="selected"[/#if]>${ category }</option>
    [/#list]
</select>
[/#macro]


[#macro bonusAnswerSelection answers formPath='' selectedAnswer='' addCss='']
<select class="bonus-admin__answer-selection ${ addCss }" name="${ formPath }">
    <option value="""">Kies een antwoord...</option>
    [#list answers as answer]
        <option value="${ answer.id }" [#if selectedAnswer ? has_content && answer.id == selectedAnswer.id]selected="selected"[/#if]>${ answer.value }</option>
    [/#list]
</select>
[/#macro]