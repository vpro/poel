import Stapes from 'stapes';
import $ from 'jquery';

var CollapseController = Stapes.subclass( {

    constructor : function ( sections ) {
        this.$sections = $( sections );
        $.each( this.$sections, function ( i, section ) {

            this.bindClickHandlers( $( section ) );

        }.bind( this ) );

    },

    bindClickHandlers: function ( $section ) {

        $section.on( 'click', function () {
            $section.toggleClass( 'collapsible-section-opened' );

            if ( $section.hasClass('collapsible-section-opened') ) {
                $section.removeClass( $section.data( 'closedcolor' ) );
                $section.addClass( $section.data( 'opencolor' ) );
            } else {
                $section.removeClass( $section.data( 'opencolor' ) );
                $section.addClass( $section.data( 'closedcolor' ) );

            }
        } );

    }

});

export default CollapseController;