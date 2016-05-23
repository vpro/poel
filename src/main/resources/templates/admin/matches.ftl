[#import "../macros/head.ftl" as headUtil]
[#import "../macros/footer.ftl" as footerUtil]
[#import "../macros/layout.ftl" as layout]

[#import "../macros/navigation.ftl" as navigationUtil]

<!DOCTYPE html>

<html lang="nl">

    [@headUtil.head title="wedstrijden beheren" /]
    <body>

    [@navigationUtil.navigation title='Admin' subtitle='wedstrijden' back='/' /]

        <div class="grid">

            [@layout.sectionWithLayout
                content={'layout': '100'}
                title='wedstrijden'
                backGroundColor="bg-darkgreen"
            ]

                [#list matches]

                <form action="#" class="form" method="post">

                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <ul class="matches">

                        [#items as match]
                            <li class="match">

                                <input type="hidden" name="matches[${match?index}].id" value="${match.id}" />

                                <input type="text" name="matches[${match?index}].homeTeam" value="${match.homeTeam}" />
                                <input type="text" name="matches[${match?index}].awayTeam" value="${match.awayTeam}" />
                                <input type="datetime-local" name="matches[${match?index}].start" value="${match.start?string["yyyy-MM-dd'T'hh:mm"]}" />

                                <input type="button" value="Verwijderen" class="delete-match" />

                            </li>
                        [/#items]

                    </ul>


                    <input type="button" class="sort-matches" value="Sorteren (datum)"/>

                    <input type="button" class="add-match" value="Toevoegen"/>

                    <input type="submit" value="Opslaan" />
                    <input type="reset" value="Annuleren" />

                </form>

                [#else]
                    Hier is (nog) niets
                [/#list]

            [/@layout.sectionWithLayout]

        </div>

    [@footerUtil.footer /]

    <script>
        System.import( '/js/form/controllers/MatchController.js' ).then( function ( matchControllerModule ) {

            new matchControllerModule.default( document.querySelectorAll( 'form' ) );

        } );
    </script>

    </body>

</html>