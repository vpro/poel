[#include 'countries.ftl']
[#import 'bonuses.ftl' as bonusUtil]

[#assign STATUS_FINISHED = 'finished']
[#assign STATUS_UNFINISHED = 'unfinished']
[#assign STATUS_FUTURE = 'future']

[#macro showPrediction status scoredPrediction parity index]
    [#if scoredPrediction.prediction.match ? has_content]
        [@showMatch status scoredPrediction parity index /]
    [#elseif scoredPrediction.prediction.bonus ? has_content]
        [@showBonus status scoredPrediction parity index /]
    [/#if]
[/#macro]

[#macro showMatch status scoredPrediction parity index]

    [#assign prediction = scoredPrediction.prediction]
    [#assign predictionId = prediction.id !]
    [#assign match = prediction.match]
    [#assign actualResult = prediction.match.matchResult !]
    [#assign predictedResult = prediction.matchResult !]
    [#assign hasPredictedResult = predictedResult ? has_content]
    [#assign multiplier = prediction.multiplier]
    [#assign score = scoredPrediction.score]
    [#assign predictionIndex = index]
    [#assign timeToStart = match.start?long - .now?long]

<table class="predictions match-prediction">
    <tbody>

    <tr class="prediction__row prediction__row-${ parity }">
        <td class="prediction__game">

            <span class="prediction__game-home">
                [#if countryCodes[match.homeTeam] ? has_content]
                <span class="flag-icon flag-icon-${ countryCodes[match.homeTeam] !}"></span>
                [/#if]
                ${ match.homeTeam }
            </span>

            <span class="prediction__game-away">
                [#if countryCodes[match.awayTeam] ? has_content]
                <span class="flag-icon flag-icon-${ countryCodes[match.awayTeam] !}"></span>
                [/#if]
                ${ match.awayTeam }
            </span>
            [#if status == STATUS_FUTURE]
                <input type="hidden" name="predictions[${predictionIndex}].matchId" value="${match.id}"/>
                [#if predictionId?has_content]
                <input type="hidden" name="predictions[${predictionIndex}].predictionId" value="${predictionId}"/>
                [/#if]
            [/#if]
        </td>

        <td class="prediction__predicted">
            <input class="prediction home-prediction" type="number" min="0" name="predictions[${predictionIndex}].homeTeamGoals" [#if hasPredictedResult]value="${predictedResult.homeTeamGoals}" [/#if] [#if status != STATUS_FUTURE ]disabled [/#if] />
            <input class="prediction away-prediction" type="number" min="0" name="predictions[${predictionIndex}].awayTeamGoals" [#if hasPredictedResult]value="${predictedResult.awayTeamGoals}" [/#if] [#if status != STATUS_FUTURE ]disabled [/#if] />
        </td>

        <td class="prediction__multiplier
                [#if status == STATUS_FUTURE]prediction__multiplier_editable[/#if]
                [#if score ? has_content && status == STATUS_FINISHED]prediction__multiplier_points-${ score }[/#if]
                [#if multiplier]prediction__mulitplier_checked[/#if]
                ">

            [#if status == STATUS_FUTURE || ( status != STATUS_FUTURE && multiplier ) ]
            <input class="prediction__multiplier__input" id="prediction__multiplier${predictionIndex}_${status}" type="checkbox"
                   name="predictions[${predictionIndex}].multiplier" value="true"
                   [#if multiplier]checked="checked"[/#if] [#if status != STATUS_FUTURE]disabled="disable"[/#if] />
            <label class="prediction__multiplier__label" title="Joker inzetten"
                   for="prediction__multiplier${predictionIndex}_${status}">
                    <i class="glyph glyph-heart"></i>
                    <span class="prediction__multiplier__label-count"></span>
            </label>
            [/#if]
        </td>

        <td class="prediction__score">
            [#if score ? has_content && status == STATUS_FINISHED]
                <span class="prediction__points prediction__points-${score}">${score}</span>
            [/#if]
        </td>

    </tr>


    <tr class="prediction__row prediction__row-result prediction__row-${ parity }">
        <td class="prediction__result-title" >
            ${match.start}

            [#if timeToStart > 0 &&  timeToStart < 10800000 && !hasPredictedResult ]
            <br><span class="prediction-deadline c-white bg-red"><i class="glyph glyph-alert c-white"></i> Let op: wedstrijd begint bijna! </span>
            [/#if]
        </td>
        <td class="prediction__result" >
            [#if actualResult ? has_content]
                <input type="number" value="${actualResult.homeTeamGoals}" disabled />
                <input type="number" value="${actualResult.awayTeamGoals}" disabled />
            [/#if]
        </td>
        <td class="">
        </td>
        <td class="prediction__score">
        </td>
    </tr>

    </tbody>
</table>
[/#macro]



[#macro showBonus status scoredPrediction parity index]

    [#assign prediction = scoredPrediction.prediction]
    [#assign predictionId = prediction.id !]
    [#assign bonus = prediction.bonus]
    [#assign bonusScore = bonus.score]
    [#assign actualResult = bonus.answer !]
    [#assign predictedResult = prediction.answer !]
    [#assign hasPredictedResult = predictedResult ? has_content]
    [#assign multiplier = prediction.multiplier]
    [#assign score = scoredPrediction.score]
    [#assign predictionIndex = index]
    [#assign timeToStart = bonus.start?long - .now?long]

<table class="predictions bonus-prediction">
    <tbody>

    <tr class="prediction__row prediction__row-${ parity }">
        <td class="prediction__question">

            ${ bonus.question } (voor ${bonusScore} punt[#if bonusScore != 1]en[/#if])

            [#if status == STATUS_FUTURE]
                <input type="hidden" name="predictions[${predictionIndex}].bonusId" value="${bonus.id}"/>
                [#if predictionId?has_content]
                    <input type="hidden" name="predictions[${predictionIndex}].predictionId" value="${predictionId}"/>
                [/#if]
            [/#if]
        </td>

        <td class="prediction__predicted">
            [#if bonus.category ? has_content && ( hasPredictedResult || ( ! hasPredictedResult && status == STATUS_FUTURE ) ) ]
                [#if bonus.category == 'COUNTRY']
                    [@bonusUtil.bonusAnswerSelection answers=countries formPath='predictions[${predictionIndex}].answerId' selectedAnswer=(predictedResult!) addCss='prediction' disabled=(status != STATUS_FUTURE) /]
                [#elseif bonus.category == 'PLAYER']
                    [@bonusUtil.bonusAnswerSelection answers=players formPath='predictions[${predictionIndex}].answerId' selectedAnswer=(predictedResult!) addCss='prediction' disabled=(status != STATUS_FUTURE) /]
                [#elseif bonus.category == 'SCORE']
                    [@bonusUtil.bonusAnswerSelection answers=players formPath='predictions[${predictionIndex}].answerId' selectedAnswer=(predictedResult!) addCss='prediction' disabled=(status != STATUS_FUTURE) /]
                [/#if]
            [/#if]
        </td>

        <td class="prediction__multiplier
                [#if status == STATUS_FUTURE]prediction__multiplier_editable[/#if]
                [#if score ? has_content && status == STATUS_FINISHED]prediction__multiplier_points-${ score }[/#if]
                [#if multiplier]prediction__mulitplier_checked[/#if]
                ">

            [#if status == STATUS_FUTURE || ( status != STATUS_FUTURE && multiplier ) ]
                <input class="prediction__multiplier__input" id="prediction__multiplier${predictionIndex}_${status}" type="checkbox"
                       name="predictions[${predictionIndex}].multiplier" value="true"
                       [#if multiplier]checked="checked"[/#if] [#if status != STATUS_FUTURE]disabled="disable"[/#if] />
                <label class="prediction__multiplier__label" title="Joker inzetten"
                       for="prediction__multiplier${predictionIndex}_${status}">
                        <i class="glyph glyph-heart"></i>
                        <span class="prediction__multiplier__label-count"></span>
                </label>
            [/#if]
        </td>

        <td class="prediction__score">
            [#if score ? has_content && status == STATUS_FINISHED]
                <span class="prediction__points prediction__points-${score}">${score}</span>
            [/#if]
        </td>

    </tr>


    <tr class="prediction__row prediction__row-result prediction__row-${ parity }">
        <td class="prediction__result-title" >
            ${bonus.start}

            [#if timeToStart > 0 &&  timeToStart < 10800000 && !hasPredictedResult ]
                <br><span class="prediction-deadline c-white bg-red"><i class="glyph glyph-alert c-white"></i> Let op: deadline is bijna! </span>
            [/#if]
        </td>
        <td class="prediction__result" >
            [#if actualResult ? has_content]
                ${actualResult.value}
            [/#if]
        </td>
        <td class="">
        </td>
        <td class="prediction__score">
        </td>
    </tr>

    </tbody>
</table>
[/#macro]
