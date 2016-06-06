[#macro bonusCategorySelection categories formPath='' selectedCategory='' addCss='']
<div class="select-container">
    <select class="bonus-choice-admin__category-selection ${ addCss }" name="${ formPath }">
        <option value="">Kies een categorie...</option>
        [#list categories as category]
            <option value="${ category }" [#if category == selectedCategory]selected="selected"[/#if]>${ category }</option>
        [/#list]
    </select>
</div>
[/#macro]


[#macro bonusAnswerSelection answers formPath='' selectedAnswer='' addCss='' disabled=false]
<div class="select-container [#if disabled]select-container__disabled[/#if]">
    <select class="bonus-admin__answer-selection ${ addCss }" name="${ formPath }" [#if disabled]disabled="disabled"[/#if]>
        <option value="""">Kies een antwoord...</option>
        [#list answers as answer]
            <option value="${ answer.id }" [#if selectedAnswer ? has_content && answer.id == selectedAnswer.id]selected="selected"[/#if]>${ answer.value }</option>
        [/#list]
    </select>
</div>
[/#macro]