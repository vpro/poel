import Stapes from 'stapes';
import $ from 'jquery';

var RankingController = Stapes.subclass({


    constructor : function ( $groupFilterCheckbox ) {

        var currentUserGroupId = $groupFilterCheckbox.data('id');

        var $allRows = $( '.ranking__row' );

        var $rowsToHide = $allRows.filter( function () {
            return $( this ).data('group') != currentUserGroupId;
        });

        var $ranksToHide = $( '.ranking__rank-tohide' );

        $groupFilterCheckbox.change( function () {
            if ( $groupFilterCheckbox.is( ':checked' ) ) {
                $rowsToHide.addClass( 'ranking__row-hidden' );
                $ranksToHide.removeClass( 'hidden' );

            } else {
                $rowsToHide.removeClass( 'ranking__row-hidden' );
                $ranksToHide.addClass( 'hidden' );
            }

            var $visibleRows = $('.ranking__row:not(.ranking__row-hidden)');
            $allRows.removeClass( 'ranking__row-odd' );

            $visibleRows.each( function ( index, row ) {

                if ( index%2 == 0 ) {
                    $( row ).addClass('ranking__row-odd');
                }
            });

        });

    }

});

export default RankingController;