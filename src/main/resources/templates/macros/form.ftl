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
                <span class="flag-icon flag-icon-${ countryCodes[match.homeTeam] !}"></span>
                ${ match.homeTeam }
            </span>
            <span class="prediction__game-divider">
                -
            </span>
            <span class="prediction__game-away">
                <span class="flag-icon flag-icon-${ countryCodes[match.awayTeam] !}"></span>
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

        <td class="prediction__multiplier">
            <input id="prediction__multiplier${predictionIndex}" type="checkbox" name="predictions[${predictionIndex}].multiplier" value="true" [#if multiplier]checked[/#if] />
            <label for="prediction__multiplier${predictionIndex}">Joker</label>
        </td>

        <td class="prediction__score">
            [#if score ? has_content]
                <span class='prediction__points prediction__points-${score} '>${score}</span>
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
