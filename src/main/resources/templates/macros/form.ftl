[#include 'countries.ftl']

[#assign STATUS_FINISHED = 'finished']
[#assign STATUS_UNFINISHED = 'unfinished']
[#assign STATUS_FUTURE = 'future']

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
            <span class="prediction__game-divider">
                -
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
                [#if score ? has_content]prediction__multiplier_points-${ score }[/#if]
                ">
            <input class="prediction__multiplier__input" id="prediction__multiplier${predictionIndex}_${status}" type="checkbox"
                   name="predictions[${predictionIndex}].multiplier" value="true"
                   [#if multiplier]checked="checked"[/#if] [#if status != STATUS_FUTURE]disabled="disable"[/#if] />

            <label class="prediction__multiplier__label" title="Joker inzetten"
                   for="prediction__multiplier${predictionIndex}_${status}"><i class="glyph glyph-heart"></i></label>
        </td>

        <td class="prediction__score">
            [#if score ? has_content]
                <span class="prediction__points prediction__points-${score}">${score}</span>
            [/#if]
        </td>

    </tr>


    <tr class="prediction__row prediction__row-result prediction__row-${ parity }">
        <td class="prediction__result-title" >

            [#if status == STATUS_FUTURE]
                Wedstrijd op: ${match.start?string["dd-MM, HH:mm"]}

                [#if match.start?long - .now?long < 10800000 && !hasPredictedResult ]
                <br><span class="prediction-deadline c-white bg-red"><i class="glyph glyph-alert c-white"></i> Let op: wedstrijd begint bijna! </span>
                [/#if]
            [#elseif status == STATUS_FINISHED]
                Uitslag:
            [#else]
                Wedstrijd gestart op: ${match.start?string["dd-MM, HH:mm"]}
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
