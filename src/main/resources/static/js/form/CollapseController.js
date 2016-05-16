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

        var $title = $section.find( '.collapsible-section-title' );
        var $content = $section.find( '.collapsible-section-content' );

        $title.on( 'click', function () {

            $section.toggleClass( 'open' );

        } );

    }

});

export default CollapseController;