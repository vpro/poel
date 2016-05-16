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
        } );

    }

});

export default CollapseController;